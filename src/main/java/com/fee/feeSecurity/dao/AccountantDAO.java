package com.fee.feeSecurity.dao;

import com.fee.feeSecurity.dto.AccountantDto;
import com.fee.feeSecurity.dto.StudentDto;
import com.fee.feeSecurity.entity.Role;
import com.fee.feeSecurity.entity.Student;
import com.fee.feeSecurity.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountantDAO {

    Page<User> findAllByRoles(Pageable pageable, Role role) ;
    Student findById(int id);
    void save(AccountantDto accountant);
    void deleteById(int id);
    AccountantDto findByEmail(String email);
    void payIt(int id, Student student);
}
