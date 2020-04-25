package com.fee.feeSecurity.controller;


import com.fee.feeSecurity.dto.UserDto;
import com.fee.feeSecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping("/users")
//    public ModelAndView getAllUsers() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("users");
//        modelAndView.addObject("users", userService.getAllUsers());
//        return modelAndView;
//    }

    @RequestMapping(method=RequestMethod.GET, value="/user")
    public ModelAndView getUserByUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        modelAndView.addObject("user", userService.getUserByUsername(username));
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editUser(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit");
        modelAndView.addObject("user", userService.getUserById(id));
        return modelAndView;
    }

    @PostMapping("/user/id")
    public String updateUser(@PathVariable long id, @Valid UserDto userDto) {
        userService.updateUser(id, userDto);
        return "redirect:/user";
    }

    @DeleteMapping
    public String deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return "redirect:/logout";
    }
}
