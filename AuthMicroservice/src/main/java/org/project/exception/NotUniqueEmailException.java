package org.project.exception;

import org.project.shared.Messages;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.Map;

public class NotUniqueEmailException extends RuntimeException{

    public NotUniqueEmailException() {
        super(Messages.getMessageForLocale("validation.exception.error", LocaleContextHolder.getLocale()));
    }
    public Map<String,String> getValidationErrors(){
        return Collections.singletonMap("email", Messages.getMessageForLocale("auth.email.unique", LocaleContextHolder.getLocale()));
    }
    public int getStatus(){
        return HttpStatus.BAD_REQUEST.value();
    }
}
