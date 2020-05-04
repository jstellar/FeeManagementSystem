package com.fee.feeSecurity.controller;

import com.fee.feeSecurity.dto.AccountantDto;
import com.fee.feeSecurity.dto.StudentDto;
import com.fee.feeSecurity.entity.Student;
import com.fee.feeSecurity.services.AccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
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
//        accService.makePayment(id);
        modelAndView.addObject("student", accService.getStudentById(id));
        return modelAndView;
    }

//    @PutMapping(value = "/accountant/pay/{id}")
    @RequestMapping(method=RequestMethod.GET, value="/accountant/pay/{id}")
    public void makePayment(@PathVariable int id, @Valid StudentDto student) throws IOException {
//        if (bindingResult.hasErrors()) {
//            res.sendRedirect("/register");
//        }else {
            accService.makePayment(id, student);
//        }
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
