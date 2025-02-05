package org.project.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.validation.UniqueEmail;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequestDTO {
    @NotBlank(message = "{auth.username.notblank}")
    @NotNull
    String username;

    @NotBlank(message = "{auth.password.notblank}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "{auth.password.length}")
    String password;

    @Email(message = "{auth.email.valid}")
    @NotBlank(message = "{auth.email.notblank}")
    @Size(max = 255, message = "{auth.email.length}")
    @UniqueEmail
    String email;
}
