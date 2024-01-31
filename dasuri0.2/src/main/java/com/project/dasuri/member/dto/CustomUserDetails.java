package com.project.dasuri.member.dto;

import com.project.dasuri.member.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private UserEntity userEntity;
    public CustomUserDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                System.out.println("@# CustomUserDetails-> Collectn / getAuthority getRole정보 =======>"+userEntity.getRole());
                return userEntity.getRole();

            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        System.out.println("@# CustomUserDetails-> Collectn / getAuthority getPassword정보 =======>"+userEntity.getUserPwd());
        return userEntity.getUserPwd();
    }

    @Override
    public String getUsername() {
        System.out.println("@# CustomUserDetails-> Collectn / getAuthority getUserId정보 =======>"+userEntity.getUserId());
        return userEntity.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
