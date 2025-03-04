package org.project.exception;

import org.project.shared.Messages;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;


public class ActivationException extends RuntimeException {
    public ActivationException() {
        super(Messages.getMessageForLocale("activation.mail.failure.exception", LocaleContextHolder.getLocale()));
    }

    public int getStatus(){
        return HttpStatus.BAD_GATEWAY.value();
    }
}
