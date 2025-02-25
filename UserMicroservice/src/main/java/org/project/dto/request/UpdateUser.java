package org.project.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUser {
    @NotBlank(message = "{auth.username.notblank}")
    @Size(min = 4, max=255)
    String username;

    @Email(message = "{auth.email.valid}")
    @NotBlank(message = "{auth.email.notblank}")
    @Size(max = 255, message = "{auth.email.length}")
    private String email;

    @Pattern(regexp = "\\+?[0-9]{10,15}", message = "{auth.phone.valid}")
    private String phone;

}
