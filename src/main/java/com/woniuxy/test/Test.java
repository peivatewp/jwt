package com.woniuxy.test;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

public class Test {
    private static final Long EXPIRE_TIME=60*60*60*1000L;

    public static void main(String[] args) throws UnsupportedEncodingException {
//        Calendar instance = Calendar.getInstance();
//        instance.add(Calendar.SECOND,300);
//        System.out.println(instance.getTime());
        //设置playload信息
        String token = JWT.create().withClaim("name", "tom")
        .withClaim("age", 28)
                //设置过期时间
                .withExpiresAt(new Date(System.currentTimeMillis()+EXPIRE_TIME))
                //设置加密算法
                .sign(Algorithm.HMAC256("wrwefwef"));
        System.out.println(token);

        Verification verification = JWT.require(Algorithm.HMAC256("wrwefwef"));
        JWTVerifier verifier = verification.build();
        DecodedJWT verify = verifier.verify(token);
        System.out.println(verify.getClaim("name").asString());
        System.out.println(verify.getClaim("age").asInt());
    }
}
