package com.example.bootcampisservice.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="USER")
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "FAMILY")
    private String family;
    @Column(name = "USERNAME", unique = true)
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "NATIONAL_ID", unique = true)
    private String nationalId;
    @Column(name = "EMAIL", unique = true)
    private String email;
    @Column(name = "STATUS")
    private Boolean status;
    @Column(name = "REGISTER_DATE")
    private Date registerDate;
    @Column(name = "LOGIN_DATE")
    private Date loginDate;

}
