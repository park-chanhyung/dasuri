package com.project.dasuri.member.dto;

import com.project.dasuri.member.entity.UserDetailEntity;
import com.project.dasuri.member.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
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
//        collection.add(new GrantedAuthority() {
//            @Override
//            public String getAuthority() {
//                System.out.println("@# CustomUserDetails-> Collectn / getAuthority getRole정보 =======>"+userEntity.getRole());
//                return userEntity.getRole();
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

    //계정 만료여부
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정 잠김 여부
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //계정 유효기간
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정 정지
    @Override
    public boolean isEnabled() {
        // 현재 시간이 정지 만료 시간보다 이후인 경우에만 계정을 활성화합니다.
        return userDetailEntity.getSuspensionExpiry() == null ||
                LocalDateTime.now().isAfter(userDetailEntity.getSuspensionExpiry());
//        return true;
    }
}
