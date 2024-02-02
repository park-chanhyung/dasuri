package com.project.dasuri.member.dto;

import com.project.dasuri.member.entity.UserDetailEntity;
import com.project.dasuri.member.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final UserDetailEntity userDetailEntity;
    private UserEntity userEntity;

    public CustomUserDetails(UserDetailEntity userDetailEntity) {
        this.userDetailEntity = userDetailEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

//        Collection<GrantedAuthority> collection = new ArrayList<>();
//
//        collection.add(new GrantedAuthority() {
//            @Override
//            public String getAuthority() {
//                System.out.println("@# CustomUserDetails-> Collectn / getAuthority getRole정보 =======>"+userEntity.getRole());
//                return userEntity.getRole();
//
//            }
//        });
//        return collection;
        return Collections.singleton(() -> userDetailEntity.getRole());
    }

    @Override
    public String getPassword() {
        System.out.println("@# CustomUserDetails-> Collectn / getAuthority getPassword정보 =======>"+userDetailEntity.getUserPwd());
        return userDetailEntity.getUserPwd();
    }

    @Override
    public String getUsername() {
        System.out.println("@# CustomUserDetails-> Collectn / getAuthority getUserId정보 =======>"+userDetailEntity.getUserId());
        return userDetailEntity.getUserId();
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
