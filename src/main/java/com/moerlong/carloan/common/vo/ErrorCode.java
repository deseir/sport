package com.moerlong.carloan.common.vo;

public enum ErrorCode {

    SUCCESS(0, "成功"),

    PARAM_EMPTY(10001, "接口参数为空"),

    DATABASE_EXCEPTION(10002, "数据库操作异常"),

    PARAM_ERROR(10003, "接口参数错误"),

    OLD_PWD_NOT_RIGHT(402, "原密码不正确"),

    LOGIN_PASSWD_ERR(50001, "用户名不存在"),

    LOGIN_USER_NOT_EXIST(50002, "用户密码错误"),

    TOKEN_TIMEOUT_ERROR(50003, "TOKEN会话超时"),

    USER_ALREADY_EXIST(50004, "用户已注册"),

    SMS_SEND_VERIFY_CODE_ERROR(50005, "发送短信验证码失败"),

    SMS_CODE_ERROR(50006, "短信验证码验证失败"),
    

    PWD_NOT_REQPWD(50007,"密码和确认密码不一致"),

    
    
    IDENTITY_AUTH_REPEAT(60001, "您已通过身份认证，请勿重复认证"),

    IDENTITY_AUTH_FAILED(60002, "身份认证未通过！"),

    IDENTITY_AUTH_EXCEPTION(60003, "身份认证异常！"),

    LIVENESS_AUTH_REPEAT(60004, "您已通过活体认证，请勿重复认证"),

    LIVENESS_AUTH_EXCEPTION(60005, "活体认证异常！"),

    LIVENESS_AUTH_FAILED(60006, "活体认证失败！"),



    REPAYMENT_INFO_NOT_EXIST(70001, "无效的还款信息"),

    REPAYMENT_PAYING_EXIST(70002, "存在支付中的订单，请稍后再试"),

    REPAYMENT_PLAN_AMOUNT_ERROR(70003, "还款计划金额错误"),

    REPAYMENT_DEDUCT_MONEY_ERROR(70004, "代扣失败"),

    REPAYMENT_PAY_FAIL(70005, "支付失败"),

    REPAYMENT_PAY_INFO_NOT_EXIST(70006, "无效的支付订单信息"),

    REPAYMENT_PAY_ALLOT_ERROR(70007, "分配的还款金额不正确"),

    REPAYMENT_PAY_DOING(70008, "支付处理中"),

    PAY_INFO_NOT_EXIST(70009, "无效的放款信息"),

    PAY_MONEY_ERROR(70010, "放款金额错误"),

    PAY_DETAIL_INFO_NOT_EXIST(70011, "无效的放款详情单信息"),

    PAY_PROCESS_ERROR(70012, "放款流程错误"),

    PAY_PROCESS_FINANCE_APPROVE_ERROR(70013, "财务审批流程出现错误"),

    PAY_PROCESS_FINANCE_MANAGER_APPROVE_ERROR(70014, "财务经理审批流程出现错误"),

    REPAYMENT_PLAN_INFO_NOT_EXIST(70015, "还款计划信息不存在"),

    REPAYMENT_PLAN_INFO_ALREADY_FINISH(70016, "本期已还清"),

    REPAYMENT_ONCE_EARLY_NOT_SUPPORT_ACROSS_PEROID(70017, "系统不支持跨期提前还款"),

    REPAYMENT_ONCE_EARLY_ERROR_APPOINT_DATE(70018, "约定提前还款时间不正确"),

    REPAYMENT_ONCE_EARLY_PROCESS_ERROR(70019, "提前还款流程错误"),

    NQLR_STATUS(4001,"请先完成内勤录入"),
    NO_SAVE_CAR(4006,"请先录入车辆信息！"),
    APPLYINFO_LOST(4003,"借贷基本信息缺失！"),
    CUSTOMERINFO_LOST(4004,"客户基本信息缺失！"),
    CUSTWORKINFO_LOST(4005,"申请人工作信息缺失！"),
    CARINFOVO_LOST(4007,"车辆信息缺失！"),
    YC_STATUS(4002,"请先完成验车信息录入"),

    GPS_INSTALL_STATUS(4003,"请先完成gps安装流程"),
    GPS_UNINSTALL_STATUS(4005,"请先完成gps卸载流程"),

    INTERVIEW_NOT_SAVE(4004,"请先保存面审意见，然后提交！"),

    FINALJUDGEMENTINFO_NOT_SAVE(40045,"请先保存终审意见，然后提交！"),

    TOKEN_IS_NULL(3000,"tokenId为空！"),
    
    NO_CONTRACT(3001,"该订单还没有签订合同"),

    NO_FINALJUDGEMENTINFO(3002,"该订单没有查到终审信息"),

    NO_SAVE_APPLY(3003,"请先保存抵押申请信息！"),

    NO_ACCEPT_CONFIRM(3004,"请先保存小票照片(确认受理)！"),

    NO_COMPLETE_CONFIRM(3005,"请先保存车辆登记证书！"),

    NO_CONTRACT_SAVE(3006,"请先下载合同模板或填写合同签订信息！"),

    NO_CONTRACT_SIGNDATE(3007,"请先保存合同签订信息！"),

    SYSTEM_INNER_FAILED(99999, "系统内部错误！"),

    FILE_UPLOAD_FAILED(80000, "文件名中包含非法字符！");


    private Integer errCode;
    private String msg;


    ErrorCode(Integer errCode, String msg){
        this.errCode = errCode;
        this.msg = msg;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
