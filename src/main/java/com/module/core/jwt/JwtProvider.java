package com.module.core.jwt;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Duration;
import java.util.Date;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;


@Component
public class JwtProvider {

    private static long accessTokenValidMilSecond = Duration.ofMinutes(30).toMillis();
    private static long refreshTokenValidMilSecond = Duration.ofDays(7).toMillis();
    private String JWT_SECRET = "jwtSecretKey";

    public JwtTokenDto generateAccessToken(String name, Long id) {
        return generateToken(name, id, accessTokenValidMilSecond);
    }

    public JwtTokenDto generateRefreshToken(String name, Long id) {
        return generateToken(name, id, refreshTokenValidMilSecond);
    }

    public JwtDto generateJwt(String name, Long id) {
        return new JwtDto(generateAccessToken(name, id), generateRefreshToken(name, id));
    }

    private JwtTokenDto generateToken(String name, Long id, Long tokenValidMilSecond) {
        Date now = new Date();

        long expiredIn = now.getTime() + tokenValidMilSecond;
        String token = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .claim(name, id)
                .setIssuedAt(now)
                .setExpiration(new Date(expiredIn))
                .signWith(HS256, JWT_SECRET)
                .compact();
        return new JwtTokenDto(token, expiredIn);
    }

    public Claims parseJwtToken(String authorizationHeader) {

        validationAuthorizationHeader(authorizationHeader); // (1)
        String token = extractToken(authorizationHeader); // (2)

        return Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    private void validationAuthorizationHeader(String header) {
        if (header == null || !header.startsWith("Bearer ")) {
            throw new JwtException("Jwt 인증 형식이 맞지 않습니다.");
        }
    }

    private String extractToken(String authorizationHeader) {
        return authorizationHeader.substring("Bearer ".length());
    }

    @ExceptionHandler({UnsupportedJwtException.class, MalformedJwtException.class, SignatureException.class, IllegalArgumentException.class})
    public void HandleJwtException(Exception exception) {
        throw new JwtException("JWT 토큰에 문제가 있습니다.");
    }

    @ExceptionHandler({ExpiredJwtException.class})
    public void HandleJwtExpiredException(ExpiredJwtException expiredJwtException) {
        throw new JwtException("JWT 토큰이 만료되었습니다.");
    }
}
