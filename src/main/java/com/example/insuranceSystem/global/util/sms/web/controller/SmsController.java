package com.example.insuranceSystem.global.util.sms.web.controller;


import com.example.insuranceSystem.global.util.sms.web.dto.SmsSendRes;
import com.example.insuranceSystem.global.util.sms.web.dto.SmsSendReq;
import com.example.insuranceSystem.global.util.sms.logic.SmsServiceImpl;
import com.example.insuranceSystem.global.web.response.Header;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("api/v1/sms")
@RequiredArgsConstructor
@Api(tags = "SMS 관련 API")
public class SmsController {

    private final SmsServiceImpl smsService;


    @ApiOperation(value = "문자발송", notes = "문자를 발송합니다.")
    @PostMapping("/send")
    public Header<SmsSendRes> send(@RequestBody SmsSendReq smsSendReq) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException, URISyntaxException {
        return Header.OK(SmsSendRes.builder()
                .value(smsService.send(smsSendReq))
                .build());
    }
}
