package com.moerlong.carloan.modular.app.business;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.google.gson.JsonObject;
import com.moerlong.carloan.common.persistence.model.UserVO;
import com.moerlong.carloan.common.vo.ResultVO;
import com.moerlong.carloan.modular.car.entity.CarGpsDetailInfo;
import com.moerlong.carloan.modular.car.entity.CarGpsUnInstallPhotoInfo;
import com.moerlong.carloan.modular.car.entity.CarPhotoInfo;
import com.moerlong.carloan.modular.loan.entity.vo.LoanAppAppinfoVO;
import com.moerlong.carloan.modular.loan.entity.vo.LoanApplyInfoVo;

import java.util.List;
import java.util.Map;

public interface AppBussiness {

    /**
     * 用户密码登录
     * @param mobile
     * @param pwd
     * @return
     */
    public ResultVO<Object> loginWithPwd(String account, String pwd);

    /**
     * 登出
     * @param tokenId
     * @return
     */
    public ResultVO<Object> logout(String tokenId);

    /**
     * 获取用户信息
     * @param tokenId
     * @return
     */
    public ResultVO<Object> getUserInfo(String tokenId);

    /**
     * 通过手机号发送验证码
     * @param mobile    手机号
     * @param flag      0--注册  1--重置密码
     * @return
     */
    public ResultVO<Object> sendVerifyCode(String mobile, Integer flag);

    /**
     * 通过短信验证码登录
     * @param mobile
     * @param smsCode
     * @param smsId
     * @return
     */
    public ResultVO<Object> loginWithVerifyCode(String mobile, String smsCode, String smsId);

    /**
     * 获取待办列表
     * @param tokenId
     * @return
     */
    public ResultVO<Object> getTodoWorkList(String tokenId, Integer pageNum, Integer pageSize);
    
    
    /**
     * 根据订单号获取验车师的初始化信息
     * @param tokenId
     * @param applyId
     * @return
     */
    public ResultVO<Object> selInitCarverifyByCustId(String tokenId,Long applyId);

    /**
     * 车辆图片保存
     * @param
     */
    public ResultVO<Object> carPhotoSave(List<CarPhotoInfo> list, String tokenId);
    /**
     * 修改我的信息
     * @param user
     * @return
     */

    public ResultVO<Object> saveOrUpdateUser(String idCode,String address,String tokenId);
    /**
     *回退
     * @param userId
     * @param custName
     * @param custIdNo
     * @param beginTime
     * @param endTime
     * @return
     */

    public ResultVO<Object> getBackWorkList(String tokenId,Integer pageNum,Integer pageSize);

    /**
     * GPS安装信息提交或更新
     * @param list
     * @param tokenId
     * @param applyId
     * @param gpsInfo
     * */

    public ResultVO<Object> carGpsInstallInfoSaveOrUpdate(List<CarGpsDetailInfo> list, String tokenId, Long applyId, JSONObject gpsInfo);

    /**
     * GPS安装信息回显
     * @param tokenId
     * @param applyId
     * @param params
     * @return
     * */

    public ResultVO<Object> carGpsInstallInfoShow( String tokenId, Long applyId,Map<String,Object> params);

    /**
     * GPS拆卸保存
     * @param list
     * @param tokenId
     * @param applyId
     * */
    public ResultVO<Object> carGpsUnInstallInfoSaveOrUpdate(List<CarGpsUnInstallPhotoInfo> list, String tokenId, Long applyId);
    /**
     * GPS拆卸信息
     * @param tokenId
     * @param applyId
     * @return
     * */
    public ResultVO<Object> carGpsUnInstallInfoShow(String tokenId,Long applyId);

    /**
     * 验车信息提交
     * @param list
     * @param tokenId
     * @param applyId
     * */
    public ResultVO<Object> carVerifyInfoSaveOrUpdate(List<CarPhotoInfo> list, String tokenId, Long applyId);

    /**
     * 验车信息
     * @param tokenId
     * @param applyId
     * @return
     * */
    public ResultVO<Object> carVerifyInfoShow(String tokenId, Long applyId);

    /**
     * 抵押申请提交
     * @param tokenId
     * @param applyId
     * */
    public ResultVO<Object> carBussMortgageInfoSavaOrUpdate(String tokenId, Long applyId,Map<String,Object> params);

    /**
     * 抵押申请信息
     * @param tokenId
     * @param applyId
     * @return
     * */
    public ResultVO<Object> carBussMortgageInfoShow(String tokenId, Long applyId);

    /**
     * 解押申请提交
     * @param tokenId
     * @param applyId
     * @param params
     * */
    public ResultVO<Object> carDetentionInfoSaveOrUpdate(String tokenId, Long applyId,Map<String,Object> params);

    /**
     * 解押申请信息
     * @param tokenId
     * @param applyId
     * @return
     * */
    public ResultVO<Object> carDetentionInfoShow(String tokenId, Long applyId);

    /**
     * 常见问题
     * */
    public ResultVO<Object> carFAQ(String tokenId);

    /**
     * 获取订单信息
     * @param params
     * @return
     */
    public ResultVO<Object> getApplyInfor(Map<String,Object> params);
    /**
     * 获取订单详情信息 
     * @param params
     * @return
     */
    public ResultVO<Object> findByAppInfoById(Map<String,Object> params);
    
    /**
     * 获取ORC信息 
     * @param params
     * @return
     */
    public ResultVO<Object> getUserORC(Map<String,Object> params);
    
    /**
     * 活体认证 
     * @param params
     * @return
     */
    public ResultVO<Object> saveOrUpdateLivingBody(Map<String,Object> params);
    
    /**
     * 找回密码
     * @param params
     * @return
     */
    public ResultVO<Object> updateMobilePwd(Map<String,Object> params);
}
