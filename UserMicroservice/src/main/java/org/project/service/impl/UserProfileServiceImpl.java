package org.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.project.document.UserProfile;
import org.project.dto.request.CreateUserRequestDTO;
import org.project.repository.UserProfileRepository;
import org.project.service.UserProfileService;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final CacheManager cacheManager;

    @Override
    public void createUser(CreateUserRequestDTO dto) {
        userProfileRepository.save(UserProfile.builder()
                    .authId(dto.getAuthId())
                    .username(dto.getUsername())
                    .email(dto.getEmail())
                .build());
    }

    @Override
    public List<UserProfile> getAllUsers() {
        return userProfileRepository.findAll();
    }

    @Cacheable("upper-case")
    @Override
    public String getUpperName(String name) {
        String upperName = name.toUpperCase();
        try{
            Thread.sleep(300L);
        }catch (Exception e){}
        return upperName;
    }

    public void clearCache() {
        Objects.requireNonNull(cacheManager.getCache("upper-case")).clear();
    }

}
