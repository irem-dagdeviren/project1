package org.project.exception;

import org.project.shared.Messages;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;


public class NotFoundException extends RuntimeException {
    public NotFoundException(String id) {
        super(Messages.getMessageForLocale("not.found.entity.exception", LocaleContextHolder.getLocale(), id));
    }

    public int getStatus(){
        return HttpStatus.NOT_FOUND.value();
    }
}
