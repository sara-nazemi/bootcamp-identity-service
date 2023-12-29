package com.example.bootcampisservice.coverters;

import com.example.bootcampisservice.models.dtoes.UserDto;
import com.example.bootcampisservice.models.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserConverter extends BaseConverter<UserEntity, UserDto> {
}
