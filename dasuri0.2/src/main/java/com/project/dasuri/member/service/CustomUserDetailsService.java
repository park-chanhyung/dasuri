package com.project.dasuri.member.service;

import com.project.dasuri.member.dto.CustomUserDetails;
import com.project.dasuri.member.entity.UserEntity;
import com.project.dasuri.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userData = userRepository.findByUserId(username);
        System.out.println("@#@# username(ID) ===================> " + userData.getUserId());
        System.out.println("@#@# userData.비밀번호 = " + userData.getUserPwd());

        if (userData != null) {
            System.out.println("@#@#@# userDATA 정보가 있습니다. ============================");
            return new CustomUserDetails(userData);
        }
        return null;
    }
}
