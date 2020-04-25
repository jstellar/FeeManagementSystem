package com.fee.feeSecurity.dao.Impl;

import com.fee.feeSecurity.dao.RoleDAO;
import com.fee.feeSecurity.entity.Role;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleDAOImpl implements RoleDAO {

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
    public Role findByName(String name) {
        Query query =  getSession().createQuery("from Role where name=:name");
        query.setParameter("name", name);
        Role r1 = (Role) query.uniqueResult();
        System.out.println(r1);
        return r1;
    }

    @Override
    public void save(Role role) {
        Session sess = sf.openSession();
        Transaction tx = null;
        try {
            tx = sess.beginTransaction();
            getSession().save(role);
            tx.commit();
        }
        catch (Exception e) {
            if (tx!=null) tx.rollback();
            throw e;
        }
        finally {
            sess.close();
        }
        getSession().save(role);
    }
}
