package com.project.dasuri.member.service;

import com.project.dasuri.member.config.AccountDisabledException;
import com.project.dasuri.member.config.CustomAuthenticationFailureHandler;
import com.project.dasuri.member.dto.CustomUserDetails;
import com.project.dasuri.member.entity.ProEntity;
import com.project.dasuri.member.entity.UserDetailEntity;
import com.project.dasuri.member.entity.UserEntity;
import com.project.dasuri.member.repository.ProRepository;
import com.project.dasuri.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ProRepository proRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetailEntity userDetailEntity = userRepository.findByUserId(username);
        if (userDetailEntity == null) {
            userDetailEntity = proRepository.findByProId(username);
        }
        if (userDetailEntity == null) {
            throw new UsernameNotFoundException(username+" 사용자를 찾을 수 없습니다.");
        }

        CustomUserDetails customUserDetails = new CustomUserDetails(userDetailEntity);

        if (!customUserDetails.isEnabled()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd a hh:mm");
            String formattedDate = userDetailEntity.getSuspensionExpiry().format(formatter);
            throw new DisabledException("만료 일자: " + formattedDate);

//            throw new DisabledException("만료 일자: "+userDetailEntity.getSuspensionExpiry().toString());
        }
        return customUserDetails;
    }
}
