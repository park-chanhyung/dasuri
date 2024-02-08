package com.project.dasuri.member.config;

import javax.security.sasl.AuthenticationException;

public class AccountDisabledException extends AuthenticationException {
    public AccountDisabledException(String msg) {
        super(msg);
    }
}
