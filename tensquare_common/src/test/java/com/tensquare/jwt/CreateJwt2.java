package com.tensquare.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class CreateJwt2 {
    public static void main(String[] args) {
        //设置过期时间为30秒
        //当前时间
        long now = System.currentTimeMillis();
        //设置过期时间为30秒
        long exp = now + 1000*60;
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("666")
                .setExpiration(new Date(exp))
                .setSubject("李荣荣我是你爹")
                .setIssuedAt(new Date())
                .claim("roles","admin")
                .claim("logo","logo.png")
                .signWith(SignatureAlgorithm.HS256,"itcast");
        System.out.println(jwtBuilder.compact());
    }
}
