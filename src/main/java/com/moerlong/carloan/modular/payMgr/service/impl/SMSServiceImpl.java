package com.moerlong.carloan.modular.payMgr.service.impl;

import com.google.gson.Gson;
import com.moerlong.carloan.common.persistence.model.User;
import com.moerlong.carloan.modular.loan.entity.vo.SendMsgVo;
import com.moerlong.carloan.modular.payMgr.service.SMSService;

import com.moerlong.carloan.modular.system.dao.UserMgrDao;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SMSServiceImpl implements SMSService{
    private static final Logger LOG = LoggerFactory.getLogger(SMSServiceImpl.class);

    @Value("${system.sms.url}")
    private String sendSMSUrl;

    @Autowired
    private UserMgrDao managerDao;
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
        Gson gs = new  Gson();
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
    public boolean sendMsgSMS(List<SendMsgVo> mobileList, String businessCode, Map<String, Object> msgParam){
        RestTemplate restTemplate = new RestTemplate();

        for(SendMsgVo sendMsgVo:mobileList){
            Map mapReq = new HashMap();
            mapReq.put("mobile", sendMsgVo.getMobile());
            mapReq.put("businessType", businessCode);
            msgParam.put("operName",sendMsgVo.getOperName());
            msgParam.put("account",sendMsgVo.getAccount());
            mapReq.put("params", msgParam);

            String resp = "";
            try{
                resp = restTemplate.postForObject(sendSMSUrl+"sendMessage", mapReq, String.class);

                if(StringUtils.isEmpty(resp)) {
                    LOG.error("======调用短信接口失败====== {}", "无返回数据");
                    continue;
                }
                Gson gs = new  Gson();
                Map<String,String> map = gs.fromJson(resp, Map.class);
                String respStatus = (String)map.get("status");


                if(!"200".equals(respStatus)) {
                    LOG.error("======调用短信接口失败====== {}", resp);
                    continue;
                }

            }catch(Throwable e){
                LOG.error("======调用短信接口异常====== {}"+resp, e);
                return false;
            }
        }
        return  true;
    }
}
