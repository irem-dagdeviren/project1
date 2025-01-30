package org.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="auth")
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "User name cannot be blank")
    @Column(name = "user_name", nullable = false)
    private String userName;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 255, message = "Password length must be between 8 and 255 characters")
    @Column(name = "password", nullable = false)
    private String password;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be blank")
    @Size(max = 255, message = "Email length must be less than or equal to 255 characters")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    private Boolean active;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Long createdAt;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Long updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new java.util.Date().getTime();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new java.util.Date().getTime();
    }
}
