package com.fee.feeSecurity.services;

import com.fee.feeSecurity.dao.AdminDAO;
import com.fee.feeSecurity.dao.RoleDAO;
import com.fee.feeSecurity.dao.UserDAO;
import com.fee.feeSecurity.dto.AdminDto;
import com.fee.feeSecurity.dto.UserDto;
import com.fee.feeSecurity.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminDAO adminDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private SessionFactory sf;

    @Autowired
    private UserDAO userDAO;

    public List<AdminDto> getAllUsers() {
        Page<User> users = adminDAO.findAll(PageRequest.of(0, 10, Direction.ASC, "id"));
        return users.map(AdminDto::new).getContent();
    }

    public AdminDto getUserById(long id) {
        User user = adminDAO.findUserById(id);
        return new AdminDto(user);
    }

    public void update(long id, UserDto userDto) {

        User user = adminDAO.findUserById(id);
        user.setId(userDto.getId());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());

//		new AdminDto(user);
        adminDAO.save(user);
    }
}
