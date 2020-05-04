package com.fee.feeSecurity.services;

import com.fee.feeSecurity.dao.AccountantDAO;
import com.fee.feeSecurity.dao.RoleDAO;
import com.fee.feeSecurity.dao.UserDAO;
import com.fee.feeSecurity.dto.AccountantDto;
import com.fee.feeSecurity.dto.StudentDto;
import com.fee.feeSecurity.entity.Student;
import com.fee.feeSecurity.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Arrays;
import java.util.List;

@Service
public class AccService {

    @Autowired
    private AccountantDAO accountantDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private ModelMapper modelMapper;

    public Student toStudent(StudentDto studentDto) {
//        return modelMapper.map(studentDto, Student.class);
        return new Student(studentDto);

    }

    public List<AccountantDto> getAllStudents() {
        Page<User> users = accountantDAO
                .findAllByRoles(PageRequest.of(0, 2, Sort.Direction.ASC, "id"),
                        roleDAO.findByName("ROLE_STUDENT"));
        return users.map(AccountantDto::new).getContent();
    }

    public Student getStudentById(int id) {
        Student student = accountantDAO.findById(id);
        return student;
    }

    public void update(long id, StudentDto studentDto) {
    }

    public void makePayment(int id, StudentDto studentDto) {
        Student student = toStudent(studentDto);
        accountantDAO.payIt(id, student);
    }
}
