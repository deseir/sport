package com.moerlong.carloan.modular.paybackMgr.service.impl;

import com.alibaba.fastjson.JSON;
import com.moerlong.carloan.modular.paybackMgr.service.PayCenterService;
import com.moerlong.carloan.modular.paybackMgr.txservice.vo.PayCenterPayResultVO;
import com.moerlong.carloan.common.vo.ErrorCode;
import com.moerlong.carloan.common.vo.ResultVO;
import com.moerlong.carloan.util.HttpClient;
import com.moerlong.carloan.util.HttpResultVO;
import com.moerlong.carloan.util.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class PayCenterServiceImpl implements PayCenterService{

    private static final Logger LOG = LoggerFactory.getLogger(PayCenterServiceImpl.class);


    @Value("${system.paycenter.channleKey}")
    private String channelKey;

    /**代扣请求路径*/
    @Value("${system.paycenter.costUrl}")
    private String costUrl;

    /**代扣查询路径*/
    @Value("${system.paycenter.singleCostQueryUrl}")
    private String singleCostQueryUrl;

    /**代付请求路径*/
    @Value("${system.paycenter.payUrl}")
    private String payUrl;

    /**代付查询路径*/
    @Value("${system.paycenter.singlePayQueryUrl}")
    private String singlePayQueryUrl;

    /**前台手续费代扣请求路径*/
    @Value("${system.paycenter.mrlcostUrl}")
    private String mrlcostUrl;

    /**前台手续费代扣查询路径*/
    @Value("${system.paycenter.mrlcostQueryUrl}")
    private String mrlcostQueryUrl;

    @Resource
    private HttpClient httpClient;

    @Override
    public ResultVO<PayCenterPayResultVO> deductMoney(String batchNo, Long uid, String bankCardNo, String userName, String bindingMobile, String idNo, String openProvince, String openCity, String bankBranch, String idType, String amount, String bankName) {

        LOG.info("[===>>>划扣交易接口，请求参数:{},{},{},{},{},{},{},{},{},{},{}]======", batchNo, uid, bankCardNo, userName,
                bindingMobile, idNo, openProvince, openCity, bankBranch, idType, amount);

        Map<String, String> paramMap = new HashMap<>();
        Long timestamp = System.currentTimeMillis();
        String msgSign = MD5Utils.MD5Encode(timestamp + channelKey, "UTF-8");
        paramMap.put("interType", "3000");
        paramMap.put("terminalId", "10011");
        paramMap.put("timestamp", String.valueOf(timestamp));
        paramMap.put("msgSign", msgSign);

        Map<String, String> transMap = new HashMap<>();
        transMap.put("batchNo", batchNo);
        transMap.put("userUuid", String.valueOf(uid));
        transMap.put("accNo", bankCardNo);
        transMap.put("accName", userName);
        transMap.put("mobile", bindingMobile);
        transMap.put("idNo", idNo);
        transMap.put("bankName", bankName);
        transMap.put("openProvince", openProvince);
        transMap.put("openCity", openCity);
        transMap.put("bankBranch", bankBranch);
        transMap.put("idType", "id");

        BigDecimal cardAmount = new BigDecimal(amount);
        transMap.put("amount", cardAmount.multiply(new BigDecimal(100)).setScale(0).toString());
        paramMap.put("transDetails", JSON.toJSONString(transMap));


        LOG.info("[===>>>service][autoDeductMoney()][===>>>批次号：{},支付中心划扣交易请求数据:{}======]", batchNo,
                JSON.toJSONString(paramMap));
        HttpResultVO httpVO = null;
        try {
            //HttpClient httpClient = new HttpClient();
            httpVO = httpClient.doPostDetail(costUrl, null, paramMap, "UTF-8");
            LOG.info("[===>>>service][autoDeductMoney()][===<<<批次号：{},支付中心划扣交易响应数据:{}======]", batchNo,
                    JSON.toJSONString(httpVO));

            if (httpVO.getStatusCode() != HttpStatus.SC_OK) {
                LOG.error("[===<<<批次号：{},支付中心代扣接口HTTP状态错误][HTTP状态{}][{}]", batchNo, httpVO.getStatusCode(),
                        JSON.toJSONString(httpVO));
                return ResultVO.build(ErrorCode.REPAYMENT_DEDUCT_MONEY_ERROR);
            }
            if (StringUtils.isBlank(httpVO.getResponseBody())) {
                LOG.error("[===<<<批次号：{},支付中心代扣接口HTTP状态错误][HTTP状态{}][{}]", batchNo, httpVO.getStatusCode(),
                        JSON.toJSONString(httpVO));
                return ResultVO.build(ErrorCode.REPAYMENT_DEDUCT_MONEY_ERROR);
            }

            PayCenterPayResultVO result = JSON.parseObject(httpVO.getResponseBody(), PayCenterPayResultVO.class);

            return ResultVO.build(ErrorCode.SUCCESS, result);
        } catch (Exception e) {
            LOG.error("[===<<<批次号:{},支付中心代扣交易HTTP调用异常][{}]", batchNo, JSON.toJSONString(paramMap), e);
            return ResultVO.build(ErrorCode.REPAYMENT_DEDUCT_MONEY_ERROR);
        }

    }

    @Override
    public ResultVO<PayCenterPayResultVO> queryDeductMoneyStatus(String batchNo) {
        Long timestamp = System.currentTimeMillis();
        String msgSign = MD5Utils.MD5Encode(timestamp + channelKey, "UTF-8");
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("interType", "3001");
        paramMap.put("terminalId", "10011");
        paramMap.put("timestamp", String.valueOf(timestamp));
        paramMap.put("msgSign", msgSign);
        paramMap.put("batchNo", batchNo);

        try {
            LOG.info("[>>>service][queryDeductMoneyStatus()][===>>>批次号：{},支付中心划扣查询请求数据:{}]", batchNo,
                    JSON.toJSONString(paramMap));

            //HttpClient httpClient = new HttpClient();
            HttpResultVO httpVO = httpClient.doPostDetail(singleCostQueryUrl, null, paramMap, "UTF-8");
            LOG.info("[>>>service][queryDeductMoneyStatus()][===<<<批次号：{},支付中心划扣查询响应数据:{}]", batchNo,
                    JSON.toJSONString(httpVO));

            if (httpVO.getStatusCode() != HttpStatus.SC_OK) {
                LOG.error("===>>>[批次号：{},查询代扣支付结果返回HTTP状态码非200，抛出异常]======", batchNo);
                throw new RuntimeException("批次号：" + batchNo + "，查询代扣支付结果返回HTTP状态码非200");
            }
            PayCenterPayResultVO vo = JSON.parseObject(httpVO.getResponseBody(), PayCenterPayResultVO.class);
            return ResultVO.build(ErrorCode.SUCCESS, vo);
        } catch (Exception e) {
            LOG.error("===>>>[批次号：{}，查询代扣支付结果异常，excp:{}======]", batchNo, e);
            throw new RuntimeException("批次号：" + batchNo + ",查询代扣支付结果接口异常,excp:{}", e);
        }
    }

    @Override
    public ResultVO<PayCenterPayResultVO> payMoney(String batchNo, Long uid, String bankCardNo, String userName, String bindingMobile, String idNo, String openProvince, String openCity, String bankBranch, String idType, String amount, String bankName) {

        LOG.info("[===>>>代付交易接口，请求参数:{},{},{},{},{},{},{},{},{},{},{},{}]======",
                batchNo, uid, bankCardNo, userName,
                bindingMobile, idNo, openProvince, openCity, bankBranch, idType, amount, bankName);

        Map<String, String> paramMap = new HashMap<>();
        Long timestamp = System.currentTimeMillis();
        String msgSign = MD5Utils.MD5Encode(timestamp + channelKey, "UTF-8");
        paramMap.put("interType", "4000");
        paramMap.put("terminalId", "10011");
        paramMap.put("timestamp", String.valueOf(timestamp));
        paramMap.put("msgSign", msgSign);

        Map<String, String> transMap = new HashMap<>();
        transMap.put("batchNo", batchNo);
        transMap.put("userUuid", String.valueOf(uid));
        transMap.put("accNo", bankCardNo);
        transMap.put("accName", userName);
        transMap.put("mobile", bindingMobile);
        transMap.put("idNo", idNo);
        transMap.put("bankName", bankName);
        transMap.put("openProvince", openProvince);
        transMap.put("openCity", openCity);
        transMap.put("bankBranch", bankBranch);
        transMap.put("idType", "id");

        BigDecimal cardAmount = new BigDecimal(amount);
        transMap.put("amount", cardAmount.toString());
        paramMap.put("transDetails", JSON.toJSONString(transMap));


        LOG.info("[===>>>service][payMoney()][===>>>批次号：{},支付中心代付交易请求数据:{}======]", batchNo,
                JSON.toJSONString(paramMap));
        HttpResultVO httpVO = null;
        try {
            //HttpClient httpClient = new HttpClient();
            httpVO = httpClient.doPostDetail(payUrl, null, paramMap, "UTF-8");
            LOG.info("[===>>>service][payMoney()][===<<<批次号：{},支付中心代付交易响应数据:{}======]", batchNo,
                    JSON.toJSONString(httpVO));

            if (httpVO.getStatusCode() != HttpStatus.SC_OK) {
                LOG.error("[===<<<批次号：{},支付中心代付接口HTTP状态错误][HTTP状态{}][{}]", batchNo, httpVO.getStatusCode(),
                        JSON.toJSONString(httpVO));
                return ResultVO.build(ErrorCode.REPAYMENT_DEDUCT_MONEY_ERROR);
            }
            if (StringUtils.isBlank(httpVO.getResponseBody())) {
                LOG.error("[===<<<批次号：{},支付中心代付接口HTTP状态错误][HTTP状态{}][{}]", batchNo, httpVO.getStatusCode(),
                        JSON.toJSONString(httpVO));
                return ResultVO.build(ErrorCode.REPAYMENT_DEDUCT_MONEY_ERROR);
            }

            PayCenterPayResultVO result = JSON.parseObject(httpVO.getResponseBody(), PayCenterPayResultVO.class);

            return ResultVO.build(ErrorCode.SUCCESS, result);
        } catch (Exception e) {
            LOG.error("[===<<<批次号:{},支付中心代付交易HTTP调用异常][{}]", batchNo, JSON.toJSONString(paramMap), e);
            return ResultVO.build(ErrorCode.REPAYMENT_DEDUCT_MONEY_ERROR);
        }
    }

    @Override
    public ResultVO<PayCenterPayResultVO> queryPayMoneyStatus(String batchNo) {
        Long timestamp = System.currentTimeMillis();
        String msgSign = MD5Utils.MD5Encode(timestamp + channelKey, "UTF-8");
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("interType", "4001");
        paramMap.put("terminalId", "10011");
        paramMap.put("timestamp", String.valueOf(timestamp));
        paramMap.put("msgSign", msgSign);
        paramMap.put("batchNo", batchNo);

        try {
            LOG.info("[>>>service][queryPayMoneyStatus()][===>>>批次号：{},支付中心代付查询请求数据:{}]", batchNo,
                    JSON.toJSONString(paramMap));

            //HttpClient httpClient = new HttpClient();
            HttpResultVO httpVO = httpClient.doPostDetail(singlePayQueryUrl, null, paramMap, "UTF-8");
            LOG.info("[>>>service][queryPayMoneyStatus()][===<<<批次号：{},支付中心代付查询响应数据:{}]", batchNo,
                    JSON.toJSONString(httpVO));

            if (httpVO.getStatusCode() != HttpStatus.SC_OK) {
                LOG.error("===>>>[批次号：{},查询代付支付结果返回HTTP状态码非200，抛出异常]======", batchNo);
                throw new RuntimeException("批次号：" + batchNo + "，查询代付支付结果返回HTTP状态码非200");
            }
            PayCenterPayResultVO vo = JSON.parseObject(httpVO.getResponseBody(), PayCenterPayResultVO.class);
            return ResultVO.build(ErrorCode.SUCCESS, vo);
        } catch (Exception e) {
            LOG.error("===>>>[批次号：{}，查询代付支付结果异常，excp:{}======]", batchNo, e);
            throw new RuntimeException("批次号：" + batchNo + ",查询代付支付结果接口异常,excp:{}", e);
        }
    }

    /**
     * 摩尔龙前台手续费扣除
     */
    @Override
    public ResultVO<PayCenterPayResultVO> deductMrlMoney(String batchNo, Long uid, String bankCardNo, String userName, String bindingMobile, String idNo, String openProvince, String openCity, String bankBranch, String idType, String amount, String bankName) {

        LOG.info("[===>>>划扣交易接口，请求参数:{},{},{},{},{},{},{},{},{},{},{}]======", batchNo, uid, bankCardNo, userName,
                bindingMobile, idNo, openProvince, openCity, bankBranch, idType, amount);

        Map<String, String> paramMap = new HashMap<>();
        Long timestamp = System.currentTimeMillis();
        String msgSign = MD5Utils.MD5Encode(timestamp + channelKey, "UTF-8");
        paramMap.put("interType", "5000");
        paramMap.put("terminalId", "10011");
        paramMap.put("timestamp", String.valueOf(timestamp));
        paramMap.put("msgSign", msgSign);

        Map<String, String> transMap = new HashMap<>();
        transMap.put("batchNo", batchNo);
        transMap.put("userUuid", String.valueOf(uid));
        transMap.put("accNo", bankCardNo);
        transMap.put("accName", userName);
        transMap.put("mobile", bindingMobile);
        transMap.put("idNo", idNo);
        transMap.put("bankName", bankName);
        transMap.put("openProvince", openProvince);
        transMap.put("openCity", openCity);
        transMap.put("bankBranch", bankBranch);
        transMap.put("idType", "id");

        BigDecimal cardAmount = new BigDecimal(amount);
        transMap.put("amount", cardAmount.multiply(new BigDecimal(100)).setScale(0).toString());
        paramMap.put("transDetails", JSON.toJSONString(transMap));


        LOG.info("[===>>>service][autoDeductMoney()][===>>>批次号：{},支付中心摩尔龙划扣交易请求数据:{}======]", batchNo,
                JSON.toJSONString(paramMap));
        HttpResultVO httpVO = null;
        try {
            //HttpClient httpClient = new HttpClient();
            httpVO = httpClient.doPostDetail(mrlcostUrl, null, paramMap, "UTF-8");
            LOG.info("[===>>>service][autoDeductMoney()][===<<<批次号：{},支付中心摩尔龙划扣交易响应数据:{}======]", batchNo,
                    JSON.toJSONString(httpVO));

            if (httpVO.getStatusCode() != HttpStatus.SC_OK) {
                LOG.error("[===<<<批次号：{},支付中心摩尔龙代扣接口HTTP状态错误][HTTP状态{}][{}]", batchNo, httpVO.getStatusCode(),
                        JSON.toJSONString(httpVO));
                return ResultVO.build(ErrorCode.REPAYMENT_DEDUCT_MONEY_ERROR);
            }
            if (StringUtils.isBlank(httpVO.getResponseBody())) {
                LOG.error("[===<<<批次号：{},支付中心摩尔龙代扣接口HTTP状态错误][HTTP状态{}][{}]", batchNo, httpVO.getStatusCode(),
                        JSON.toJSONString(httpVO));
                return ResultVO.build(ErrorCode.REPAYMENT_DEDUCT_MONEY_ERROR);
            }

            PayCenterPayResultVO result = JSON.parseObject(httpVO.getResponseBody(), PayCenterPayResultVO.class);

            return ResultVO.build(ErrorCode.SUCCESS, result);
        } catch (Exception e) {
            LOG.error("[===<<<批次号:{},支付中心摩尔龙代扣交易HTTP调用异常][{}]", batchNo, JSON.toJSONString(paramMap), e);
            return ResultVO.build(ErrorCode.REPAYMENT_DEDUCT_MONEY_ERROR);
        }

    }

    @Override
    public ResultVO<PayCenterPayResultVO> queryMrlDeductMoneyStatus(String batchNo) {
        Long timestamp = System.currentTimeMillis();
        String msgSign = MD5Utils.MD5Encode(timestamp + channelKey, "UTF-8");
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("interType", "5001");
        paramMap.put("terminalId", "10011");
        paramMap.put("timestamp", String.valueOf(timestamp));
        paramMap.put("msgSign", msgSign);
        paramMap.put("batchNo", batchNo);

        try {
            LOG.info("[>>>service][queryDeductMoneyStatus()][===>>>批次号：{},支付中心摩尔龙划扣查询请求数据:{}]", batchNo,
                    JSON.toJSONString(paramMap));

            //HttpClient httpClient = new HttpClient();
            HttpResultVO httpVO = httpClient.doPostDetail(mrlcostQueryUrl, null, paramMap, "UTF-8");
            LOG.info("[>>>service][queryDeductMoneyStatus()][===<<<批次号：{},支付中心摩尔龙划扣查询响应数据:{}]", batchNo,
                    JSON.toJSONString(httpVO));

            if (httpVO.getStatusCode() != HttpStatus.SC_OK) {
                LOG.error("===>>>[批次号：{},查询摩尔龙代扣支付结果返回HTTP状态码非200，抛出异常]======", batchNo);
                throw new RuntimeException("批次号：" + batchNo + "，查询摩尔龙代扣支付结果返回HTTP状态码非200");
            }
            PayCenterPayResultVO vo = JSON.parseObject(httpVO.getResponseBody(), PayCenterPayResultVO.class);
            return ResultVO.build(ErrorCode.SUCCESS, vo);
        } catch (Exception e) {
            LOG.error("===>>>[批次号：{}，查询摩尔龙代扣支付结果异常，excp:{}======]", batchNo, e);
            throw new RuntimeException("批次号：" + batchNo + ",查询摩尔龙代扣支付结果接口异常,excp:{}", e);
        }
    }
}
