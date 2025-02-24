package org.project.manager;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import static org.project.config.RestApis.*;

@FeignClient(url =  BASE_URL + AUTH , name = "auth" )
public interface AuthManager {

    @GetMapping(VERIFY_TOKEN)
    public Long verifyToken( @RequestHeader(name="Authorization", required = false) String token);

}