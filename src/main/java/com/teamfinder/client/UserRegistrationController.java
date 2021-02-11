package com.teamfinder.client;

import com.teamfinder.client.response.TfResponseValidationList;
import com.teamfinder.client.transfer.RegistrationCommand;
import com.teamfinder.domain.authorization.ports.incoming.UserAuthorizationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/register")
@RequiredArgsConstructor
public class UserRegistrationController {

    private final UserAuthorizationPort userAuthorizationCommandPort;

    @PostMapping
    public ResponseEntity<String> registerNotConfirmedUser(@RequestBody RegistrationCommand registrationCommand) {
        userAuthorizationCommandPort.register(registrationCommand);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("User confirmed");
    }

    @GetMapping(value = "/confirm-account")
    public ResponseEntity<String> confirmUserAccount(@RequestParam("token") String confirmationToken) {
        userAuthorizationCommandPort.confirmUser(confirmationToken);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("User confirmed");
    }
}
