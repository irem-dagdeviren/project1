package org.project.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.document.UserProfile;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String username;
    private String email;
    private String phone;
    private Boolean active;
    private String id;
    private Long authId;

    public UserDTO(UserProfile userProfile) {
        setId(userProfile.getId());
        setUsername(userProfile.getUsername());
        setEmail(userProfile.getEmail());
        setPhone(userProfile.getPhone());
        setActive(userProfile.getActive());
        setAuthId(userProfile.getAuthId());
    }
}
