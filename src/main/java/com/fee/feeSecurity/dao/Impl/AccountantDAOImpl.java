package com.fee.feeSecurity.dao.Impl;

import com.fee.feeSecurity.dao.AccountantDAO;
import com.fee.feeSecurity.dao.RoleDAO;
import com.fee.feeSecurity.dao.UserDAO;
import com.fee.feeSecurity.dto.AccountantDto;
import com.fee.feeSecurity.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public List<AccountantDto> findAllStudents() {

        Page<User> users = userDAO
                .findAllByRoles(PageRequest.of(0, 10, Sort.Direction.ASC, "id"),
                        roleDAO.findByName("ROLE_STUDENT"));

        return users.map(AccountantDto::new).getContent();
    }

    @Override
    public AccountantDto findById(Long id) {
        return getSession().get(AccountantDto.class, id);
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
    public void makePayment() {

    }
}
