package org.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="token")
public class Token {
    @Id
    String token;

    @Transient
    String prefix = "Bearer";

    @JsonIgnore
    @ManyToOne
    Auth user;

    public Token(String prefix, String token) {
        this.prefix = prefix;
        this.token = token;
    }
}
