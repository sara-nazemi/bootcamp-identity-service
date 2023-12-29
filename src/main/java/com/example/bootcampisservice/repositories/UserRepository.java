package com.example.bootcampisservice.repositories;

import com.example.bootcampisservice.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
}
