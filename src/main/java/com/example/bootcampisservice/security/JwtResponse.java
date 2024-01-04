package com.example.bootcampisservice.security;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class JwtResponse {

    private final String jwtToken;

}
