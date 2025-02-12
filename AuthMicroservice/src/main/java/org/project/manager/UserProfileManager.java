package org.project.manager;

import org.project.dto.request.CreateUserRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.project.config.RestApis.*;

@FeignClient(url =  BASE_URL + USERPROFILE , name = "userProfile" )
public interface UserProfileManager {

    @PostMapping(CREATE_USER)
    ResponseEntity<Boolean> createUser(@RequestBody CreateUserRequestDTO dto);

    @PutMapping(ACTIVATE_USER)
    ResponseEntity<Boolean> activateUser(@PathVariable Long token);
}
