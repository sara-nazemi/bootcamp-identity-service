package com.example.bootcampisservice.controllers;

import com.example.bootcampisservice.coverters.UserConverter;
import com.example.bootcampisservice.coverters.UserConverterImpl;
import com.example.bootcampisservice.models.dtoes.UserDto;
import com.example.bootcampisservice.models.entities.UserEntity;
import com.example.bootcampisservice.sampleResponse.IdentityResponse;
import com.example.bootcampisservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/user")
@Transactional(readOnly = true)
public class UserController extends BaseController<UserEntity, UserDto,Long>{
    @Autowired
    private UserService userService;
    @Autowired
    private UserConverterImpl userConverter;
//    @PostMapping("/register")
//    public IdentityResponse<?> register(@RequestBody UserDto userDto){
//        UserDto userDto1=userConverter.convertDto(userService.save(userConverter.convertEntity(userDto)));
//        return IdentityResponse.builder() .message(resourceBundleUtil.getMessage("operation.successful.run", "fa"))
//                .code("operation.successful.run")
//                .date(new Date())
//                .hasError(false)
//                .data(userDto1)
//                .build();
    }

