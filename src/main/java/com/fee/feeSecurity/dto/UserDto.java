package com.fee.feeSecurity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fee.feeSecurity.entity.User;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;


@Data
public class UserDto {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

//    @NotNull
//    @Size(min = 1)
    private String username;

//    @NotNull
//    @Size(min = 6)
    private String password;

    @NotNull
    @Size(min = 1)
    private String firstName;

    @NotNull
    @Size(min = 1)
    private String lastName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dob;

//    @NotNull
//    @Size(min = 1)
    private String email;

//    @NotNull
//    @Size(min = 1)
//    private int fee;
//
//    @NotNull
//    @Size(min = 1)
//    private int feePaid;
//
//    private int due;

    public UserDto() { }

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.dob = user.getDob();
        this.email = user.getEmail();
//        this.fee = user.getFee();
//        this.feePaid = user.getFeePaid();
//        this.due = user.getDue();
    }
}
