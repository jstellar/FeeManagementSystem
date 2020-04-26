package com.fee.feeSecurity.dao.Impl;

import com.fee.feeSecurity.dao.AccountantDAO;
import com.fee.feeSecurity.dao.RoleDAO;
import com.fee.feeSecurity.dao.UserDAO;
import com.fee.feeSecurity.dto.AccountantDto;
import com.fee.feeSecurity.dto.StudentDto;
import com.fee.feeSecurity.entity.Role;
import com.fee.feeSecurity.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountantDAOImpl implements AccountantDAO {

    @Autowired
    private SessionFactory sf;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

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
        Query q = getSession().createQuery("select  u From User u JOIN u.roles r WHERE r.name = :role_name");
        q.setParameter("role_name", role_name);
        List<User> users = q.getResultList();
        return new PageImpl<>(users, pageable, users.size());

    }

    @Override
    public User findById(int id) {
        return getSession().get(User.class, id);
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
    public void payIt(int id) {

        Query q = getSession().createQuery("Update User d Set d.due = d.fee - d.feePaid where d.id = : id");
        q.setParameter("id", id);

    }
}
