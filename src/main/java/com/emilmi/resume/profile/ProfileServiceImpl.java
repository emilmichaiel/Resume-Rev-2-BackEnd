package com.emilmi.resume.profile;

import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    public ProfileServiceImpl(ProfileRepository profileRepository, ProfileMapper profileMapper) {
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
    }


    @Override
    public ProfileDto getProfile() {
        Profile profile = profileRepository
                .findAll()
                .stream()
                .findFirst()
                .orElse(new Profile());

        return profileMapper.toDto(profile);
    }

    @Override
    public ProfileDto saveProfile(ProfileDto profileDto) {
        Profile profile = profileMapper.toDocument(profileDto);

        profileRepository.findAll()
                .stream()
                .findFirst()
                .ifPresent(existingProfile -> profile.setId(existingProfile.getId()));

        return profileMapper.toDto(profileRepository.save(profile));
    }
}
