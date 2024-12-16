package com.backend.utils;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class Algorithms {
    // AES Encryption
    public void encryptAES(Path inputFile, Path outputFile, String key) throws Exception {
    SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] fileBytes = Files.readAllBytes(inputFile);
        byte[] encryptedBytes = cipher.doFinal(fileBytes);

        Files.write(outputFile, encryptedBytes);
    }

    // AES Decryption
    public void decryptAES(Path inputFile, Path outputFile, String key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] fileBytes = Files.readAllBytes(inputFile);
        byte[] decryptedBytes = cipher.doFinal(fileBytes);

        Files.write(outputFile, decryptedBytes);
    }

    // DES Encryption
    public void encryptDES(Path inputFile, Path outputFile, String key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] fileBytes = Files.readAllBytes(inputFile);
        byte[] encryptedBytes = cipher.doFinal(fileBytes);

        Files.write(outputFile, encryptedBytes);
    }

    // DES Decryption
    public void decryptDES(Path inputFile, Path outputFile, String key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] fileBytes = Files.readAllBytes(inputFile);
        byte[] decryptedBytes = cipher.doFinal(fileBytes);

        Files.write(outputFile, decryptedBytes);
    }

    // Blowfish Encryption
    public void encryptBlowfish(Path inputFile, Path outputFile, String key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] fileBytes = Files.readAllBytes(inputFile);
        byte[] encryptedBytes = cipher.doFinal(fileBytes);

        Files.write(outputFile, encryptedBytes);
    }

    // Blowfish Decryption
    public void decryptBlowfish(Path inputFile, Path outputFile, String key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key.getBytes(), "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] fileBytes = Files.readAllBytes(inputFile);
        byte[] decryptedBytes = cipher.doFinal(fileBytes);

        Files.write(outputFile, decryptedBytes);
    }
}
