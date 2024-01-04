package com.example.bootcampisservice.repositories;

import com.example.bootcampisservice.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    @Query("from UserEntity e where e.phoneNumber = :username")
    UserEntity loadByUsername(@Param("username") String username);
}
