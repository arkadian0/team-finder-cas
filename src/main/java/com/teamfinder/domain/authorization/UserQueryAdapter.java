package com.teamfinder.domain.authorization;

import com.teamfinder.client.response.UserDto;
import com.teamfinder.domain.authorization.ports.incoming.UserQueryPort;
import com.teamfinder.domain.authorization.ports.outcoming.UserRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserQueryAdapter implements UserQueryPort {

    private final UserRepositoryPort userRepositoryPort;

    @Autowired
    public UserQueryAdapter(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public boolean checkEmailExist(String email) {
        Optional<Account> account = userRepositoryPort.findByEmail(email);
        return account.isPresent();
    }

    @Override
    public boolean checkUserIsEnabled(String email) {
        Optional<Account> account = userRepositoryPort.findByEmail(email);

        if (!account.isPresent())
            throw new IllegalStateException(String.format("User by email: %s don't exist", email));
        else
            return account.get().getIsEnabled();
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<Account> accounts = userRepositoryPort.getAllUsers();
        return accounts.stream()
                .map(account -> new UserDto(account.getEmail(),account.getRole().getName(),account.getIsEnabled()))
                .collect(Collectors.toList());
    }
}
