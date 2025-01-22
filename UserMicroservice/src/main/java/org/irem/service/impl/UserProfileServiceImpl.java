package org.irem.service.impl;

import lombok.RequiredArgsConstructor;
import org.irem.document.UserProfile;
import org.irem.dto.request.CreateUserRequestDTO;
import org.irem.repository.UserProfileRepository;
import org.irem.service.UserProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository userProfileRepository;

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

}
