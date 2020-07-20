package com.example.demo.utils;

import org.junit.jupiter.api.Test;

import java.util.Date;


class JwtHelperTest {

    @Test
    void createToken() {

        var date = new Date(System.currentTimeMillis() + 1000  * 60 * 60*  2 );
        String token = JwtHelper.createToken("111", date );

        System.out.println(token);

        String user = JwtHelper.parseToken(token);

        System.out.println(user);
    }
}