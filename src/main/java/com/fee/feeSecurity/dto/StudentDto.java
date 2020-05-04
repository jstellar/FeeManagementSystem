package com.fee.feeSecurity.dto;

import com.fee.feeSecurity.entity.Role;
import com.fee.feeSecurity.entity.Student;
import com.fee.feeSecurity.entity.User;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int fee;
    private int feePaid;
    private int due;
//    private boolean enabled;
//    private List<Role> roles;
    private User user;

//    public StudentDto(int fee, int feePaid, int due, boolean enabled, List<Role> roles) {
//        this.fee = fee;
//        this.feePaid = feePaid;
//        this.due = due;
//        this.enabled = enabled;
//        this.roles = roles;
//    }

//    public StudentDto(User user, int fee, int feePaid, int due, boolean enabled, List<Role> roles) {
//        super(user);
//        this.fee = fee;
//        this.feePaid = feePaid;
//        this.due = due;
//        this.enabled = enabled;
//        this.roles = roles;
//    }
}
