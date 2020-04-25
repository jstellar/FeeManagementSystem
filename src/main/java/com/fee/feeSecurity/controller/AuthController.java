package com.fee.feeSecurity.controller;

import com.fee.feeSecurity.dto.UserDto;
import com.fee.feeSecurity.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/home", "/index"}, method = RequestMethod.GET)
    public String welcome() {
        return "/home";
    }

    @RequestMapping(method=RequestMethod.POST, value="/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(HttpServletResponse res, @Valid UserDto userDto, BindingResult bindingResult, Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            res.sendRedirect("/register");
        }else {
            UserDto user = userService.signupUser(userDto);
            model.addAttribute("user", user);
            res.sendRedirect("/users");
        }

    }
}
