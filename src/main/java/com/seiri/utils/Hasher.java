package com.seiri.utils;

import org.mindrot.jbcrypt.BCrypt;

public class Hasher {

    public static String hash(String unhashedPassword) {
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(unhashedPassword, salt);
    }

    public static boolean check(String unhashedPassword, String hashedPassword) {
        return BCrypt.checkpw(unhashedPassword, hashedPassword);
    }

}
