package com.example.bootcampisservice.sampleResponse;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class IdentityResponse<T> {
    private String message;
    private String code;
    private Date date;
    private Boolean hasError;
    private T data;
}
