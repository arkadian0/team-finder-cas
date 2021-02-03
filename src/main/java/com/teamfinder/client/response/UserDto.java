package com.teamfinder.client.response;

import com.teamfinder.domain.authorization.enums.SystemRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String email;
    private SystemRole roleName;
    private boolean enabled;

    public UserDto(String email, SystemRole roleName, boolean enabled) {
        this.email = email;
        this.roleName = roleName;
        this.enabled = enabled;
    }
}
