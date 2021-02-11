package com.teamfinder.domain.authorization;


import com.teamfinder.client.response.TfResponseValidationList;
import com.teamfinder.client.transfer.RegistrationCommand;
import com.teamfinder.domain.authorization.enums.SystemRole;
import com.teamfinder.domain.authorization.ports.incoming.UserAuthorizationPort;
import com.teamfinder.domain.authorization.ports.outcoming.ConfirmationTokenRepositoryPort;
import com.teamfinder.domain.authorization.ports.outcoming.EmailSenderPort;
import com.teamfinder.domain.authorization.ports.outcoming.RoleRepositoryPort;
import com.teamfinder.domain.authorization.ports.outcoming.UserRepositoryPort;
import com.teamfinder.validators.AccountValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
class UserAuthorizationService implements UserDetailsService, UserAuthorizationPort {

    private final UserRepositoryPort userRepositoryPort;
    private final RoleRepositoryPort roleRepositoryPort;
    private final EmailSenderPort emailSenderPort;
    private final ConfirmationTokenRepositoryPort confirmationTokenRepositoryPort;

    @Override
    public void register(RegistrationCommand registrationCommand) {
        Optional<Account> account = userRepositoryPort.findByEmail(registrationCommand.getEmail());
        Account newUser = new Account(registrationCommand, roleRepositoryPort.findByRole(SystemRole.USER).get());
        userRepositoryPort.save(newUser);
        emailSenderPort.sendEmailWithConfirmationToken(newUser.getConfirmationToken());
    }

    @Override
    public void confirmUser(String confirmationToken) {
        Optional<ConfirmationToken> token = confirmationTokenRepositoryPort.findByConfirmationToken(confirmationToken);
        token.ifPresentOrElse(this::activeUserAccount, () -> {
            throw new IllegalStateException(String.format("Invalid confirmation token : %s", confirmationToken));
        });
    }

    private void activeUserAccount(ConfirmationToken foundedToken) {
        Account user = foundedToken.getAccount();
        user.activeUser();
        userRepositoryPort.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Account> user = userRepositoryPort.findByEmail(email);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(String.format("User not found by name: %s", email));
        }
        return Account.createUserDetails(user.get());
    }
}
