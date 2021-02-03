package com.teamfinder.domain.authorization.ports.incoming;

import com.teamfinder.client.response.UserDto;

import java.util.List;

public interface UserQueryPort {
    boolean checkEmailExist(String email);

    boolean checkUserIsEnabled(String email);

    List<UserDto> getAllUsers();
}
