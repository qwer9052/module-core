package com.module.core.jwt;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class JwtTokenDto {

    private String token;
    private Long expiredIn;

    public JwtTokenDto(String token, Long expiredIn) {
        this.token = token;
        this.expiredIn = expiredIn;
    }
}
