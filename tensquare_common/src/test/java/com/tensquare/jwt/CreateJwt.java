package com.tensquare.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class CreateJwt {
    public static void main(String[] args) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("666")
                .setSubject("李荣荣我是你爹")
                .setIssuedAt(new Date())
                .claim("roles","admin")
                .claim("logo","logo.png")
                .signWith(SignatureAlgorithm.HS256,"itcast");
        System.out.println(jwtBuilder.compact());
    }
}
