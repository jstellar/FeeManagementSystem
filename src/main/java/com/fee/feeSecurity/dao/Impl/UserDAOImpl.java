package com.fee.feeSecurity.dao.Impl;

import com.fee.feeSecurity.dao.UserDAO;
import com.fee.feeSecurity.dto.UserDto;
import com.fee.feeSecurity.entity.Role;
import com.fee.feeSecurity.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserDAOImpl implements UserDAO {

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
    public void save(User user) {
        sf.getCurrentSession().save(user);
//        getSession().save(user);
    }

    @Override
    public List<User> findAll() {
        Query<User> query = getSession().createQuery("FROM User", User.class);
        return query.getResultList();
    }

    @Override
    public User findByUsername(String username) {
        Query query =  getSession().createQuery("from User where username=:username");
        query.setParameter("username", username);
        return (User) query.uniqueResult();
//        return getSession().get(User.class, username);
    }

    @Override
    public Page<User> findAllByRoles(Pageable pageable, Role role) {
        System.out.println("Find all by roles");
        Query us = getSession().createQuery("select  u From User u JOIN u.roles r WHERE r.name = 'ROLE_STUDENT'");
        List<User> users = us.getResultList();
        return new PageImpl<User>(users, pageable, users.size());

    }

    @Override
    public void deleteUser(long userId) {
        getSession().delete(userId);
    }

    @Override
    public User findUserById(long id) {
        return getSession().get(User.class, id);
    }

}
