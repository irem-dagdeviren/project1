package org.project.exception;

import org.project.shared.Messages;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;


public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super(Messages.getMessageForLocale("activation.token.invalid", LocaleContextHolder.getLocale()));
    }

    public int getStatus(){
        return HttpStatus.BAD_REQUEST.value();
    }
}
