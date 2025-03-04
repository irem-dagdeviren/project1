package org.project.document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class UserProfile {
    @Id
    String id;

    @Indexed(unique = true)
    private Long authId;

    @NotBlank(message = "Username cannot be blank")
    @Indexed(unique = true)
    private String username;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Surname cannot be blank")
    private String surname;

    @Email(message = "Email should be valid")
    @Indexed(unique = true)
    private String email;

    @Pattern(regexp = "\\+?[0-9]{10,15}", message = "Phone number must be valid")
    private String phone;
    private String avatar;
    private Boolean active;
    private Long createdAt;

    @LastModifiedDate
    private Long updatedAt;

    public void setDefaults() {
        if (this.active == null) {
            this.active = true;
        }
        if (this.createdAt == null) {
            this.createdAt = new java.util.Date().getTime();
        }
    }

}
