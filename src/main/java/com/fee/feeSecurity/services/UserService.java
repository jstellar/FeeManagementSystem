package com.fee.feeSecurity.services;

import com.fee.feeSecurity.dao.RoleDAO;
import com.fee.feeSecurity.dao.UserDAO;
import com.fee.feeSecurity.dto.UserDto;
import com.fee.feeSecurity.entity.Role;
import com.fee.feeSecurity.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BCryptPasswordEncoder encoder;

    private UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    private User convertToUser(UserDto userDto) {
//        return modelMapper.map(userDto, User.class);
        return new User(userDto, Arrays.asList(roleDAO.findByName("ROLE_USER")));
    }

    public List<UserDto> getAllUsers() {
        Page<User> users = userDAO
                .findAllByRoles(PageRequest.of(0, 10, Sort.Direction.ASC, "id"),roleDAO.findByName("ROLE_USER"));
        return users.map(UserDto::new).getContent();
    }

    public void deleteUser(long userId) {
        userDAO.deleteUser(userId);
    }

    public void updateUser(int userId, UserDto userDto) {
        User user = convertToUser(userDto);
        user.setId(userId);
        userDAO.save(user);
    }

    public UserDto getUserById(int id) {
        return convertToDto(userDAO.findUserById(id));
    }

    public UserDto signupUser(UserDto userDto) {
        User user = convertToUser(userDto);
        user.setPassword(encoder.encode(userDto.getPassword()));
        userDAO.save(user);
        return convertToDto(user);
    }

    public UserDto getUserByUsername(String username) {
        User user = userDAO.findByUsername(username);
        return convertToDto(user);
    }

    public void createUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        Role userRole = new Role("USER");
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setRoles(roles);
        userDAO.save(user);
    }
}
