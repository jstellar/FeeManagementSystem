package com.fee.feeSecurity.controller;

import com.fee.feeSecurity.dto.UserDto;
import com.fee.feeSecurity.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/admin")
    public ModelAndView getAllUsers() {
        return new ModelAndView().addObject("users", adminService.getAllUsers());
    }

    @GetMapping("/admin/{id}")
    public ModelAndView displayEditUser(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin-edit");
        modelAndView.addObject("user", adminService.getUserById(id));
        return modelAndView;
    }

    @GetMapping(value = "/admin/update/{id}")
    public String updateUser(@PathVariable long id, @Valid UserDto userDto) {
//		AdminDto user = adminService.getUserById(id);
        adminService.update(id, userDto);
        return "redirect:/admin";
    }
}
