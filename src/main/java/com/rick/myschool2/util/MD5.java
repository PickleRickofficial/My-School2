package com.rick.myschool2.util;

import sun.security.rsa.RSASignature;

import java.security.*;
import java.util.Base64;

public class MD5 {

    /**
     * MD5withRSA 通常用于数字签名，而不是用于登录验证。它的主要用途是确保消息的完整性和来源认证，而不是验证用户的身份。
     * 在实际应用中，推荐使用更安全的哈希算法，如 SHA-256，以提高安全性
     */
//    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
//
////        sun.security.provider.MD5 md5 = new sun.security.provider.MD5();不建议使用
//        RSASignature.MD5withRSA md5withRSA = new RSASignature.MD5withRSA();
//
//        // 生成 RSA 密钥对
//        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//        keyPairGenerator.initialize(2048);
//        KeyPair keyPair = keyPairGenerator.generateKeyPair();
//        PublicKey publicKey = keyPair.getPublic();
//        PrivateKey privateKey = keyPair.getPrivate();
//
//        // 创建一个数字签名对象，使用私钥进行签名
//        Signature signature = Signature.getInstance("MD5withRSA");
//        signature.initSign(privateKey);
//
//        // 要签名的消息
//        String message = "Hello, RSA with MD5!";
//        byte[] messageBytes = message.getBytes();
//
//        // 更新要签名的消息
//        signature.update(messageBytes);
//
//        // 对消息进行签名
//        byte[] digitalSignature = signature.sign();
//
//        // 打印签名结果
//        System.out.println("Digital Signature: " + Base64.getEncoder().encodeToString(digitalSignature));
//
//        // 验证签名
//        signature.initVerify(publicKey);
//        signature.update(messageBytes);
//        boolean verified = signature.verify(digitalSignature);
//        System.out.println("Signature Verified: " + verified);
//
//
//    }

}
