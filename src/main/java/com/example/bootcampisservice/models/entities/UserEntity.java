package com.example.bootcampisservice.models.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="USER")
public class UserEntity extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "FAMILY")
    private String family;
    @Column(name = "PHONENUMBER", unique = true)
    private String phoneNumber;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "NATIONAL_ID", unique = true)
    private String nationalId;
    @Column(name = "EMAIL", unique = true)
    private String email;
    @Column(name = "STATUS")
    private Boolean status;
    @Column(name = "REGISTER_DATE")
    private Date registerDate;
    @Column(name = "LOGIN_DATE")
    private Date loginDate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.getPhoneNumber();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
