package com.neicake.cafeapp.config;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder extends BCryptPasswordEncoder {

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        System.out.println(encoder.encode("theremustalwaysbealichking"));

    }
}
