package com.example.bootcampisservice.services;

import com.example.bootcampisservice.exception.IdentityServiceException;
import com.example.bootcampisservice.models.entities.UserEntity;
import com.example.bootcampisservice.repositories.UserRepository;
import com.example.bootcampisservice.security.JwtTokenUtil;
import com.example.bootcampisservice.services.serviceCall.WalletServiceCallFeignImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserEntity,Long> implements UserService{

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WalletServiceCallFeignImpl walletServiceCall;

    @Override
    protected JpaRepository<UserEntity, Long> getRepository() {
       return userRepository;
    }

    //@CacheEvict(cacheNames = "UserServiceImpl_findAll")
    @Override
    public UserEntity save(UserEntity entity) {
        if(entity.getPhoneNumber() == null){
            throw new IdentityServiceException("phone-number-null");
        }
        entity.setPassword(bcryptEncoder.encode(entity.getPassword()));
        walletServiceCall.createWallet(jwtTokenUtil.generateToken(entity));
        return super.save(entity);
    }

    //@Cacheable(cacheNames = "UserServiceImpl_findAll")
    @Override
    public List<UserEntity> findAll() {
        return super.findAll();
    }

    @Override
    public UserEntity loadByUsername(String username) {
        return userRepository.loadByUsername(username);
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("sara"));
    }
}
