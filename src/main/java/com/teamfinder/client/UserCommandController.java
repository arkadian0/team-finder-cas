package com.teamfinder.client;

import com.teamfinder.domain.authorization.ports.incoming.UserCommandPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserCommandController {

    private final UserCommandPort userCommandPort;

    @DeleteMapping(value = "/{email}")
    public ResponseEntity deleteUser(@PathVariable String email) {
        userCommandPort.deleteUser(email);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("User identified by email " + email + " was deleted");
    }
}
