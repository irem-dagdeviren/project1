package org.project.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project.dto.request.LoginRequestDTO;
import org.project.dto.request.RegisterRequestDTO;
import org.project.dto.response.LoginResponse;
import org.project.entity.Auth;
import org.project.service.AuthService;
import org.project.service.TokenService;
import org.project.service.impl.BasicAuthTokenServiceImpl;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import static org.project.config.RestApis.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTHSERVICE)
public class AuthController {
    private final AuthService authService;
    private final MessageSource messageSource;
    private final TokenService tokenService;
    private final BasicAuthTokenServiceImpl basicAuthTokenService;

    @PostMapping(REGISTER)
    public ResponseEntity<Auth> register(@Valid @RequestBody RegisterRequestDTO dto) {
        // to demonstration of messages localizations
        String message = messageSource.getMessage("auth.registration.successful", null, LocaleContextHolder.getLocale());
        System.out.println(message);
        return new ResponseEntity<>(authService.register(dto), HttpStatus.CREATED);
    }

    @PostMapping(AUTHORIZE)
    public ResponseEntity<LoginResponse> authorize(@Valid @RequestBody LoginRequestDTO dto) {
        LoginResponse loginResponse = authService.authenticate(dto);
        var cookie = ResponseCookie.from("hoax-token", loginResponse.getToken().getToken()).path("/").httpOnly(true).build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(loginResponse);

    }

    @GetMapping(VERIFY_TOKEN)
    public Long verifyToken(@RequestHeader(name="Authorization", required = false) String token) {
        return basicAuthTokenService.verifyToken(token);
    }

    @PostMapping(LOGOUT)
    ResponseEntity<?> handleLogout(@RequestHeader(name="Authorization", required = false) String authorizationHeader, @CookieValue(name="hoax-token", required = false) String cookieValue){
        var tokenWithPrefix = authorizationHeader;
        if(cookieValue != null){
            tokenWithPrefix = "AnyPrefix " +cookieValue;
        }
        authService.logout(tokenWithPrefix);
        var cookie = ResponseCookie.from("hoax-token", "").path("/").maxAge(0).httpOnly(true).build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body("Logout success");
    }


    @PutMapping(ACTIVATE)
    public ResponseEntity<Auth> activateUser(@PathVariable String token) {
       authService.activate(token);
       return null;
    }


    @PostMapping(REGISTERED)
    public ResponseEntity<Boolean> hasRegistered(@RequestParam LoginRequestDTO dto) {
        return ResponseEntity.ok(authService.hasRegistered(dto));

    }

    @GetMapping(TOKEN)
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping(GETALL)
    public Page<Auth> getAll(Pageable pageable) {
        return authService.getAll(pageable);
    }
}
