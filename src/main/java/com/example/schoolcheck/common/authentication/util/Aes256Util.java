package com.example.schoolcheck.common.authentication.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class Aes256Util {

    /**
     * AES256 대칭키 암호화 알고리즘
     * KEY는 16bit로 항상 고정되어야함
     * 추가로 암호환 된 text 값을 base64로 한번 더 해쉬과정을 거침(소금)
     * @param text
     * @return
     */

    private static String KEY = "ICanSeeOnlyYello";
    private static final String IV = KEY.substring(0,16);
    public static String alg = "AES/CBC/PKCS5Padding";
    public static final String AES = "AES";


    /**암호화
     * @param text
     * @return
     */
    public static String encrypt(String text){
        try{
            Cipher cipher = Cipher.getInstance(alg);
            SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), AES);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.ENCRYPT_MODE,keySpec,ivParameterSpec);
            byte[] encrypted = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));

            return Base64.encodeBase64URLSafeString(encrypted);
        } catch (NoSuchPaddingException e) {
            log.error("잘못된 padding 알고리즘을 사용. error: {}", e.getMessage());
            throw new IllegalArgumentException(e);
        } catch (NoSuchAlgorithmException e) {
            log.error("잘못된 암호화 알고리즘을 사용. error: {}", e.getMessage());
            throw new IllegalArgumentException(e);
        } catch (InvalidAlgorithmParameterException e) {
            log.error("잘못된 알고리즘 파라미터 전송. error: {}", e.getMessage());
            throw new IllegalArgumentException(e);
        } catch (InvalidKeyException e) {
            log.error("무효한 KEY 사용. error: {}", e.getMessage());
            throw new IllegalArgumentException(e);
        } catch (IllegalBlockSizeException e) {
            log.error("크기가 맞지않은 블록. error: {}", e.getMessage());
            throw new IllegalArgumentException(e);
        } catch (BadPaddingException e) {
            log.error("잘못된 패딩 사용 error: {}", e.getMessage());
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 복호화
     * @param cipherText
     * @return
     */
    public static String decrypt(String cipherText){
        try{
            Cipher cipher = Cipher.getInstance(alg);
            SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), AES);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);

            byte[] decodedBytes = Base64.decodeBase64(cipherText);
            byte[] decrypted = cipher.doFinal(decodedBytes);
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (NoSuchPaddingException e) {
            log.error("잘못된 padding 알고리즘을 사용. error: {}", e.getMessage());
            throw new IllegalArgumentException(e);
        } catch (NoSuchAlgorithmException e) {
            log.error("잘못된 암호화 알고리즘을 사용. error: {}", e.getMessage());
            throw new IllegalArgumentException(e);
        } catch (InvalidAlgorithmParameterException e) {
            log.error("잘못된 알고리즘 파라미터 전송. error: {}", e.getMessage());
            throw new IllegalArgumentException(e);
        } catch (InvalidKeyException e) {
            log.error("무효한 KEY 사용. error: {}", e.getMessage());
            throw new IllegalArgumentException(e);
        } catch (IllegalBlockSizeException e) {
            log.error("크기가 맞지않은 블록. error: {}", e.getMessage());
            throw new IllegalArgumentException(e);
        } catch (BadPaddingException e) {
            log.error("잘못된 패딩 사용 error: {}", e.getMessage());
            throw new IllegalArgumentException(e);
        }
    }
}
