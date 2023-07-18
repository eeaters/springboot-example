package io.yujie.springboot.example.entity.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "user")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "age")
    private Integer age;

}
