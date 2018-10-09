package com.moerlong.carloan.util;


import com.moerlong.carloan.modular.loan.entity.vo.SendMsgVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 全局变量-系统参数
 */
public class ParamConstants {

    /** 财务人员 */
    public final static String ROLE_FINA="11";
    /** 财务经理 */
    public final static String ROLE_FINA_MA="10";
    /** 内勤 */
    public final static String ROLE_NQ="3";//内勤
    /** 内勤主管 */
    public final static String ROLE_NQZG="2";//内勤主管
    
    /** 验车师 */
    public final static String ROLE_YCS="4";// 验车师
    
    /** 面试 */
    public final static String ROLE_MS="6";// 面审
    
    /** 面试主管 */
    public final static String ROLE_MSZG="5";// 面审主管
    
    /** 抵押专员 */
    public final static String ROLE_DYZY="8";// 抵押专员
    
    /** 终审 */
    public final static String ROLE_ZS="7";// 终审
    
    /** 前台财务 */
    public final static String ROLE_QTCW="12";// 前台财务
    
    /** 业务经理 */
    public final static String ROLE_BUSS_MA="9";
   

    /** 财务部 */
    public final static Integer DEPT_FINA=28;
    /** 业务部 */
    public final static Integer DEPT_BUSS=29;
    /** 开发人员 */
    public final static Integer DEPT_DEVE=30;
    /** 成都财务 */
    public final static Integer DEPT_FINA_CD=31;

    /** 短信通知相关人员*/
    public static Map<String,List<SendMsgVo>> userList=new HashMap<String,List<SendMsgVo>>();

    /** 返回Vo */
    public static List<SendMsgVo> getValue(String key){

        return userList.get(key);
    }
}
