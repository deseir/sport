package com.moerlong.carloan.modular.app.utils;

import com.alibaba.fastjson.JSON;
import com.moerlong.carloan.common.vo.ErrorCode;
import com.moerlong.carloan.common.vo.ResultVO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserAuthTools {
    private static final Logger LOG = LoggerFactory.getLogger(UserAuthTools.class);

    private static final String TOKEN_LAST_TAG = "}";

    private static final int CONTENT_LENGTH = 128;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static StringRedisTemplate srt;

    @SuppressWarnings("static-access")
	@PostConstruct
    public void init(){
        this.srt = stringRedisTemplate;
    }

    /**
     * 根据token获取用户信息
     *
     * @param tokenId
     * @return UserTokenInfoModel 异常为null
     */

    public static UserTokenInfoModel getUserInfoByTokenId(String tokenId) {
        if (StringUtils.isBlank(tokenId)) {
            return null;
        }
        UserTokenInfoModel userInfoModel = null;
        try {
            String desToken = tokenId.substring(0, CONTENT_LENGTH);
            String digest = new String(tokenId.substring(CONTENT_LENGTH));
            String userInfo4Json = AESUtil.decrypt(desToken, null);
            //LOG.info(">>>tokenId:{}====userInfo4Json:{}" + userInfo4Json);
            userInfo4Json = userInfo4Json.substring(0, userInfo4Json.lastIndexOf(TOKEN_LAST_TAG) + 1);
            String tokendig = new String(SHAUtil.digest(userInfo4Json).getBytes("UTF-8"));
            //LOG.info("[>>>getUserInfoByTokenId<<<][digest:{}][解密签名:{}][userInfo4Json:{}]", digest, tokendig, userInfo4Json);
            if (digest.equals(tokendig)) {
                userInfoModel = (UserTokenInfoModel) JSON.parseObject(userInfo4Json, UserTokenInfoModel.class);
            } else {
                LOG.error("[<<<getUserInfoByTokenId<<<][解密验签错误][digest:{}][解密签名:{}][tokenId:{}]", digest, tokendig,
                        tokenId);
            }
        } catch (Exception e) {
            LOG.error("token 解析用户信息异常", e);
        }
        return userInfoModel;
    }


    public static ResultVO<UserTokenInfoModel> getUserInfo(String tokenId){

        UserTokenInfoModel tokenModel = UserAuthTools.getUserInfoByTokenId(tokenId);
        if (tokenModel == null) {
            LOG.error("[>>>getUserInfo] getUserInfo token={}", tokenId);
            return ResultVO.build(ErrorCode.TOKEN_TIMEOUT_ERROR);
        }
        //LOG.info("[>>>getUserInfo] 解析用户信息为:{}", JSON.toJSONString(tokenModel));

        if (tokenModel.getExpiredTime() <= System.currentTimeMillis()) {
            LOG.error("[>>>getUserInfo] getUserInfo token={}", tokenId);
            return ResultVO.build(ErrorCode.TOKEN_TIMEOUT_ERROR);
        }

        if(!srt.hasKey(TokenGenerator.REDIS_EXPIREDTOKEN_KEY + tokenModel.getId() + tokenModel.getExpiredTime())){
            LOG.error("[>>>getUserInfo] redis not exist token={}", tokenId);
            return ResultVO.build(ErrorCode.TOKEN_TIMEOUT_ERROR);
        }

        return ResultVO.build(ErrorCode.SUCCESS, tokenModel);

    }
}
