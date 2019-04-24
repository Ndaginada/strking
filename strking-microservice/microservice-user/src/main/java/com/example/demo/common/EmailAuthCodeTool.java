package com.example.demo.common;

import java.util.Random;

/**
 * Created by Kkai on 2018/3/23.
 */
public class EmailAuthCodeTool {
     static String CODE_SET ="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

     public  String getCode(){
         StringBuilder sb=new StringBuilder(4);
         for(int i=0;i<4;i++)
         {
             char ch= CODE_SET.charAt(new Random().nextInt(CODE_SET.length()));
             sb.append(ch);
         }

         return sb.toString();
     }


}
