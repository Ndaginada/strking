package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Kkai on 2017/11/4.
 */
@RestController
public class DemoController {

    @RequestMapping("hello")
    public String hello(){
        return "hello World!";
    }
}
