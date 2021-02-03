package com.teamfinder.infrastruture.repository.user;


import com.teamfinder.domain.authorization.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<Account, Long> {
    Optional<Account> findByEmail(String email);

    Long deleteByEmail(String email);
}
