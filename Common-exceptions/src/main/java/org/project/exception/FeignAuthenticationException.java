package org.project.exception;

import org.project.shared.Messages;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;

public class FeignAuthenticationException extends RuntimeException {
    public FeignAuthenticationException() {
        super(Messages.getMessageForLocale("authentication.exception.error", LocaleContextHolder.getLocale()));
    }
    public int getStatus(){
        return HttpStatus.UNAUTHORIZED.value();
    }

}
