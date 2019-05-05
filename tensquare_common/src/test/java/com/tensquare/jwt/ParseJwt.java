package com.tensquare.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ParseJwt {
    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJleHAiOjE1NTYyNDE4MDUsInN1YiI6IuadjuiNo-iNo-aIkeaYr-S9oOeIuSIsImlhdCI6MTU1NjI0MTc0NSwicm9sZXMiOiJhZG1pbiIsImxvZ28iOiJsb2dvLnBuZyJ9.6-tQVo2mzuhg0anyabfWaBiA5rduFWRTIB8K-Bx5_uo";
        Claims claims = Jwts.parser().setSigningKey("itcast").parseClaimsJws(token).getBody();
        System.out.println("id:"+claims.getId());
        System.out.println("subject:"+claims.getSubject());
        System.out.println("roles:"+claims.get("roles"));
        System.out.println("logo:"+claims.get("logo"));
        SimpleDateFormat sdf = new SimpleDateFormat(("yyy-MM-DD hh:mm:ss"));
        System.out.println("签发时间："+sdf.format(claims.getIssuedAt()));
        System.out.println("过期时间："+sdf.format(claims.getExpiration()));
        System.out.println("当前时间："+sdf.format(new Date()));
        System.out.println("IssuedAt:"+claims.getIssuedAt());
    }
}
