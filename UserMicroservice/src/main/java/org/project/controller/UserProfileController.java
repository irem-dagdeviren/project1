package org.project.controller;

import feign.FeignException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project.dto.request.CreateUserRequestDTO;
import org.project.dto.request.UpdateUser;
import org.project.dto.response.UserDTO;
import org.project.service.UserProfileService;
import org.project.shared.Messages;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.naming.ServiceUnavailableException;

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
    public Page<UserDTO> getAllUsers(Pageable pageable, @RequestHeader(name="Authorization", required = false) String authHeader) throws AuthenticationException, ServiceUnavailableException {
        if (authHeader == null || authHeader.isEmpty()) {
           return null;
        }
        try{
            return userProfileService.getAllUsers(pageable,authHeader);
        }catch (FeignException e) {
            if (e.status() == 401) {
                throw new AuthenticationException(Messages.getMessageForLocale("authentication.exception.error", LocaleContextHolder.getLocale())); // Custom exception
            } else {
                throw new ServiceUnavailableException(Messages.getMessageForLocale("service.unavailable.exception.error", LocaleContextHolder.getLocale()));
            }
        } catch (Exception e) {
            throw new ServiceUnavailableException(Messages.getMessageForLocale("service.unavailable.communication.exception.error", LocaleContextHolder.getLocale()));
        }
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
