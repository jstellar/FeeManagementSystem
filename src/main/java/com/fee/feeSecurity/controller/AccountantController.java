package com.fee.feeSecurity.controller;

import com.fee.feeSecurity.dto.AccountantDto;
import com.fee.feeSecurity.dto.StudentDto;
import com.fee.feeSecurity.services.AccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AccountantController {

    @Autowired
    private AccService accService;

    @GetMapping("/accountant")
    public ModelAndView getAllStudents() {
        return new ModelAndView().addObject("students", accService.getAllStudents());
    }

    @GetMapping(value="/accountant/student-edit/{id}")
    public ModelAndView displayForm(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("acc-student-edit");
        accService.makePayment(id);
        modelAndView.addObject("student", accService.getStudentById(id));
        return modelAndView;
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
