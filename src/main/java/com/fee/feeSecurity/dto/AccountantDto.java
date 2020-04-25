package com.fee.feeSecurity.dto;

import com.fee.feeSecurity.entity.Role;
import com.fee.feeSecurity.entity.User;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class AccountantDto extends UserDto{

    private boolean enabled;
    private Collection<Role> roles;

    public AccountantDto(User user) {
        super(user);
        this.enabled = user.isEnabled();
        this.roles = user.getRoles();
    }
}
