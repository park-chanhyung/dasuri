package com.project.dasuri.member.entity;

import java.time.LocalDateTime;

public interface UserDetailEntity {
    String getRole();
    String getUserPwd();
    String getUserId();

    LocalDateTime getSuspensionExpiry();

}
