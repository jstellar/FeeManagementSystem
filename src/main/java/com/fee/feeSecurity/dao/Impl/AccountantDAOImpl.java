package com.fee.feeSecurity.dao.Impl;

import com.fee.feeSecurity.dao.AccountantDAO;
import com.fee.feeSecurity.dao.RoleDAO;
import com.fee.feeSecurity.dao.UserDAO;
import com.fee.feeSecurity.dto.AccountantDto;
import com.fee.feeSecurity.dto.StudentDto;
import com.fee.feeSecurity.dto.UserDto;
import com.fee.feeSecurity.entity.Role;
import com.fee.feeSecurity.entity.Student;
import com.fee.feeSecurity.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class AccountantDAOImpl implements AccountantDAO {

    @Autowired
    private SessionFactory sf;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private ModelMapper modelMapper;


    public Session getSession() {
        Session session = sf.getCurrentSession();
        if (session == null) {
            sf.openSession();
        }
        return session;
    }

    @Override
    public Page<User> findAllByRoles(Pageable pageable, Role role) {
        String role_name = role.getName();
        List<User> users = getSession().createQuery("select u From User u JOIN u.roles r WHERE r.name = :role_name")
            .setParameter("role_name", role_name)
            .getResultList();
        return new PageImpl<>(users, pageable, users.size());

    }

    @Override
    public Student findById(int id) {
       Student student =  (Student) getSession().createQuery("from Student s where s.user.id =: id")
            .setParameter("id", id)
            .uniqueResult();
       return student;
    }

    @Override
    public void save(AccountantDto accountant) {
        getSession().saveOrUpdate(accountant);
    }

    @Override
    public void deleteById(int id) {
        getSession().createQuery("DELETE FROM User WHERE id=:id_del")
        .setParameter("id_del", id)
        .executeUpdate();
    }

    @Override
    public AccountantDto findByEmail(String email) {
        return getSession().get(AccountantDto.class, email);
    }

    @Override
    public void payIt(int id, Student student) {
        User user = userDAO.findUserById(id);
        int idx = user.getStudents().get(0).getId();
        student.setId(idx);
        getSession().merge(user);
        getSession().saveOrUpdate(student);
        Query q = getSession().createQuery("Update Student d Set d.due = d.fee - d.feePaid where d.id = : idx");
        q.setParameter("idx", idx)
        .executeUpdate();
    }
}
