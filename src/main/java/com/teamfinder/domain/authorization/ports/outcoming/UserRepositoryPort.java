package com.teamfinder.domain.authorization.ports.outcoming;

import com.teamfinder.domain.authorization.Account;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {
    Optional<Account> findByEmail(String email);

    Account save(Account preparedUser);

    List<Account> getAllUsers();

    void deleteUser(String email);
}
