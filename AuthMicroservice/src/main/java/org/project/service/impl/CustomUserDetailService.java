package org.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.project.entity.Auth;
import org.project.entity.AuthPrincipal;
import org.project.repository.AuthRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Auth auth = authRepository.findByUserName(username);
        if(Objects.isNull(auth)) {
            throw new UsernameNotFoundException("user 404");
        }

        AuthPrincipal authPrincipal =  new AuthPrincipal(auth);
        return authPrincipal;
    }
}
