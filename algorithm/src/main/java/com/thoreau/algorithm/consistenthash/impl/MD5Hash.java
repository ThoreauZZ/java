package com.thoreau.algorithm.consistenthash.impl;

import com.thoreau.algorithm.consistenthash.IHash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Hash implements IHash {
    @Override
    public long getHash(String key) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            md5.update(key.getBytes());
            byte[] digest = md5.digest();
            long h = 0;
            for (int i = 0; i < 4; i++) {
                h <<= 8;
                h |= ((int) digest[i]) & 0xFF;
            }
            return h;
        } catch (NoSuchAlgorithmException e) {
            return 0;
        }
    }
}
