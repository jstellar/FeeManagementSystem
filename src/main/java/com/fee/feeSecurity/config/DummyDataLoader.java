package com.fee.feeSecurity.config;

import java.util.Arrays;
import java.util.logging.Logger;

import com.fee.feeSecurity.dao.RoleDAO;
import com.fee.feeSecurity.dao.StudentDAO;
import com.fee.feeSecurity.dao.UserDAO;
import com.fee.feeSecurity.entity.Role;
import com.fee.feeSecurity.entity.Student;
import com.fee.feeSecurity.entity.User;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DummyDataLoader{

    org.slf4j.Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private StudentDAO studentDAO;

    @Transactional
    void createRole(String roleName) {
        Role role = roleDAO.findByName(roleName);
        if (role == null) {
            role = new Role(roleName);
            roleDAO.save(role);
        }
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event) {

        createRole("ROLE_ADMIN");
        createRole("ROLE_USER");
        createRole("ROLE_ACCOUNTANT");
        createRole("ROLE_STUDENT");

        User user = new User();
        user.setFirstName("Emre");
        user.setLastName("Yildiz");
        user.setUsername("admin");
        user.setEmail("emreyidiz@gmail.com");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setRoles(Arrays.asList(roleDAO.findByName("ROLE_ADMIN")));
        user.setEnabled(true);
        userDAO.save(user);

        User user2 = new User();
        user2.setFirstName("Hasan");
        user2.setLastName("Yildiz");
        user2.setUsername("user");
        user2.setEmail("mhyldiz@gmail.com");
        user2.setPassword(passwordEncoder.encode("pass"));
        user2.setRoles(Arrays.asList(roleDAO.findByName("ROLE_USER")));
        user2.setEnabled(true);
        userDAO.save(user2);

        User user3 = new User();
        user3.setFirstName("Halim");
        user3.setLastName("Yildiz");
        user3.setUsername("accountant");
        user3.setEmail("hyildiz@gmail.com");
        user3.setPassword(passwordEncoder.encode("pass"));
        user3.setRoles(Arrays.asList(roleDAO.findByName("ROLE_ACCOUNTANT")));
        user3.setEnabled(true);
        userDAO.save(user3);

        User user4 = new User();
        user4.setFirstName("Mehmet");
        user4.setLastName("Yildiz");
        user4.setUsername("student");
        user4.setEmail("mehyildiz@gmail.com");
        user4.setPassword(passwordEncoder.encode("pass"));
        user4.setRoles(Arrays.asList(roleDAO.findByName("ROLE_STUDENT")));
        user4.setEnabled(true);
        userDAO.save(user4);
        Student st3 = new Student();
        st3.setUser(user4);
        st3.setFee(1000);
        st3.setFeePaid(700);
        studentDAO.save(st3);

        User user5 = new User();
        user5.setFirstName("Dursun");
        user5.setLastName("Yildiz");
        user5.setUsername("dyildiz");
        user5.setEmail("dyildiz@gmail.com");
        user5.setPassword(passwordEncoder.encode("pass"));
        user5.setRoles(Arrays.asList(roleDAO.findByName("ROLE_STUDENT")));
        user5.setEnabled(true);
        userDAO.save(user5);
        Student student = new Student();
        student.setUser(user5);
        student.setFee(1000);
        student.setFeePaid(500);
        studentDAO.save(student);



        User user6 = new User();
        user6.setFirstName("Tekin");
        user6.setLastName("Yildiz");
        user6.setUsername("dyildiz");
        user6.setEmail("tyildiz@gmail.com");
        user6.setPassword(passwordEncoder.encode("pass"));
        user6.setRoles(Arrays.asList(roleDAO.findByName("ROLE_STUDENT")));
        user6.setEnabled(true);
        userDAO.save(user6);
        Student st2 = new Student();
        st2.setUser(user6);
        st2.setFee(1000);
        st2.setFeePaid(700);
        studentDAO.save(st2);
    }
}
