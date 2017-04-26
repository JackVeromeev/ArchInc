package com.netcracker.veromeev.archinc.util.encryption;

import org.junit.Test;

public class SHA512EncryptionTest {
    @Test
    public void encrypt() throws Exception {
        System.out.println(
                SHA512Encryption.encrypt("admin"));
    }
}