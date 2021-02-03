package com.teamfinder.domain.authorization;

import com.teamfinder.domain.authorization.ports.incoming.UserCommandPort;
import com.teamfinder.domain.authorization.ports.outcoming.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
class UserCommandAdapter implements UserCommandPort {

   private final UserRepositoryPort userRepositoryPort;

    @Override
    @Transactional
    public void deleteUser(String email) {
        userRepositoryPort.deleteUser(email);
    }
}
