package org.project.service;

import org.project.dto.request.CreateUserRequestDTO;
import org.project.dto.response.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserProfileService {
    void createUser(CreateUserRequestDTO dto);

    Page<UserDTO> getAllUsers(Pageable pageable);

    String getUpperName(String name);

    void activateUser(Long authId);
}
