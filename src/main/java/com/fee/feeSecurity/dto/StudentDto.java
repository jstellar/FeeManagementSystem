package com.fee.feeSecurity.dto;

import com.fee.feeSecurity.entity.Role;
import com.fee.feeSecurity.entity.User;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class StudentDto extends UserDto{

    private int fee;
    private int feePaid;
    private int due;
    private boolean enabled;
    private Collection<Role> roles;

    public StudentDto(User user) {
        super(user);
        this.enabled = user.isEnabled();
        this.roles = user.getRoles();
    }
}
