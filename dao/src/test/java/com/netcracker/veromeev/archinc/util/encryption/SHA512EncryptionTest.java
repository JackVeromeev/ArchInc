package com.netcracker.veromeev.archinc.util.encryption;

import org.junit.Test;

public class SHA512EncryptionTest {
    @Test
    public void encrypt() throws Exception {
        System.out.println(
                SHA512Encryption.encrypt("1234", 5678));
        System.out.println(
                SHA512Encryption.encrypt("5678", 1234));
        System.out.println(
                SHA512Encryption.encrypt("5678", 1234).length());
    }
}