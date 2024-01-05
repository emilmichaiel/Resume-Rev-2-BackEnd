package com.emilmi.resume.security;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public AuthDto register(@Valid @RequestBody UserDto userDto) {
        return authService.register(userDto);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public AuthDto auth(@Valid @RequestBody UserDto userDto) {
        return authService.authenticate(userDto);
    }

    @PostMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    public AuthDto refreshToken(@Valid @RequestBody AuthDto authDto) {
        return authService.refreshToken(authDto);
    }
}
