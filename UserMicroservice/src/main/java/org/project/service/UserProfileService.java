package org.project.service;

import org.project.document.UserProfile;
import org.project.dto.request.CreateUserRequestDTO;

import java.util.List;

public interface UserProfileService {
    void createUser(CreateUserRequestDTO dto);

    List<UserProfile> getAllUsers();

    String getUpperName(String name);
}
