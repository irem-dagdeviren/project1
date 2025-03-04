package org.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="auth", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "{auth.username.notblank}")
    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @NotBlank(message = "{auth.password.notblank}")
    @Size(min = 8, max = 255, message = "{auth.password.length}")
    @Column(name = "password", nullable = false)
    private String password;

    @Email(message = "{auth.email.valid}")
    @NotBlank(message = "{auth.email.notblank}")
    @Size(max = 255, message = "{auth.email.length}")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    private Boolean active = false;

    private String activationToken;

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
