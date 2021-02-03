package com.teamfinder.domain.authorization.ports.incoming;

public interface UserCommandPort {
    void deleteUser(String email);
}
