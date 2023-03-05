package com.example.helloproject.data.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable =false, length =20, unique = true)
    private Long id;

    //id
    @Column(nullable =false)
    private String username;

    //pwd
    @Column(nullable =false)
    private String password;

    //email
    @Column(nullable =false)
    private String email;

    //birth 20220222
    @Column(nullable =false)
    private Integer birth;

    //phone
    @Column(nullable =false)
    private Integer Phone;

    //admin user
    //private Role role;

}
