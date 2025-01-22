package org.irem.service;

import org.irem.document.UserProfile;
import org.irem.dto.request.CreateUserRequestDTO;

import java.util.List;

public interface UserProfileService {
    void createUser(CreateUserRequestDTO dto);

    List<UserProfile> getAllUsers();
}
