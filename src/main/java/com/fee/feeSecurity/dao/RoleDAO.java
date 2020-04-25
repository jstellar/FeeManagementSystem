package com.fee.feeSecurity.dao;

import com.fee.feeSecurity.entity.Role;

public interface RoleDAO {
    Role findByName(String name);
    void save(Role role);
}
