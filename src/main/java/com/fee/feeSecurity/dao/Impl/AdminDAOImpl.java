package com.fee.feeSecurity.dao.Impl;

import com.fee.feeSecurity.dao.AdminDAO;
import com.fee.feeSecurity.dto.AdminDto;
import com.fee.feeSecurity.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminDAOImpl implements AdminDAO {

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
    public Page<User> findAll(Pageable pageable) {

        List<User> users = getSession().createQuery("From User", User.class).getResultList();
        Page<User> usersPage = new PageImpl<>(users,pageable, users.size());

        return usersPage;
    }

    @Override
    public User findUserById(long id) {
        return getSession().get(User.class, id);
    }

    @Override
    public void save(User user) {
        getSession().save(user);
    }
}
