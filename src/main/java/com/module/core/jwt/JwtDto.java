package com.module.core.jwt;

import lombok.Builder;
import lombok.Getter;

@Getter
public class JwtDto {
	private JwtTokenDto accessToken;
	private JwtTokenDto refreshToken;

	public JwtDto(JwtTokenDto accessToken, JwtTokenDto refreshToken){
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
}
