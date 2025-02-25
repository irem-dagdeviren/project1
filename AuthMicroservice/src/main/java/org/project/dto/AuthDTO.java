package org.project.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.entity.Auth;
import org.project.validation.UniqueEmail;
import org.project.validation.UniqueUsername;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthDTO {

    long id;

    @NotBlank(message = "{auth.username.notblank}")
    @NotNull
    @UniqueUsername
    String username;

    @Email(message = "{auth.email.valid}")
    @NotBlank(message = "{auth.email.notblank}")
    @Size(max = 255, message = "{auth.email.length}")
    @UniqueEmail
    String email;

    public AuthDTO(Auth auth){
        setId(auth.getId());
        setUsername(auth.getUserName());
        setEmail(auth.getEmail());
    }
}
