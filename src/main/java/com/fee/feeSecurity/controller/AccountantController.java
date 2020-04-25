package com.fee.feeSecurity.controller;

import com.fee.feeSecurity.dto.AccountantDto;
import com.fee.feeSecurity.dto.StudentDto;
import com.fee.feeSecurity.services.AccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

public class AccountantController {

    @Autowired
    private AccService accService;

    @GetMapping("/accountant")
    public ModelAndView getAllUsers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("accountant");
        modelAndView.addObject("students", accService.getAllStudents());
        return  modelAndView;
    }

    @GetMapping(value="/accountant/{id}")
    public ModelAndView displayForm(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("acc-edit");
        modelAndView.addObject("student", accService.getStudentById(id));
        return  modelAndView;
    }

    @GetMapping(value = "/accountant/update/{id}")
    public String update(@PathVariable long id, @Valid StudentDto studentDto) {
//		AdminDto user = adminService.getUserById(id);
        accService.update(id, studentDto);
        return "redirect:/accountant";
    }

    @GetMapping(value = "/accountant/create")
    public String createStudent(@Valid StudentDto studentDto) {
//		AdminDto user = adminService.getUserById(id);
//        accService.create(studentDto);
        return "redirect:/accountant";
    }

}
