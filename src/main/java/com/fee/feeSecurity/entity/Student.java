package com.fee.feeSecurity.entity;

import com.fee.feeSecurity.dto.StudentDto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "student")
@NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s")
public class Student implements Serializable{

    @Id
    @SequenceGenerator(name="USERS_ID_GENERATOR" )
    @GeneratedValue(strategy=GenerationType.AUTO, generator="USERS_ID_GENERATOR")
    @Column(unique=true, nullable=false , name="id")
    private int id;
    private int fee;
    private int feePaid;
    private int due;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    public Student(StudentDto studentDto) {
        this(studentDto.getId(),
        studentDto.getFee(),
        studentDto.getFeePaid(),
        studentDto.getDue(),
        studentDto.getUser());
    }
}
