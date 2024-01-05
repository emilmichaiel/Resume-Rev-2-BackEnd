package com.emilmi.resume.security;

public interface AuthService {
    AuthDto register(UserDto userDto);

    AuthDto authenticate(UserDto userDto);

    AuthDto refreshToken(AuthDto authDto);
}
