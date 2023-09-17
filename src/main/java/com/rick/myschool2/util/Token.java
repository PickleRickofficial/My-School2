package com.rick.myschool2.util;


import com.auth0.jwt.JWT;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.rick.myschool2.domain.LoginForm;

import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Token {

    /**
     * 生成token
     */
    //密钥
    private static final String SECRET = "123456";//自己定


   public static String generateToken(LoginForm loginForm){
       // 获取当前的时间戳
//       Instant now = Instant.now();
//       Instant expireTime = now.plus(Duration.ofHours(1));
//       System.out.println(now);

       Calendar instance = Calendar.getInstance();
       instance.add(Calendar.SECOND,3600);
//       System.out.println(instance.getCalendarType());
//       System.out.println(instance);
       Date expireDate = instance.getTime();//转化为日期
//       System.out.println("expireDate->>"+expireDate);


       Map<String, Object> map = new HashMap<>();
       map.put("alg", "HS256");
       map.put("typ", "JWT");

       String token = JWT.create()
               .withHeader(map)//请求头
               .withClaim("userName", loginForm.getUserName())// 设置自定义声明
               .withClaim("userType", loginForm.getUserType())
               .withSubject("测试token")// 设置主题
               .withIssuedAt(new Date())//签名时间
               .withExpiresAt(expireDate)//过期时间
               .sign(Algorithm.HMAC256(SECRET));// 使用 HMAC256 签名算法进行签名
       return token;

   }


    /**
     * 用于验证并解析 JWT 令牌的，它会对令牌进行签名验证，确保令牌未被篡改。
     * 只有在签名验证通过后，才会解析令牌并提取其中的数据。它返回一个 DecodedJWT 对象，其中包含了解析后的令牌数据。
     */
    public static Map<String, Claim> verifyToken(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT verified = null;
        try {
            //令牌中通常包含了一个过期时间（exp），验证器会检查当前时间是否在过期时间之前。如果令牌已过期，验证将失败。
            verified = verifier.verify(token);
            /*其它验证：检查用户的权限或角色等
             例如：JWT.require(Algorithm.HMAC256(SECRET)).withClaim("claimName", claimValue).build().verify(token) */
        } catch (JWTVerificationException e) {
            throw new RuntimeException("凭证过期，请重新登录");
        }
        System.out.println("1\n"+verified.getPayload());//用户的相关信息,JSON 对象,需要json转换
        System.out.println("2\n"+verified.getToken());//头部、有效载荷和签名
        System.out.println("3\n"+verified.getSignature());//签名部分
        System.out.println("4\n"+verified.getHeader());//算法、令牌类型
        return verified.getClaims();

    }



    /**
     * 解码令牌，而不进行签名验证，因此不会抛出异常
     */
    public static Map<String, Claim> TkGetUsername(String token) {

        DecodedJWT decodedJWT = JWT.decode(token);
        Claim username = decodedJWT.getClaims().get("userName");
        Claim userType = decodedJWT.getClaims().get("userType");
        System.out.println("username-->"+username+"\nuserType-->"+userType);
        return decodedJWT.getClaims();

    }


    /**
     * 测试方法
     * @param args
     */
    public static void main(String[] args) {
        try {
            LoginForm loginForm = new LoginForm("张三","123456","456",5);
            String token = Token.generateToken(loginForm);
            System.out.println("verifyToken:"+Token.verifyToken(token));
            System.out.println("parseToken:"+Token.TkGetUsername(token));

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
