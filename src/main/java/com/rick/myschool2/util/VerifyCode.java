package com.rick.myschool2.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class VerifyCode {

    //基本属性
    private static int WIDTH=90;
    private static int HEIGHT=35;
    private static int FONT_SIZE=20;
    private static char[] vCode;
    private static BufferedImage vImage;



    /**
     * 生成验证码
     */
    public static BufferedImage createVerifyCode(){

        //创建对象
        BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_BGR);
        //帮助绘画
        Graphics graphics = image.getGraphics();
        //创建验证码字符
        vCode=createCheckCode(4);
        //验证码背景
        drawBackground(graphics);
        //在验证码图像上绘制验证码文本
        drawVCode(graphics,vCode);
        //释放资源，如果不手动释放这些资源，可能会导致内存泄漏或图形资源的持续占用，从而影响应用程序的性能和稳定性。
        graphics.dispose();

        return image;
    }

    public static String getVCode(){
        return vCode.toString();
    }


    /**
     * 验证码字符
     * @param codeLength
     * @return
     */
    private static char[]  createCheckCode(int codeLength){

        String randomChars="0123456789abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        char[] codes=new char[codeLength];
        for (int i = 0; i < codeLength; i++) {
            int num= (int) (Math.random()*(10+26*2));
            codes[i] = randomChars.charAt(num);
        }
        return codes;
    }

    /**
     * 验证码背景
     * @param g
     */
    private static void drawBackground(Graphics g) {
        g.setColor(Color.white);
        //用指定颜色填充一个矩形区域
        g.fillRect(0,0,WIDTH,HEIGHT);

        //绘制干扰点
        for (int i = 0; i < 200; i++) {
            int x = (int) (Math.random() * WIDTH);
            int y = (int) (Math.random() * HEIGHT);
            g.setColor(getRandomColor());
            //在指定位置绘制一个椭圆形
            g.drawOval(x, y, 1, 1);

        }
    }

    /**
     * 验证码颜色
     * @return
     */
    private static Color getRandomColor() {
        Random ran = new Random();
        return new Color(ran.nextInt(220), ran.nextInt(220), ran.nextInt(220));
    }

    /**
     * 绘制验证码
     * @param g
     * @param rands
     */
    private static void drawVCode(Graphics g, char[] rands) {
        g.setFont(new Font("Console", Font.BOLD, FONT_SIZE));

        for (int i = 0; i < rands.length; i++) {
            g.setColor(getRandomColor());
            g.drawString("" + rands[i], i * FONT_SIZE + 10, 25);
        }
    }

}
