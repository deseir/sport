package com.moerlong.carloan.modular.app.service.impl;

import com.google.gson.Gson;
import com.moerlong.carloan.modular.app.service.SMSService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SMSServiceCImpl implements SMSService {
    private static final Logger LOG = LoggerFactory.getLogger(SMSServiceCImpl.class);

    @Value("${system.sms.url}")
    private String sendSMSUrl;

    /**
     * 发送短信验证码
     * @param mobile        手机号
     * @param code          验证码（如果为空，则生成后返回）
     * @param smsType       短信类型  false--短信 true--语音
     * @return
     */
    @Override
    public String sendVerifySMS(String mobile, String code, boolean smsType) {
        RestTemplate restTemplate = new RestTemplate();
        Map mapReq = new HashMap();
        mapReq.put("mobile", mobile);
        mapReq.put("useVoice", smsType);
        mapReq.put("captcha", code);

        String resp = "";
        try{

            resp = restTemplate.postForObject(sendSMSUrl+"sendCaptcha?mobile={mobile}&captcha={captcha}&useVoice={useVoice}", null, String.class, mapReq);
        }catch(Throwable e){
            LOG.error("======调用短信接口异常====== {}", e);
            return null;
        }
        if(StringUtils.isEmpty(resp)) {
            LOG.error("======调用短信接口失败====== {}", "无返回数据");
            return null;
        }
        Gson gs = new Gson();
        Map<String,String> map = gs.fromJson(resp, Map.class);
        String respStatus = (String)map.get("status");


        if(!"200".equals(respStatus)) {
            LOG.error("======调用短信接口失败====== {}", resp);
            return null;
        }
        return  (String)map.get("data");
    }


    /**
     *  发送短信信息
     * @param mobileList      手机号列表
     * @param businessCode    业务代码
     * @param msgParam        短信数据
     * @return                true--成功
     */
    @Override
    public boolean sendMsgSMS(List<String> mobileList, String businessCode, Map<String, Object> msgParam){
        RestTemplate restTemplate = new RestTemplate();

        for(String mobile:mobileList){
            Map mapReq = new HashMap();
            mapReq.put("mobile", mobile);
            mapReq.put("businessType", businessCode);
            mapReq.put("params", msgParam);

            String resp = "";
            try{
                resp = restTemplate.postForObject(sendSMSUrl+"sendMessage", mapReq, String.class);

                if(StringUtils.isEmpty(resp)) {
                    LOG.error("======调用短信接口失败====== {}", "无返回数据");
                    return false;
                }
                Gson gs = new Gson();
                Map<String,String> map = gs.fromJson(resp, Map.class);
                String respStatus = (String)map.get("status");


                if(!"200".equals(respStatus)) {
                    LOG.error("======调用短信接口失败====== {}", resp);
                    return false;
                }

            }catch(Throwable e){
                LOG.error("======调用短信接口异常====== {}", e);
                return false;
            }
        }
        return  true;
    }
}
