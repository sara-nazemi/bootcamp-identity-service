package com.example.bootcampisservice.models.dtoes;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto extends BaseDto{
    private Long id;
    private String name;
    private String family;
    private String username;
    private String password;
    private String nationalId;
    private String email;
    private Boolean status;
    private Data registerDate;
    private Data loginDate;
    private String role;
}
