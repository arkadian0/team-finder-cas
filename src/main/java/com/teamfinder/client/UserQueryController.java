package com.teamfinder.client;

import com.teamfinder.client.response.UserDto;
import com.teamfinder.domain.authorization.ports.incoming.UserQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserQueryController {

    private final UserQueryPort userQueryPort;

    @GetMapping(value = "/check-exist/{email}")
    public ResponseEntity<Boolean> checkEmailExists(@PathVariable String email) {
        boolean isExist = userQueryPort.checkEmailExist(email);
        return ResponseEntity.ok(isExist);
    }

    @GetMapping(value = "/check-enabled/{email}")
    public ResponseEntity<Boolean> checkUserEnabled(@PathVariable String email) {
        boolean isEnabled = userQueryPort.checkUserIsEnabled(email);
        return ResponseEntity.ok(isEnabled);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userQueryPort.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
