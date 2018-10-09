package com.moerlong.carloan.modular.app.service;

import java.util.List;
import java.util.Map;

public interface SMSService {
    public static final String AUDIT_APPLY = "1";       //审核申请
    public static final String AUDIT_SUCCESS = "2";     //审核成功
    public static final String AUDIT_FAIL = "3";        //审核失败
    public static final String BORROW_SUCCESS = "4";    //放款成功
    public static final String PAYMENT_END_DAY_REMAIN = "5";    //到期提醒
    public static final String PAYMENT_OVERDUE = "6";      //逾期提醒
    public static final String REMAIN_REPAYMENT = "7";      //到期提醒2

    public static final String APPLY_TO_AUDIT = "11";       //放款申请单已提交
    public static final String APPLY_SUCCESS = "12";        //申请单审批通过
    public static final String APPLY_FAIL = "13";           //申请单审批失败
    public static final String FINANCE_AUDIT_SUCCESS = "14";    //财务审批成功
    public static final String FINANCE_AUDIT_FAIL = "15";    //财务审批失败
    public static final String FINANCE_MANAGER_AUDIT_SUCCESS = "16";    //财务主管审批成功
    public static final String FINANCE_MANAGER_AUDIT_FAIL = "17";    //财务主管审批失败
    public static final String PAY_RESULT= "18";       //放款结果
    public static final String PAY_PAYING_TIP = "19";   //放款处理时间提醒
    public static final String REPAY_RESULT = "20";     //收款结果
    public static final String REPAY_PAYING_TIP = "21"; //收款处理时间提醒
    public static final String BUSS_CHAGE_COM = "22"; //业务人员确认收取服务费
    public static final String COST_RESULT = "23"; //扣款短信
    public static final String REPAY_RECEPTION_RESULT = "24";   //前台手续费收款结果
    public static final String REPAY_ONCE_CHARGE_RESULT = "25"; //一次性手续费收款结果





    /**
     * 发送短信验证码
     * @param mobile        手机号
     * @param code          验证码（如果为空，则生成后返回）
     * @param smsType       短信类型  false--短信 true--语音
     * @return              null--失败 返回验证码
     */
    String sendVerifySMS(String mobile, String code, boolean smsType);

    /**
     *  发送短信信息
     * @param mobile          手机号
     * @param businessCode    业务代码
     * @param msgParam        短信数据
     * @return                true--成功
     */
    boolean sendMsgSMS(List<String> mobileList, String businessCode, Map<String, Object> msgParam);
}
