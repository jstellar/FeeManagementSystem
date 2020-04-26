package com.fee.feeSecurity.dto;

import com.fee.feeSecurity.entity.Role;
import com.fee.feeSecurity.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class AdminDto extends UserDto{

    private boolean enabled;
    private List<Role> roles;

    public AdminDto(User user) {
        super(user);
        this.enabled = user.isEnabled();
        this.roles = user.getRoles();
    }
}
