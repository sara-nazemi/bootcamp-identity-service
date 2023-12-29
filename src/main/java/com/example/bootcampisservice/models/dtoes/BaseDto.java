package com.example.bootcampisservice.models.dtoes;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@ToString
public abstract class BaseDto {
    private static final long serialVersionUID = 1234567L;

    private Date insertDate;


    private Date lastModifiedDate;

    @PositiveOrZero
    private Integer version;
}
