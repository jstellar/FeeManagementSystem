package com.fee.feeSecurity.dao.Impl;

import com.fee.feeSecurity.dao.StudentDAO;
import com.fee.feeSecurity.dto.StudentDto;
import com.fee.feeSecurity.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentDAOImpl implements StudentDAO {

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public StudentDto findById(Long id) {
        return null;
    }

    @Override
    public void save(StudentDto student) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public StudentDto findByEmail(String email) {
        return null;
    }
}