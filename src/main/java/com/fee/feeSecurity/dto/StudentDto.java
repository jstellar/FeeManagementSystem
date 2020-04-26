package com.fee.feeSecurity.dto;

import com.fee.feeSecurity.entity.Role;
import com.fee.feeSecurity.entity.User;
import lombok.Data;

import javax.persistence.Entity;
import java.util.Collection;
import java.util.List;

@Data
public class StudentDto extends UserDto{


    private boolean enabled;
    private List<Role> roles;

    public StudentDto(User user) {
        super(user);
        this.enabled = user.isEnabled();
        this.roles = user.getRoles();
    }
}
