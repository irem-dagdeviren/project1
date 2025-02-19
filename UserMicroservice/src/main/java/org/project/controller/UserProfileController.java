package org.project.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project.dto.request.CreateUserRequestDTO;
import org.project.dto.request.UpdateUser;
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

    @GetMapping(BY_ID)
    UserDTO getUserById(@PathVariable String id){
        return new UserDTO(userProfileService.getUser(id));
    }

    @DeleteMapping(BY_ID)
    ResponseEntity<Boolean> deleteUserById(@PathVariable String id){
        userProfileService.deleteUser(id);
        return ResponseEntity.ok(true);
    }

    @PutMapping(BY_ID)
    UserDTO updateUser(@PathVariable String id, @Valid @RequestBody UpdateUser updateUser){
        return new UserDTO(userProfileService.updateUser(id, updateUser));
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
