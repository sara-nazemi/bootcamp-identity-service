package com.example.bootcampisservice.models.dtoes;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto extends BaseDto{
    private Long id;
    private String name;
    private String family;
    private String phoneNumber;
    private String password;
    private String nationalId;
    private String email;
    private Boolean status;
    private Date registerDate;
    private Date loginDate;
    private String role;
}
