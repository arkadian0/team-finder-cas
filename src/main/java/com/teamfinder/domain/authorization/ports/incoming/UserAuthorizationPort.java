package com.teamfinder.domain.authorization.ports.incoming;

import com.teamfinder.client.response.TfResponseValidationList;
import com.teamfinder.client.transfer.RegistrationCommand;

public interface UserAuthorizationPort {
    TfResponseValidationList register(RegistrationCommand authorizationCommand);

    void confirmUser(String confirmationToken);
}