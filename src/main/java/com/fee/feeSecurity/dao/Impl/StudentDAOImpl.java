package com.fee.feeSecurity.dao.Impl;

import com.fee.feeSecurity.dao.StudentDAO;
import com.fee.feeSecurity.dto.StudentDto;
import com.fee.feeSecurity.entity.Student;
import com.fee.feeSecurity.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Table;
import java.util.List;

@Service
@Transactional
public class StudentDAOImpl implements StudentDAO {

    @Autowired
    private SessionFactory sf;

    public Session getSession() {
        Session session = sf.getCurrentSession();
        if (session == null) {
            sf.openSession();
        }
        return session;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public StudentDto findById(Long id) {
        return null;
    }

    @Override
    public void save(Student student) {
        getSession().save(student);
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public StudentDto findByEmail(String email) {
        return null;
    }
}