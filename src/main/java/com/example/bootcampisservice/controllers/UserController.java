package com.example.bootcampisservice.controllers;

import com.example.bootcampisservice.models.dtoes.UserDto;
import com.example.bootcampisservice.models.entities.UserEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Transactional(readOnly = true)
public class UserController extends BaseController<UserEntity, UserDto,Long>{
}
