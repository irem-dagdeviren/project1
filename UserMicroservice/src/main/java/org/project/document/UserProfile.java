package org.project.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class UserProfile {
    @Id
    String id;
    Long authId;
    String username;
    String name;
    String surname;
    String email;
    String phone;
    String avatar;
    Boolean active;
    Long createdAt;

}
