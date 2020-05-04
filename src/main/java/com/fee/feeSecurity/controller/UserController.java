package com.fee.feeSecurity.controller;


import com.fee.feeSecurity.dto.UserDto;
import com.fee.feeSecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(method=RequestMethod.GET, value="/user")
    public ModelAndView getUserByUsername(Authentication authentication) {
//        Object principal = authentication.getPrincipal();
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)authentication.getPrincipal()).getUsername();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        modelAndView.addObject("user", userService.getUserByUsername(((UserDetails)authentication.getPrincipal()).getUsername()));
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editUser(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit");
        modelAndView.addObject("user", userService.getUserById(id));
        return modelAndView;
    }

    @PostMapping("/user/id")
    public String updateUser(@PathVariable int id, @Valid UserDto userDto) {
        userService.updateUser(id, userDto);
        return "redirect:/user";
    }

    @DeleteMapping
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/logout";
    }
}
