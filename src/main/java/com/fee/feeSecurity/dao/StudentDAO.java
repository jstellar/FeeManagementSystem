package com.fee.feeSecurity.dao;

import com.fee.feeSecurity.dto.StudentDto;
import com.fee.feeSecurity.entity.User;

import java.util.List;

public interface StudentDAO {
    List<User> findAll();
    StudentDto findById(Long id);
    void save(StudentDto student);
    void deleteById(int id);

    StudentDto findByEmail(String email);
}
