package com.springboot.school.bestchoice.utils;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import org.apache.commons.lang3.CharSet;

import java.nio.charset.Charset;

public class HashUtils {

    private static HashFunction FUNCTION = Hashing.md5();

    private static final String SALT = "omg";

    public static String encrypt(String password){
        HashCode hashCode = FUNCTION.hashString(password+SALT, Charset.forName("utf-8"));
        return hashCode.toString();
    }
}
