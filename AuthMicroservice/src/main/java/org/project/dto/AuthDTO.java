package org.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.entity.Auth;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthDTO {

    long id;

    String username;

    String email;

    public AuthDTO(Auth auth){
        setId(auth.getId());
        setUsername(auth.getUserName());
        setEmail(auth.getEmail());
    }
}
