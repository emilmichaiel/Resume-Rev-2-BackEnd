package com.emilmi.resume.profile;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController("EmilMiProfileController")
@RequestMapping("/v1/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ProfileDto getProfile() {
        return profileService.getProfile();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ProfileDto saveProfile(@RequestBody ProfileDto profileDto) {
        return profileService.saveProfile(profileDto);
    }
}
