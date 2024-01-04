package com.example.bootcampisservice.security;

import com.example.bootcampisservice.models.entities.UserEntity;
import com.example.bootcampisservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    @Lazy
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userService.loadByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }

        JwtBankUserDetailsDto jwtBankUserDetailsDto = new JwtBankUserDetailsDto();
        jwtBankUserDetailsDto.setUsername(user.getPhoneNumber());
        jwtBankUserDetailsDto.setPassword(user.getPassword());
        jwtBankUserDetailsDto.setTelephone(user.getPhoneNumber());
        jwtBankUserDetailsDto.setEmail(user.getEmail());

        //role
        ArrayList<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority("USER"));
        jwtBankUserDetailsDto.setAuthorities(simpleGrantedAuthorities);

        return jwtBankUserDetailsDto;
    }

}
