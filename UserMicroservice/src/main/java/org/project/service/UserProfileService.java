package org.project.service;

import org.project.document.UserProfile;
import org.project.dto.request.CreateUserRequestDTO;
import org.project.dto.request.UpdateUser;
import org.project.dto.response.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserProfileService {
    void createUser(CreateUserRequestDTO dto);

    Page<UserDTO> getAllUsers(Pageable pageable, String id);

    String getUpperName(String name);

    void activateUser(Long authId);

    UserProfile getUser(String id);

    UserProfile getUserByAuthId(Long id);

    void deleteUser(String id);

    UserProfile updateUser(String id, UpdateUser updateUser,String authHeader);
}
