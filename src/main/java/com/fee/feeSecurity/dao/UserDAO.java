package com.fee.feeSecurity.dao;

import com.fee.feeSecurity.entity.Role;
import com.fee.feeSecurity.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserDAO {

    void save(User user);
    List<User> findAll();
    User findByUsername(String username);
    Page<User> findAllByRoles(Pageable pageable, Role role);
    void deleteUser(long userId);
    User findUserById(long id);
}
