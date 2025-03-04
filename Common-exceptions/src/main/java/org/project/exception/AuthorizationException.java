package org.project.exception;

import org.project.shared.Messages;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;

public class AuthorizationException extends RuntimeException {

    public AuthorizationException() {
        super(Messages.getMessageForLocale("unauthorized.access.credentials", LocaleContextHolder.getLocale()));
    }

    public int getStatus(){
        return HttpStatus.UNAUTHORIZED.value();
    }
}
