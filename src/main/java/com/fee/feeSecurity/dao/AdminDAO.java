package com.fee.feeSecurity.dao;

import com.fee.feeSecurity.dto.AdminDto;
import com.fee.feeSecurity.entity.Role;
import com.fee.feeSecurity.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminDAO {

    Page<User> findAll(Pageable pageRequest);
    User findUserById(long id);
    void save(User user);
}
