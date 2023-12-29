package com.example.bootcampisservice.services;

import com.example.bootcampisservice.models.entities.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserEntity,Long> implements UserService{
}
