package org.project.service.impl;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.project.document.UserProfile;
import org.project.dto.request.CreateUserRequestDTO;
import org.project.dto.request.UpdateUser;
import org.project.dto.response.UserDTO;
import org.project.exception.AuthorizationException;
import org.project.exception.FeignAuthenticationException;
import org.project.exception.NotFoundException;
import org.project.exception.ServiceUnavailableException;
import org.project.manager.AuthManager;
import org.project.repository.UserProfileRepository;
import org.project.service.UserProfileService;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final CacheManager cacheManager;
    private final AuthManager authManager;

    @Override
    public void createUser(CreateUserRequestDTO dto) {
        userProfileRepository.save(UserProfile.builder()
                    .authId(dto.getAuthId())
                    .username(dto.getUsername())
                    .email(dto.getEmail())
                .build());
    }

    @Override
    public Page<UserDTO> getAllUsers(Pageable pageable, String authHeader) {
        Long id = authManager.verifyToken(authHeader);
        Page<UserProfile> users =  userProfileRepository.findByAuthIdNot(pageable, id);
        return users.map(UserDTO::new);
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

    @Override
    public void activateUser(Long authId) {
        UserProfile userProfile = userProfileRepository.findByAuthId(authId).orElseThrow( () -> new NotFoundException(authId.toString()));
        userProfile.setActive(true);
        userProfileRepository.save(userProfile);
    }

    @Override
    public UserProfile getUser(String id) {
        return userProfileRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
    @Override
    public UserProfile getUserByAuthId(Long id) {
        return userProfileRepository.findByAuthId(id).orElseThrow(() -> new NotFoundException(id.toString()));
    }

    @Override
    public void deleteUser(String id) {
        UserProfile inDB = getUser(id);
        userProfileRepository.delete(inDB);
    }

    @Override
    public UserProfile updateUser(String id, UpdateUser updateUser, String authHeader) {
        Long loggedInUser = null;
        try{
            loggedInUser = authManager.verifyToken(authHeader);
        }catch (FeignException e) {
            if (e.status() == 401) {
                throw new FeignAuthenticationException();
            } else {
                throw new ServiceUnavailableException();
            }
        }
        if(Objects.isNull(loggedInUser)) {
            throw new NotFoundException(authHeader);
        }
        UserProfile inDB = getUserByAuthId(Long.parseLong(id));
        if(loggedInUser.equals(inDB.getAuthId())) {
            inDB.setUsername(updateUser.getUsername());
            inDB.setEmail(updateUser.getEmail());
            inDB.setPhone(updateUser.getPhone());
            userProfileRepository.save(inDB);
            authManager.updateUser(inDB.getAuthId(),updateUser.getEmail(), updateUser.getUsername(), authHeader);
            return inDB;

        }
        throw new AuthorizationException();
    }

    public void clearCache() {
        Objects.requireNonNull(cacheManager.getCache("upper-case")).clear();
    }

}
