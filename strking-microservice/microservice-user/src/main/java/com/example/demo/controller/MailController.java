package com.example.demo.controller;

import com.example.demo.common.EmailAuthCodeTool;
import com.example.demo.common.RespMsg;
import com.example.demo.common.RespStatus;
import com.example.demo.entity.User;
import com.example.demo.entity.UserIdPara;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;
import java.util.Random;

/**
 * Created by Kkai on 2018/3/21.
 */
@RestController
@RequestMapping("mail")
public class MailController {
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    UserService userService;

    @RequestMapping(value = "sendCode")
    public RespMsg sendCode(@RequestBody UserIdPara userId) {
        try {
            final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            EmailAuthCodeTool emailAuthCodeTool = new EmailAuthCodeTool();
            String code = emailAuthCodeTool.getCode();

            message.setFrom("strking_admin@126.com");
            User user = userService.getUserById(userId.getUserid());
            String userEmail = user.getEmail();
            message.setTo(userEmail);
            message.setSubject("Strking_验证码");
            message.setText("验证码为："+ code);
            this.mailSender.send(mimeMessage);

            RespMsg respMsg = new RespMsg();
            respMsg.setData(code);
            return respMsg;
        } catch (Exception ex) {
            RespMsg respMsg = new RespMsg();
            respMsg.setCode(RespStatus.INTERNAL_SERVER_ERROR.code());
            return respMsg;
        }
    }
}
