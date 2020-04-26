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
//    @Query( "select u from User u inner join u.roles r where r.role in :roles" )
//    List<User> findBySpecificRoles(@Param("roles") List<Role> roles);
//    @Query("SELECT u FROM User u WHERE u.id IN (SELECT ur.userId FROM UserRole ur WHERE ur.name = :role)")
    public Page<User> findAllByRoles(Pageable pageable, Role role) {
        System.out.println("Find all by roles");
//        List users = getSession().createQuery("SELECT r  FROM User u JOIN u.roles r  WHERE u.id =:1").getResultList();
//        List users = getSession().createQuery("FROM User u JOIN u.roles r  WHERE r.name = :role ORDER BY ?#{#pageable}").getResultList();
//        List users = getSession().createQuery("SELECT u FROM User u WHERE u.id IN (SELECT ur.id FROM Role ur WHERE ur.name = :role").getResultList();
//        return new PageImpl<User>(users,pageable, users.size());
//
//        Query q = getSession().createQuery("SELECT * FROM USERS WHERE LASTNAME = ?1 ORDER BY ?#{#pageable}",
//                countQuery = "SELECT count(*) FROM USERS WHERE LASTNAME = ?1",
//                nativeQuery = true);
////        Page<User> findByLastname(String lastname, Pageable pageable);

//        Query q = getSession().createQuery("select * from USER where user_type in (:userTypes) and user_context='abc'--#pageable\n");
        String st = "ROLE_STUDENT";
//        Query us = getSession().createQuery("SELECT r FROM User u JOIN u.roles r  WHERE u.id = 6");
        Query us = getSession().createQuery("select  u From User u JOIN u.roles r WHERE r.name = 'ROLE_STUDENT'");
//        us.setParameter("role.name", role.getName());
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
