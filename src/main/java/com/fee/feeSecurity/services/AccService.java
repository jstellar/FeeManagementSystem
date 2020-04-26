package com.fee.feeSecurity.services;

import com.fee.feeSecurity.dao.AccountantDAO;
import com.fee.feeSecurity.dao.RoleDAO;
import com.fee.feeSecurity.dao.UserDAO;
import com.fee.feeSecurity.dto.AccountantDto;
import com.fee.feeSecurity.dto.StudentDto;
import com.fee.feeSecurity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class AccService {

    @Autowired
    private AccountantDAO accountantDAO;

    @Autowired
    private RoleDAO roleDAO;

    public List<AccountantDto> getAllStudents() {
        Page<User> users = accountantDAO
                .findAllByRoles(PageRequest.of(0, 10, Sort.Direction.ASC, "id"),
                        roleDAO.findByName("ROLE_STUDENT"));
        return users.map(AccountantDto::new).getContent();
    }

    public StudentDto getStudentById(int id) {
        User user = accountantDAO.findById(id);
        return new StudentDto(user);
    }

    public void update(long id, StudentDto studentDto) {
    }

    public void makePayment(int id) {

        accountantDAO.payIt(id);
    }
}
