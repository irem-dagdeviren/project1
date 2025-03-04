package org.project.exception;

import org.project.shared.Messages;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;

public class ServiceUnavailableException extends RuntimeException {
    public ServiceUnavailableException() {
        super(Messages.getMessageForLocale("service.unavailable.exception.error", LocaleContextHolder.getLocale()));
    }
    public int getStatus(){
        return HttpStatus.BAD_GATEWAY.value();
    }

}
