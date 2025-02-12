package org.project.controller;

import lombok.RequiredArgsConstructor;
import org.project.dto.request.CreateUserRequestDTO;
import org.project.dto.response.UserDTO;
import org.project.service.UserProfileService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.project.config.RestApis.*;

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
    public Page<UserDTO> getAllUsers(Pageable pageable) {
        return userProfileService.getAllUsers(pageable);
    }

    @GetMapping(UPPER_NAME)
    public ResponseEntity<String> getUpperName(String name) {
        return ResponseEntity.ok(userProfileService.getUpperName(name));
    }

    @PutMapping(ACTIVATE_USER)
    public ResponseEntity<Boolean> activateUser(@PathVariable Long token) {
        userProfileService.activateUser(token);
        return ResponseEntity.ok(true);
    }
}
