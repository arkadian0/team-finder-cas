package com.teamfinder.validators;

import com.teamfinder.client.response.TfResponseValidation;
import com.teamfinder.client.response.TfResponseValidationList;
import org.springframework.context.MessageSource;

import java.util.Locale;

public abstract class ValidatorsMessages {
    final Locale LOCALE = Locale.forLanguageTag("pl_PL");
    final String EMAIL_ALREADY_EXIST = "validation.email.exist";
    final String EMAIL_ERROR_SYNTAX = "validation.email.syntax";
    final String PASSWORD_ERROR_SYNTAX = "validation.password.syntax";

    private final MessageSource messageSource;

    ValidatorsMessages(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    void addErrors(String code, TfResponseValidationList tfResponseValidationList) {
        String errorMessage = messageSource.getMessage(code,null,LOCALE);
        tfResponseValidationList.addError(new TfResponseValidation(code,errorMessage));
    }

}
