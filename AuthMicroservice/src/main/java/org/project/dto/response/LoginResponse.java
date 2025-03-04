package org.project.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.project.dto.AuthDTO;
import org.project.entity.Token;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginResponse {
    AuthDTO user;

    Token token;
}
