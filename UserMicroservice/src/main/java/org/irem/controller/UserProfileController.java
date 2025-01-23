package org.irem.controller;

import lombok.RequiredArgsConstructor;
import org.irem.document.UserProfile;
import org.irem.dto.request.CreateUserRequestDTO;
import org.irem.service.UserProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.irem.config.RestApis.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(USERPROFILE)
public class UserProfileController {
    private final UserProfileService userProfileService;

    @PostMapping(CREATE_USER)
    public ResponseEntity<Boolean> createUser(@RequestBody CreateUserRequestDTO dto) {
        userProfileService.createUser(dto);
        return ResponseEntity.ok(true);
    }

    @GetMapping(GET_ALL)
    public ResponseEntity<List<UserProfile>> getAllUsers() {
        return ResponseEntity.ok(userProfileService.getAllUsers());
    }

    @GetMapping(UPPER_NAME)
    public ResponseEntity<String> getUpperName(String name) {
        return ResponseEntity.ok(userProfileService.getUpperName(name));
    }
}
