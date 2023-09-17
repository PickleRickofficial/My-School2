package com.rick.myschool2.controller;


import com.rick.myschool2.domain.LoginForm;
import com.rick.myschool2.util.Result;
import com.rick.myschool2.util.VerifyCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.coyote.Response;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping("/system")
@Api(tags = "系统控制类")
public class SystemController {


    @ApiOperation(value = "登录方法")
    @PostMapping(value = "/login",consumes = "application/json")
    public String login(
           /* @ApiParam (value = "username") @RequestParam("username")  String username,
            @ApiParam (value = "password") @RequestParam("username")  String password,*/
            @ApiParam(value = "json请求体") @RequestBody LoginForm loginForm,
            HttpServletRequest request,HttpServletResponse response
    ){
//        System.out.println("username:"+username+"\npassword:"+password);
////        //服务器cookie
////        Session.Cookie cookie = new Session.Cookie();
//        //客户端cookie
//        Cookie cookie = new Cookie("username","小明");
//        cookie.setMaxAge(3600);
//        cookie.setPath("/");
//        cookie.setHttpOnly(true);
//        response.addCookie(cookie);
//
//        Cookie[] cookies = request.getCookies();
//        if (cookies!=null)
//        for (int i = 0; i < cookies.length; i++) {
//            System.out.println("cookieName"+cookies[i].getName()+"\ncookiePassword:"+cookies[i].getValue());
//        }
//        HttpSession session = request.getSession();
//
//        session.setAttribute("username",username);
//        session.setAttribute("password",password);
//        session.setMaxInactiveInterval(60*30);
//        if (session != null) {
//            String username2 = (String) session.getAttribute("username");
//            String password2 = (String) session.getAttribute("password");
//            System.out.println("sessionUsername="+session.getAttribute("username"));
//        }
        System.out.println(loginForm);
        //验证码判断
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute("verifyCode");



        //用户类型判断

        return "200";
    }

    @ApiOperation(value = "设置验证码图片")
    @GetMapping("/getVerifyCode")
    public void setVerifyCode(HttpServletRequest Request, HttpServletResponse Response) throws IOException {
        //获取验证码图片
        BufferedImage verifyCode = VerifyCode.createVerifyCode();
        // 将验证码文本放入session域,登陆时再重新获取
        HttpSession session = Request.getSession();
        session.setAttribute("verifyCode",VerifyCode.getVCode());
        //验证码图片响应给浏览器
        ServletOutputStream outputStream = Response.getOutputStream();
        ImageIO.write(verifyCode,"png",outputStream);
        outputStream.close();

    }

}
