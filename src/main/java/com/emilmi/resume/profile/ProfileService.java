package com.emilmi.resume.profile;

public interface ProfileService {
    ProfileDto getProfile();

    ProfileDto saveProfile(ProfileDto profileDto);
}
