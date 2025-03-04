package org.project.exception;

import org.project.shared.Messages;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;

public class AuthenticationException extends RuntimeException  {

    public AuthenticationException() {
        super(Messages.getMessageForLocale("auth.invalid.credentials", LocaleContextHolder.getLocale()));
    }

    public int getStatus(){
        return HttpStatus.UNAUTHORIZED.value();
    }
}
