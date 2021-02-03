package com.teamfinder.validators;

import com.teamfinder.client.response.TfResponseValidationList;
import com.teamfinder.client.transfer.RegistrationCommand;
import com.teamfinder.domain.authorization.Account;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreateUserValidatorImpl extends ValidatorsMessages implements AccountValidator {

    private final PasswordValidator passwordValidator;

    CreateUserValidatorImpl(MessageSource messageSource,PasswordValidator passwordValidator) {
        super(messageSource);
        this.passwordValidator = passwordValidator;
    }

    @Override
    public TfResponseValidationList validateAccount(Optional<Account> account, RegistrationCommand registrationCommand) {
        TfResponseValidationList tfResponseValidationList = new TfResponseValidationList();
        validateEmailExists(account, tfResponseValidationList);
        validateEmailSyntax(registrationCommand.getEmail(), tfResponseValidationList);
        //validatePassword(registrationCommand.getPassword(),tfResponseValidationList); //TODO do analizy
        return tfResponseValidationList;
    }

    private void validateEmailExists(Optional<Account> account, TfResponseValidationList tfResponseValidationList) {
        if (account.isPresent()) {
            addErrors(EMAIL_ALREADY_EXIST, tfResponseValidationList);
        }
    }

    private void validateEmailSyntax(String email, TfResponseValidationList tfResponseValidationList) {
        boolean isValid = EmailValidator.getInstance().isValid(email);
        if (!isValid)
            addErrors(EMAIL_ERROR_SYNTAX, tfResponseValidationList);
    }

    private void validatePassword(String password, TfResponseValidationList tfResponseValidationList) {
        boolean isCorrectSyntax = passwordValidator.validate(password);
        if(!isCorrectSyntax)
            addErrors(PASSWORD_ERROR_SYNTAX, tfResponseValidationList);
    }
}
