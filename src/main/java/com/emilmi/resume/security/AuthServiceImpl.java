package com.emilmi.resume.security;

import com.emilmi.resume.exception.RestApiException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthDto register(UserDto userDto) {
        User user = new User(
                userDto.email(),
                passwordEncoder.encode(userDto.password())
        );

        return getAuthDtoAndSaveCurrentTokens(user);
    }

    @Override
    public AuthDto authenticate(UserDto userDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDto.email(),
                            userDto.password()
                    )
            );
        } catch (Exception exception) {
            throw new RestApiException(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }

        User user = userRepository.findByEmail(userDto.email())
                .orElseThrow(() -> new RestApiException("Bad credentials", HttpStatus.BAD_REQUEST));

        return getAuthDtoAndSaveCurrentTokens(user);
    }

    @Override
    public AuthDto refreshToken(AuthDto authDto) {
        String refreshToken = authDto.refreshToken();

        String email = jwtTokenUtil.extractUsername(refreshToken);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RestApiException("Invalid refresh token cannot issue new tokens", HttpStatus.BAD_REQUEST));

        if (!authDto.refreshToken().equals(user.getRefreshToken())) {
            throw new RestApiException("Invalid refresh token cannot issue new tokens", HttpStatus.BAD_REQUEST);
        }

        return getAuthDtoAndSaveCurrentTokens(user);
    }

    private AuthDto getAuthDtoAndSaveCurrentTokens(User user) {
        String token = jwtTokenUtil.generateToken(user);
        String refreshToken = jwtTokenUtil.generateRefreshToken(user);
        user.setToken(token);
        user.setRefreshToken(refreshToken);
        userRepository.save(user);

        return new AuthDto(
                token,
                jwtTokenUtil.getTokenExpiration(),
                refreshToken,
                jwtTokenUtil.getRefreshExpiration()
        );
    }
}
