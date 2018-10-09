package com.moerlong.carloan.modular.app.business.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.common.exception.BizExceptionEnum;
import com.moerlong.carloan.common.exception.BussinessException;
import com.moerlong.carloan.common.persistence.dao.UserMapper;
import com.moerlong.carloan.common.persistence.model.User;
import com.moerlong.carloan.common.persistence.model.UserVO;
import com.moerlong.carloan.common.vo.ErrorCode;
import com.moerlong.carloan.common.vo.ResultVO;
import com.moerlong.carloan.core.shiro.ShiroKit;
import com.moerlong.carloan.core.shiro.ShiroUser;
import com.moerlong.carloan.core.util.ToolUtil;
import com.moerlong.carloan.modular.app.business.AppBussiness;
import com.moerlong.carloan.modular.app.service.SMSService;
import com.moerlong.carloan.modular.app.utils.LoginModel;
import com.moerlong.carloan.modular.app.utils.TokenGenerator;
import com.moerlong.carloan.modular.app.utils.UserAuthTools;
import com.moerlong.carloan.modular.app.utils.UserTokenInfoModel;
import com.moerlong.carloan.modular.car.entity.*;
import com.moerlong.carloan.modular.car.entity.vo.CarDetentionInfoVo;
import com.moerlong.carloan.modular.car.entity.vo.InitCarVerifyVo;
import com.moerlong.carloan.modular.car.service.*;
import com.moerlong.carloan.modular.loan.bussiness.ProcessBussiness;
import com.moerlong.carloan.modular.cust.entity.LivenessAuthInfo;
import com.moerlong.carloan.modular.cust.entity.vo.CustomerInfoVo;
import com.moerlong.carloan.modular.cust.service.LivenessAuthInfoService;
import com.moerlong.carloan.modular.loan.dao.ApplyInfoDao;
import com.moerlong.carloan.modular.loan.dao.ProcessNodeDao;
import com.moerlong.carloan.modular.loan.entity.ApplyInfo;
import com.moerlong.carloan.modular.loan.entity.vo.LoanAppAppinfoVO;
import com.moerlong.carloan.modular.loan.service.ApplyInfoService;
import com.moerlong.carloan.modular.system.dao.UserMgrDao;
import com.moerlong.carloan.modular.system.service.IDeptService;
import com.moerlong.carloan.util.AppInforPage;
import com.moerlong.carloan.util.IDGenerator;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class AppBussinessImpl implements AppBussiness{

    private static final Logger LOG = LoggerFactory.getLogger(AppBussinessImpl.class);


    @Resource
    private UserMgrDao userMgrDao;
    @Resource
    private UserMapper userMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private SMSService smsService;
    @Autowired
    private ApplyInfoService    applyInfoService;
    @Autowired
    private CarInfoService carInfoService;
    @Autowired
    private CarVerifyInfoService carVerifyInfoService;
    @Autowired
    private CarPhotoInfoService carPhotoInfoService;
    @Autowired
	IDeptService deptService;
    @Autowired
	ApplyInfoDao mapper;
    @Autowired
	ProcessNodeDao	processNodeDao;
    @Autowired
    private CarGpsDetailInfoService carGpsDetailInfoService;
    @Autowired
    private CarGpsInfoService carGpsInfoService;
    @Autowired
    private GpsUnInstallPhotoInfoService unInstallService;
    @Autowired
    private CarBussMortgageInfoService carBussMortgageInfoService;
    @Autowired
    private CarDetentionInfoService carDetentionInfoService;
    @Autowired
    private ProcessBussiness processBussiness;
    @Autowired
    private LivenessAuthInfoService livenssService;
    /**
     * 用户密码登录
     * @param mobile
     * @param pwd
     * @return
     */
    @Override
    public ResultVO<Object> loginWithPwd(String account, String pwd) {
        LOG.info("===>[loginWithPwd] account={}, pwd={}", account, pwd);
        if(StringUtils.isEmpty(account) || StringUtils.isEmpty(pwd)){
            LOG.error("===>[loginWithPwd] 参数为空： account={} pwd={}", account, pwd);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }

        try {

          User userInfo = userMgrDao.getByAccount(account);
            if (userInfo == null) {
                LOG.error("===>[loginWithPwd] do not exist account={}", account);
                return ResultVO.build(ErrorCode.LOGIN_PASSWD_ERR);
            }
            	if(!userInfo.getPassword().equalsIgnoreCase(ShiroKit.md5(pwd, userInfo.getSalt()))){
                    LOG.error("===>[loginWithPwd] account {} pwd is error", account);
                    return ResultVO.build(ErrorCode.LOGIN_USER_NOT_EXIST);
                }
            	long tokenExpiredTime = TokenGenerator.getExpiredTokenExpiredTime();
                String tokenId = TokenGenerator.getTokenId(userInfo.getId(), tokenExpiredTime);

                stringRedisTemplate.opsForValue().set(TokenGenerator.REDIS_EXPIREDTOKEN_KEY + userInfo.getId() + tokenExpiredTime, tokenId,
                        TokenGenerator.EXPIREDTOKEN_EXPIRED_TIMEOUT, TimeUnit.SECONDS);
                LoginModel model = new LoginModel();
                model.setTokenId(tokenId);
                model.setExpiredTime(tokenExpiredTime);
                model.setUser(userInfo);
                return ResultVO.build(ErrorCode.SUCCESS, (Object)model);

        }catch(Exception e){
            LOG.error("===>[loginWithPwd] exception e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    @Override
    public ResultVO<Object> logout(String tokenId) {
        if(StringUtils.isEmpty(tokenId)){
            LOG.error("===>[logout] 参数为空： tokenId={}", tokenId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }

        try {
            ResultVO<UserTokenInfoModel> ret = UserAuthTools.getUserInfo(tokenId);


            if (ret.getStatus() != 0) {
                return ResultVO.build(ret.getStatus(), ret.getMsg());
            }

            UserTokenInfoModel userModel = ret.getData();

            stringRedisTemplate.delete(TokenGenerator.REDIS_EXPIREDTOKEN_KEY + userModel.getId() + userModel.getExpiredTime());

            return ResultVO.build(ErrorCode.SUCCESS);
        }catch (Exception e) {
            LOG.error("===>[logout] exception e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    @Override
    public ResultVO<Object> getUserInfo(String tokenId) {

        if(StringUtils.isEmpty(tokenId)){
            LOG.error("===>[getUserInfo] 参数为空： tokenId={}", tokenId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }

        try {
            ResultVO<UserTokenInfoModel> ret = UserAuthTools.getUserInfo(tokenId);


            if (ret.getStatus() != 0) {
                return ResultVO.build(ret.getStatus(), ret.getMsg());
            }

            UserTokenInfoModel userModel = ret.getData();

            UserVO user = userMapper.findById(userModel.getId());
            if (user == null) {
                LOG.error("===>[getUserInfo] userId={} not find in db", userModel.getId());
                return ResultVO.build(ErrorCode.LOGIN_USER_NOT_EXIST);
            }

            return ResultVO.build(ErrorCode.SUCCESS, (Object) user);
        }catch (Exception e) {
            LOG.error("===>[getUserInfo] exception e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    @Override
    public ResultVO<Object> sendVerifyCode(String mobile, Integer flag) {
        if(StringUtils.isEmpty(mobile)){
            LOG.error("===>[sendVerifyCode] 参数为空： mobile={}", mobile);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }

        try{
            String code = null;
            Map<String, Object> ret= new HashMap<>();


            List<UserVO> userInfo = userMgrDao.selectByUserVOMobile(mobile);
            if(flag == 0){
                //如果是注册的话 需要判断是否注册过
                if(userInfo.size()>0){
                	System.out.println(userInfo);
                    LOG.error("===>[sendVerifyCode] 手机号已被注册 mobile={}", mobile);
                    return ResultVO.build(ErrorCode.USER_ALREADY_EXIST);
                }
            }else if(flag == 1){
                //如果是重置密码 需要判断是否存在该客户
                if(userInfo == null){
                    LOG.error("===>[sendVerifyCode] 手机号未注册 mobile={}", mobile);
                    return ResultVO.build(ErrorCode.LOGIN_USER_NOT_EXIST);
                }
            }

            code = smsService.sendVerifySMS(mobile, null, false);
            if(code == null){
                LOG.error("===>[sendVerifyCode] 发送验证码失败 mobile={}", mobile);
                return ResultVO.build(ErrorCode.SMS_SEND_VERIFY_CODE_ERROR);
            }

            String smsId = IDGenerator.getNumUUID();

            if(stringRedisTemplate.hasKey(TokenGenerator.REDIS_SMS_KEY + mobile)){
                stringRedisTemplate.delete(TokenGenerator.REDIS_SMS_KEY + mobile);
            }

            stringRedisTemplate.opsForValue().set(TokenGenerator.REDIS_SMS_KEY + mobile, smsId+":"+code, TokenGenerator.SMS_CODE_TIMEOUT, TimeUnit.SECONDS);

            ret.put("smsId", smsId);
            System.out.println("-----------------"+smsId);
            return ResultVO.build(ErrorCode.SUCCESS, (Object)ret);
        }catch(Exception e){
            LOG.error("===>[sendVerifyCode] exception e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * 验证短信验证码
     * @param mobile
     * @param smsCode
     * @param smsId
     * @return
     */
    private boolean validateSmsCode(String mobile, String smsCode, String smsId){
        try{
            if(!stringRedisTemplate.hasKey(TokenGenerator.REDIS_SMS_KEY + mobile)){
                LOG.error("===>[validateSmsCode] mobile={} not exist in redis", mobile);
                return false;
            }

            String redisSMSContent = stringRedisTemplate.opsForValue().get(TokenGenerator.REDIS_SMS_KEY + mobile);
            if(StringUtils.isEmpty(redisSMSContent)){
                LOG.error("===>[validateSmsCode] mobile={} content is null", mobile);
                return false;
            }

            String redisCode = redisSMSContent.substring(redisSMSContent.indexOf(":")+1);
            String redisSmsId = redisSMSContent.substring(0, redisSMSContent.indexOf(":"));

            stringRedisTemplate.delete(TokenGenerator.REDIS_SMS_KEY + mobile);

            if(!(redisCode.equals(smsCode) && redisSmsId.equals(redisSmsId))){
                return false;
            }

            return true;
        }catch(Exception e){
            LOG.error("===>[validateSmsCode] exception e={}", e);
            return false;
        }
    }

    /**
     * 短信验证码登录
     * @param mobile
     * @param smsCode
     * @param smsId
     * @return
     */
    @Override
    public ResultVO<Object> loginWithVerifyCode(String mobile, String smsCode, String smsId) {
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(smsCode) || StringUtils.isEmpty(smsId)){
            LOG.error("===>[sendVerifyCode] 参数为空： mobile={} smsCode={} smsId={}", mobile, smsCode, smsId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }

        try{
            if(!validateSmsCode(mobile, smsCode, smsId)){
                LOG.error("===>[register] mobile={} 验证码错误", mobile);
                return ResultVO.build(ErrorCode.SMS_CODE_ERROR);
            }

            User temp = userMgrDao.selectByMobile(mobile);
            if(temp == null){
                LOG.error("===>[register] mobile={} 用户不存在", mobile);
                return ResultVO.build(ErrorCode.LOGIN_PASSWD_ERR);
            }

            long tokenExpiredTime = TokenGenerator.getExpiredTokenExpiredTime();
            String tokenId = TokenGenerator.getTokenId(temp.getId(), tokenExpiredTime);

            stringRedisTemplate.opsForValue().set(TokenGenerator.REDIS_EXPIREDTOKEN_KEY + temp.getId() + tokenExpiredTime, tokenId,
                    TokenGenerator.EXPIREDTOKEN_EXPIRED_TIMEOUT, TimeUnit.SECONDS);

            LoginModel model = new LoginModel();
            model.setTokenId(tokenId);
            model.setExpiredTime(tokenExpiredTime);
            model.setUser(temp);

            return ResultVO.build(ErrorCode.SUCCESS, (Object)model);

        }catch(Exception e){
            LOG.error("===>[sendVerifyCode] exception e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }

    /**
     * App获取待办列表
     * @param tokenId
     * @return
     */
    @Override
    public ResultVO<Object> getTodoWorkList(String tokenId, Integer pageNum, Integer pageSize) {

        if(StringUtils.isEmpty(tokenId)){
            LOG.error("===>[getTodoWorkList] 参数为空： tokenId={}", tokenId);
            return ResultVO.build(ErrorCode.TOKEN_IS_NULL);
        }

        ResultVO<UserTokenInfoModel> ret = UserAuthTools.getUserInfo(tokenId);
        if (ret.getStatus() != 0) {
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
        try {
			UserTokenInfoModel userModel = ret.getData();
			Long userId = userModel.getId();
			PageHelper.startPage(pageNum, pageSize);
			List<LoanAppAppinfoVO> pageList = applyInfoService.selectToAppdoApplyList(userId);
			List<LoanAppAppinfoVO> list=new ArrayList<>();
			for (LoanAppAppinfoVO loanApplyInfoVo : pageList) {
				if (loanApplyInfoVo.getSfrzStatus()==null) {
					loanApplyInfoVo.setNodeId(1);;
					loanApplyInfoVo.setNodeDesc("待认证");;
				}else if(loanApplyInfoVo.getYcStatus()==1) {
					loanApplyInfoVo.setNodeId(2);
					loanApplyInfoVo.setNodeDesc("验车");
				}else if(loanApplyInfoVo.getGpsInstallStatus()==2){
					loanApplyInfoVo.setNodeId(3);
					loanApplyInfoVo.setNodeDesc("GPS安装");
				}else if(loanApplyInfoVo.getStatus().equals("7100")){
					loanApplyInfoVo.setNodeId(4);
					loanApplyInfoVo.setNodeDesc("抵押登记");
				}else if(loanApplyInfoVo.getStatus().equals("11000")){
					loanApplyInfoVo.setNodeId(5);
					loanApplyInfoVo.setNodeDesc("解押登记");
				}else if(loanApplyInfoVo.getGpsUninstallStatus()==2){
					loanApplyInfoVo.setNodeId(6);
					loanApplyInfoVo.setNodeDesc("GPS拆卸");
				}
				list.add(loanApplyInfoVo);
			}

			PageInfo<LoanAppAppinfoVO> pageInfo = new PageInfo<LoanAppAppinfoVO>(list);
			Map<String, Object> res = new HashMap<>();
			res.put("total",pageInfo.getTotal());
			res.put("rows",pageInfo.getList());	
			return ResultVO.build(ErrorCode.SUCCESS, (Object)res);
		} catch (Exception e) {
			LOG.error("===>[getTodoWorkList] 系统异常", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
		}
    }

    /**
     * 初始化验车师信息
     * @param tokenId
     * @param applyId
     * @return
     */
    public ResultVO<Object> selInitCarverifyByCustId(String tokenId,Long applyId){
        if(StringUtils.isEmpty(tokenId)){
            LOG.error("===>[selInitCarverifyByCustId] 参数为空： tokenId={}", tokenId);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }

        ResultVO<UserTokenInfoModel> ret = UserAuthTools.getUserInfo(tokenId);
        if (ret.getStatus() != 0) {
            return ResultVO.build(ret.getStatus(), ret.getMsg());
        }

        try {
            if (ToolUtil.isEmpty(applyId)) {
                throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
            }
            ApplyInfo applyinfo = applyInfoService.selectById(applyId);
            if (ToolUtil.isEmpty(applyinfo)) {
                throw new BussinessException(BizExceptionEnum.DB_RESOURCE_NULL);
            }
            InitCarVerifyVo initCarVerifyVo = this.carInfoService.selInitCarverifyByCustId(applyinfo.getCustId());

            CarVerifyInfo verifyInfo = carVerifyInfoService.selectByCarId(initCarVerifyVo.getCarId());
            if (verifyInfo != null) {
                initCarVerifyVo.setId(verifyInfo.getId());
                initCarVerifyVo.setCarCond(verifyInfo.getCarCond());
                initCarVerifyVo.setSuggestion(verifyInfo.getSuggestion());
                initCarVerifyVo.setConfigTablePhoto(verifyInfo.getConfigTablePhoto());
                initCarVerifyVo.setMaintainPhoto(verifyInfo.getMaintainPhoto());
                initCarVerifyVo.setCarAssessmentPrice(verifyInfo.getCarAssessmentPrice());
            }
            return ResultVO.build(ErrorCode.SUCCESS, (Object) initCarVerifyVo);
        }catch(Exception e){
            LOG.error("===>[selInitCarverifyByCustId] exception e={}", e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }

    }

    public ResultVO<Object> carPhotoSave(List<CarPhotoInfo> list,String tokenId){
        if(StringUtils.isEmpty(tokenId)){
            LOG.error("===>[carPhotoSaveOrUpdate] 参数为空： tokenId={}", tokenId);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }

        ResultVO<UserTokenInfoModel> ret = UserAuthTools.getUserInfo(tokenId);
        if (ret.getStatus() != 0) {
            return ResultVO.build(ret.getStatus(), ret.getMsg());
        }

        carPhotoInfoService.insertList(list);
        return ResultVO.build(ErrorCode.SUCCESS);
    }



    /**
	 * 修改我的信息
	 */
	@Override
	public ResultVO<Object> saveOrUpdateUser(String idCode,String address,String tokenId) {
		Map<String, Object> res=new HashMap<>();
		if(tokenId==null){
            LOG.error("===>[saveOrUpdateUser] 参数为空： tokenId",tokenId);
            return ResultVO.build(ErrorCode.PARAM_EMPTY);
        }
		 ResultVO<UserTokenInfoModel> ret = UserAuthTools.getUserInfo(tokenId);
		 if (ret.getStatus() != 0) {
	            return ResultVO.build(ret.getStatus(), ret.getMsg());
	        }
		 	UserTokenInfoModel userModel = ret.getData();
	        Long userId = userModel.getId();
		
		try {
			userMgrDao.updateByUsrVO(idCode, address, userId.intValue());
			res.put("status",0);
			res.put("errMsg","操作成功");
			return ResultVO.build(ErrorCode.SUCCESS, (Object)res);
		} catch (Exception e) {
			LOG.error("===>[saveOrUpdateUser] 系统内部错误"+e.getMessage());
			return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
		}
	}
	/**
	 * App 回退
	 */
	@Override
	public ResultVO<Object> getBackWorkList(String tokenId,Integer pageNum,Integer pageSize) {
		Map<String, Object> res=new HashMap<>();
		ResultVO<UserTokenInfoModel> ret = UserAuthTools.getUserInfo(tokenId);
		 if (ret.getStatus() != 0) {
	            return ResultVO.build(ret.getStatus(), ret.getMsg());
	        }
		 	UserTokenInfoModel userModel = ret.getData();
	        Long userId = userModel.getId();
		String deptIdList = deptService.selectAllDept(userId.intValue());
		System.out.println("----deptIdList"+deptIdList);
    	List<LoanAppAppinfoVO> list = new ArrayList<>();

		//获取所有同步列表
    	List<Map<String, Object>> mapList = processNodeDao.selectAllField();
		for (Map<String, Object> map : mapList) {
			List<LoanAppAppinfoVO> syncList=new ArrayList<>();
			List<LoanAppAppinfoVO> syncTempList= mapper.selectSyncToAppDoApplyList(userId, deptIdList, map.get("syncFieldName").toString());
			for (LoanAppAppinfoVO loanApplyInfoVo : syncTempList) {
				if (loanApplyInfoVo.getSfrzStatus()==null) {
					loanApplyInfoVo.setNodeId(1);
					loanApplyInfoVo.setNodeDesc("待认证");
                }else if(loanApplyInfoVo.getYcStatus()==1) {
					loanApplyInfoVo.setNodeId(2);
					loanApplyInfoVo.setNodeDesc("验车");
				}else if(loanApplyInfoVo.getGpsInstallStatus()==2){
					loanApplyInfoVo.setNodeId(3);
					loanApplyInfoVo.setNodeDesc("GPS安装");
				}else if(loanApplyInfoVo.getStatus().equals("7100")){
					loanApplyInfoVo.setNodeId(4);
					loanApplyInfoVo.setNodeDesc("抵押登记");
				}else if(loanApplyInfoVo.getStatus().equals("11000")){
					loanApplyInfoVo.setNodeId(5);
					loanApplyInfoVo.setNodeDesc("解押登记");
				}else if(loanApplyInfoVo.getGpsUninstallStatus()==2){
					loanApplyInfoVo.setNodeId(6);
					loanApplyInfoVo.setNodeDesc("GPS拆卸");
				}
				syncList.add(loanApplyInfoVo);
			}
			
			list.addAll(syncList);
		}

		//如果是内勤，获取所有抵押申请到请款申请的状态之间未提交gps申请的节点
		UserVO user=userMapper.findById(userId);

			if(user.getRoleid().equals("3")) {
				List<LoanAppAppinfoVO> las=mapper.selectUnAppApplyGps(deptIdList);
				for (LoanAppAppinfoVO loanApplyInfoVo : las) {
					loanApplyInfoVo.setInterfaceAddress("/process/gpsInstallApply");
					loanApplyInfoVo.setShowAddress("/cust/gpsInstallApply.html");
				}
				list.addAll(las);
			}

		//获取所有驳回的列表
		//List<LoanApplyInfoVo> backList = mapper.selectBackApplyList(userId, custName, custIdNo, beginTime, endTime);
		//list.addAll(backList);

		Collections.sort(list, new Comparator<LoanAppAppinfoVO>() {
			@Override
			public int compare(LoanAppAppinfoVO o1, LoanAppAppinfoVO o2) {
				return o1.getUpdateTime().compareTo(o2.getUpdateTime());
			}
		});
		  PageHelper.startPage(pageNum, pageSize);
		 PageInfo<LoanAppAppinfoVO> pageInfo = new PageInfo<LoanAppAppinfoVO>(list);
		 int count=list.size();
         res.put("total",pageInfo.getTotal());
         res.put("totalPage",count%pageSize==0?count/pageSize:count/pageSize+1);
         res.put("rows",pageInfo.getList());
         for (LoanAppAppinfoVO loanAppAppinfoVO : pageInfo.getList()) {
			System.out.println(loanAppAppinfoVO.getCustIdNo());
		}
		return ResultVO.build(ErrorCode.SUCCESS, (Object)res);
	}

    @Override
    public ResultVO<Object> carGpsInstallInfoSaveOrUpdate(List<CarGpsDetailInfo> list, String tokenId, Long applyId, JSONObject gpsInfo) {
        if(StringUtils.isEmpty(tokenId)){
            LOG.error("===>[carGpsInstallInfoSaveOrUpdate] 参数为空： tokenId={}", tokenId);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
        ResultVO<UserTokenInfoModel> ret = UserAuthTools.getUserInfo(tokenId);
        UserTokenInfoModel userTokenInfoModel = ret.getData();
        if (ret.getStatus() != 0) {
            return ResultVO.build(ret.getStatus(), ret.getMsg());
        }
        Long custId = applyInfoService.selectById(applyId).getCustId();
        Long carId = carInfoService.selectByCusId(custId).getId();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date gpsInstallDate = null;
        try {
            gpsInstallDate = simpleDateFormat.parse(String.valueOf( gpsInfo.get("gpsInstallDate")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String groupPhotoUrl = String.valueOf(gpsInfo.get("groupPhotoUrl"));
        String remark = String.valueOf(gpsInfo.get("remark"));
        CarGpsInfo carGpsInfo = new CarGpsInfo(gpsInfo.get("id")==null ? null:Long.parseLong(String.valueOf(gpsInfo.get("id"))),carId,custId,applyId,gpsInstallDate,groupPhotoUrl,remark);
        carGpsInfoService.saveOrUpdate(carGpsInfo);//gpsInfo完成
        //删除原有
        for (CarGpsDetailInfo carGpsDetailInfo : carGpsDetailInfoService.selectByCarId(carId)) {
            carGpsDetailInfoService.delete(carGpsDetailInfo.getId());
        }

        //新增
        for (CarGpsDetailInfo carGpsDetailInfo : list) {
                /*if(carGpsDetailInfo.getIsDeleted()==1)carGpsDetailInfoService.delete(carGpsDetailInfo.getId());  */
                carGpsDetailInfo.setCarId(carId);
                carGpsDetailInfoService.saveOrUpdate(carGpsDetailInfo);
        }
        //提交

        try{
            ResultVO<Object> resultVO = processBussiness.gpsInstallComplete(applyId, userTokenInfoModel.getId(), 0, "");
            if (resultVO.getStatus().equals(99999)) {resultVO.setMsg("当前操作不在该结点");}
            return resultVO;
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
        //return ResultVO.build(ErrorCode.SUCCESS);
    }

    @Override
    public ResultVO<Object> carGpsInstallInfoShow(String tokenId, Long applyId,Map<String,Object> params) {
        if(StringUtils.isEmpty(tokenId)){
            LOG.error("===>[carGpsInstallInfoShow] 参数为空： tokenId={}", tokenId);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
        ResultVO<UserTokenInfoModel> ret = UserAuthTools.getUserInfo(tokenId);
        if (ret.getStatus() != 0) {
            return ResultVO.build(ret.getStatus(), ret.getMsg());
        }
        Long custId = applyInfoService.selectById(applyId).getCustId();
        Long carId = carInfoService.selectByCusId(custId).getId();

        List<CarGpsDetailInfo> carGpsDetailInfos = carGpsDetailInfoService.selectByCarId(carId);
        List<CarGpsDetailInfo> info0 = new ArrayList<>();
        List<CarGpsDetailInfo> info1 = new ArrayList<>();
        List<CarGpsDetailInfo> info2 = new ArrayList<>();
        for (CarGpsDetailInfo carGpsDetailInfo : carGpsDetailInfos) {
            if(carGpsDetailInfo.getIsWiredless()==0)info0.add(carGpsDetailInfo);
            if(carGpsDetailInfo.getIsWiredless()==1)info1.add(carGpsDetailInfo);
            if(carGpsDetailInfo.getIsWiredless()==2)info2.add(carGpsDetailInfo);
        }
        HashMap<String, Object> msgMap = new HashMap<>();
        CarGpsInfo carGpsInfo = carGpsInfoService.selectByApplyId(params)==null?new CarGpsInfo():carGpsInfoService.selectByApplyId(params);
        msgMap.put("info0",info0);
        msgMap.put("info1",info1);
        msgMap.put("info2",info2);
        msgMap.put("carGpsInfo",carGpsInfo);

        return ResultVO.build(ErrorCode.SUCCESS,(Object) msgMap);
    }

    @Override
    public ResultVO<Object> carGpsUnInstallInfoSaveOrUpdate(List<CarGpsUnInstallPhotoInfo> list, String tokenId, Long applyId) {
        if(StringUtils.isEmpty(tokenId)){
            LOG.error("===>[carGpsUnInstallInfoSaveOrUpdate] 参数为空： tokenId={}", tokenId);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
        ResultVO<UserTokenInfoModel> ret = UserAuthTools.getUserInfo(tokenId);
        UserTokenInfoModel userTokenInfoModel = ret.getData();
        if (ret.getStatus() != 0) {
            return ResultVO.build(ret.getStatus(), ret.getMsg());
        }
        Long custId = applyInfoService.selectById(applyId).getCustId();
        Long carId = carInfoService.selectByCusId(custId).getId();

        for (CarGpsUnInstallPhotoInfo carGpsUnInstallPhotoInfo : unInstallService.selectByCarId(String.valueOf(carId))) {
            unInstallService.delete(carGpsUnInstallPhotoInfo.getId());
        }

        for (CarGpsUnInstallPhotoInfo carGpsUnInstallPhotoInfo : list) {
//              if(carGpsUnInstallPhotoInfo.getIsDeleted()==1)unInstallService.delete(carGpsUnInstallPhotoInfo.getId());
                carGpsUnInstallPhotoInfo.setCarId(carId);
                unInstallService.saveOrUpdate(carGpsUnInstallPhotoInfo);
        }
        //提交
        try{
            ResultVO<Object> resultVO = processBussiness.gpsUnistallFinish(applyId, userTokenInfoModel.getId(), 0, "");
            if (resultVO.getStatus().equals(99999)) {resultVO.setMsg("当前操作不在该结点");}
            return resultVO;
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
        //return ResultVO.build(ErrorCode.SUCCESS);
    }

    @Override
    public ResultVO<Object> carGpsUnInstallInfoShow(String tokenId, Long applyId) {
        if(StringUtils.isEmpty(tokenId)){
            LOG.error("===>[carGpsUnInstallInfoShow] 参数为空： tokenId={}", tokenId);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
        ResultVO<UserTokenInfoModel> ret = UserAuthTools.getUserInfo(tokenId);
        if (ret.getStatus() != 0) {
            return ResultVO.build(ret.getStatus(), ret.getMsg());
        }
        Long custId = applyInfoService.selectById(applyId).getCustId();
        Long carId = carInfoService.selectByCusId(custId).getId();
        HashMap<String, Object> msgMap = new HashMap<>();
        List<CarGpsUnInstallPhotoInfo> unInstallPhotoInfos = unInstallService.selectByCarId(String.valueOf(carId));
        msgMap.put("unInstallPhotoInfos",unInstallPhotoInfos);

        return ResultVO.build(ErrorCode.SUCCESS,(Object) msgMap);
    }

    @Override
    public ResultVO<Object> carVerifyInfoSaveOrUpdate(List<CarPhotoInfo> list, String tokenId, Long applyId) {
        if(StringUtils.isEmpty(tokenId)){
            LOG.error("===>[carVerifyInfoSaveOrUpdate] 参数为空： tokenId={}", tokenId);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
        ResultVO<UserTokenInfoModel> ret = UserAuthTools.getUserInfo(tokenId);
        UserTokenInfoModel userTokenInfoModel = ret.getData();
        if (ret.getStatus() != 0) {
            return ResultVO.build(ret.getStatus(), ret.getMsg());
        }
        Long custId = applyInfoService.selectById(applyId).getCustId();
        Long carId = carInfoService.selectByCusId(custId).getId();
        //删除原有的
        for (CarPhotoInfo carPhotoInfo : carPhotoInfoService.selectByCarId(String.valueOf(carId))) {
            carPhotoInfoService.delete(carPhotoInfo.getId());
        }
        //增
        for (CarPhotoInfo carPhotoInfo : list) {
/*            Integer isDeleted = carPhotoInfo.getIsDeleted();
            if(isDeleted==1){ carPhotoInfoService.delete(carPhotoInfo.getId()); }  old*/
            carPhotoInfo.setCarId(carId);
            carPhotoInfoService.saveOrUpdate(carPhotoInfo);
        }
        //提交

        try{
            ResultVO<Object> resultVO = processBussiness.ycSubmit(applyId, userTokenInfoModel.getId(), 0, "");
            if (resultVO.getStatus().equals(99999)){ resultVO.setMsg("当前操作不在该结点");}

            return resultVO;
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
        //return ResultVO.build(ErrorCode.SUCCESS);
    }

    @Override
    public ResultVO<Object> carVerifyInfoShow(String tokenId, Long applyId) {
        if(StringUtils.isEmpty(tokenId)){
            LOG.error("===>[carVerifyInfoShow] 参数为空： tokenId={}", tokenId);
             return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
        ResultVO<UserTokenInfoModel> ret = UserAuthTools.getUserInfo(tokenId);
        if (ret.getStatus() != 0) {
            return ResultVO.build(ret.getStatus(), ret.getMsg());
        }
        Long custId = applyInfoService.selectById(applyId).getCustId();
        Long carId = carInfoService.selectByCusId(custId).getId();
        HashMap<String, Object> msgMap = new HashMap<>();
        InitCarVerifyVo initCarVerifyVo = carInfoService.selInitCarverifyByCustId(custId);
        CarVerifyInfo verifyInfo = carVerifyInfoService.selectByCarId(initCarVerifyVo.getCarId());

        if(verifyInfo !=null){
            initCarVerifyVo.setId(verifyInfo.getId());
            initCarVerifyVo.setCarCond(verifyInfo.getCarCond());
            initCarVerifyVo.setSuggestion(verifyInfo.getSuggestion());
            initCarVerifyVo.setConfigTablePhoto(verifyInfo.getConfigTablePhoto());
            initCarVerifyVo.setMaintainPhoto(verifyInfo.getMaintainPhoto());
            initCarVerifyVo.setCarAssessmentPrice(verifyInfo.getCarAssessmentPrice());
            initCarVerifyVo.setQmzk(verifyInfo.getQmzk());
            initCarVerifyVo.setNszk(verifyInfo.getNszk());
            initCarVerifyVo.setGkzk(verifyInfo.getGkzk());
        }
        msgMap.put("initCarVerifyVo",initCarVerifyVo);
        List<CarPhotoInfo> carPhotoInfos = carPhotoInfoService.selectByCarId(String.valueOf(carId));
        msgMap.put("carPhotoInfos",carPhotoInfos);
        //resultVO.setData(msgMap);

        return ResultVO.build(ErrorCode.SUCCESS,(Object) msgMap);
    }
    /**
     * 获取订单信息
     * @param params
     * @return
     */
	@Override
	public ResultVO<Object> getApplyInfor(Map<String, Object> params) {
		String tokenId=params.get("tokenId").toString();
		 ResultVO<UserTokenInfoModel> ret = UserAuthTools.getUserInfo(tokenId);
	        if (ret.getStatus() != 0) {
	            return ResultVO.build(ret.getStatus(), ret.getMsg());
	        }
	        UserTokenInfoModel userModel = ret.getData();
	        Long userId = userModel.getId();
	        UserVO user=userMapper.findById(userId);
	        if (user==null) {
	        	LOG.error("===>[getApplyInfor] 用户不存在 ");
				return ResultVO.build(ErrorCode.LOGIN_PASSWD_ERR);
			}
	        if (params.get("searchName")!=null) {
	        	String search=params.get("searchName").toString();
		        if (search.matches("[0-9]+")) {
					params.put("applyId", Integer.parseInt(search));
				}else {
					params.put("custName", search);
				}
			}

	        params.put("userId", userId);

	        AppInforPage<LoanAppAppinfoVO> page=applyInfoService.getAppHandleApplyPage(params);
	        Map<String, Object> res = new HashMap<>();
	        res.put("count", page.getCount());
	        res.put("pageNum", page.getPageNum());
	        res.put("total",page.getTotal());
	        res.put("rows",page.getRows());
            return ResultVO.build(ErrorCode.SUCCESS,(Object)res);
	}
	 /**
     * 获取订单详情信息
     * @param params
     * @return
     */
	@Override
	public ResultVO<Object> findByAppInfoById(Map<String, Object> params) {
		 String tokenId=params.get("tokenId").toString();
		 ResultVO<UserTokenInfoModel> ret = UserAuthTools.getUserInfo(tokenId);
	        if (ret.getStatus() != 0) {
	            return ResultVO.build(ret.getStatus(), ret.getMsg());
	        }
	       long applyId=Long.parseLong(params.get("applyId").toString());
			return ResultVO.build(ErrorCode.SUCCESS,(Object)applyInfoService.selectAppAppinforById(applyId));
	}

	@Override
	public ResultVO<Object> getUserORC(Map<String, Object> params) {
		 String tokenId=params.get("tokenId").toString();
		ResultVO<UserTokenInfoModel> ret = UserAuthTools.getUserInfo(tokenId);
		 if (ret.getStatus() != 0) {
	            return ResultVO.build(ret.getStatus(), ret.getMsg());
	        }
		 	UserTokenInfoModel userModel = ret.getData();
	        Long userId = userModel.getId();
	        params.put("id", userId);
	        CustomerInfoVo cust=userMgrDao.findByOCR(params);
	        Map<String, Object> res=new HashMap<>();
	        if (cust!=null) {
	        	res.put("res", true);
	        	return	ResultVO.build(ErrorCode.SUCCESS,(Object)res);
			}else {
				res.put("res", false);
				return ResultVO.build(ErrorCode.IDENTITY_AUTH_FAILED,(Object)res);
			}

	}

    @Override
    public ResultVO<Object> carBussMortgageInfoSavaOrUpdate(String tokenId, Long applyId, Map<String, Object> params) {
        if(StringUtils.isEmpty(tokenId)){
            LOG.error("===>[carBussMortgageInfoSavaOrUpdate] 参数为空： tokenId={}", tokenId);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
        ResultVO<UserTokenInfoModel> ret = UserAuthTools.getUserInfo(tokenId);
        UserTokenInfoModel userTokenInfoModel = ret.getData();
        if (ret.getStatus() != 0) {
            return ResultVO.build(ret.getStatus(), ret.getMsg());
        }
        JSONObject jsonObject=(JSONObject)JSON.parse(params.get("carBussMortgageInfo").toString());
        //JSONObject jsonObject = (JSONObject) params.get("carBussMortgageInfo");
        //JSON.parse(params.get("carBussMortgageInfo"))
        CarBussMortgageInfo carBussMortgageInfo = carBussMortgageInfoService.selectByApplyId(applyId);
        if(jsonObject.get("registerPhotoUrl1")!=null){carBussMortgageInfo.setRegisterPhotoUrl1(String.valueOf((jsonObject.get("registerPhotoUrl1"))));}
        if(jsonObject.get("registerPhotoUrl2")!=null){carBussMortgageInfo.setRegisterPhotoUrl2(String.valueOf((jsonObject.get("registerPhotoUrl2"))));}
        if(jsonObject.get("registerPhotoUrl3")!=null){carBussMortgageInfo.setRegisterPhotoUrl3(String.valueOf((jsonObject.get("registerPhotoUrl3"))));}
        if(jsonObject.get("registerPhotoUrl4")!=null){carBussMortgageInfo.setRegisterPhotoUrl4(String.valueOf((jsonObject.get("registerPhotoUrl4"))));}
        if(jsonObject.get("proxyBookUrl")!=null){carBussMortgageInfo.setProxyBookUrl(String.valueOf((jsonObject.get("proxyBookUrl"))));}
        if(jsonObject.get("mortgageContractUrl")!=null){carBussMortgageInfo.setMortgageContractUrl(String.valueOf((jsonObject.get("mortgageContractUrl"))));}
        if(jsonObject.get("certPhotoUrl")!=null){carBussMortgageInfo.setCertPhotoUrl(String.valueOf((jsonObject.get("certPhotoUrl"))));}
        //抵押受理小票附件
        if(jsonObject.get("billAttachUrl")!=null){carBussMortgageInfo.setBillAttachUrl(String.valueOf((jsonObject.get("billAttachUrl"))));}
        carBussMortgageInfoService.saveOrUpdate(carBussMortgageInfo);
        //提交


        if(carBussMortgageInfo == null){ return ResultVO.build(ErrorCode.NO_SAVE_APPLY); }
        try{
            ResultVO<Object> resultVO = processBussiness.mortgageCarApply(applyId, userTokenInfoModel.getId(), 0, "");
            if (resultVO.getStatus().equals(99999)) {resultVO.setMsg("当前操作不在该结点");}
            return resultVO;
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
        //return ResultVO.build(ErrorCode.SUCCESS);
    }

    @Override
    public ResultVO<Object> carBussMortgageInfoShow(String tokenId, Long applyId) {
        if(StringUtils.isEmpty(tokenId)){
            LOG.error("===>[carBussMortgageInfoShow] 参数为空： tokenId={}", tokenId);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
        ResultVO<UserTokenInfoModel> ret = UserAuthTools.getUserInfo(tokenId);
        if (ret.getStatus() != 0) {
            return ResultVO.build(ret.getStatus(), ret.getMsg());
        }
        CarBussMortgageInfo carBussMortgageInfo = carBussMortgageInfoService.selectByApplyId(applyId);

        return ResultVO.build(ErrorCode.SUCCESS,(Object) carBussMortgageInfo);
    }

    @Override
    public ResultVO<Object> carDetentionInfoSaveOrUpdate(String tokenId, Long applyId, Map<String, Object> params) {
        if(StringUtils.isEmpty(tokenId)){
            LOG.error("===>[carDetentionInfoSaveOrUpdate] 参数为空： tokenId={}", tokenId);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
        ResultVO<UserTokenInfoModel> ret = UserAuthTools.getUserInfo(tokenId);
        UserTokenInfoModel userTokenInfoModel = ret.getData();
        if (ret.getStatus() != 0) {
            return ResultVO.build(ret.getStatus(), ret.getMsg());
        }

        JSONObject jsonObject=(JSONObject)JSON.parse(params.get("carDetentionInfo").toString());
        CarDetentionInfo carDetentionInfo = carDetentionInfoService.selectByApplyId(applyId);
        CarBussMortgageInfo carBussMortgageInfo = carBussMortgageInfoService.selectByApplyId(applyId);
        if(jsonObject.get("settleAttachUrl")!=null){carDetentionInfo.setSettleAttachUrl(String.valueOf((jsonObject.get("settleAttachUrl"))));}
        if(jsonObject.get("proxyBookUrl")!=null){carDetentionInfo.setProxyBookUrl(String.valueOf((jsonObject.get("proxyBookUrl"))));}
        if(jsonObject.get("certPhotoUrl")!=null){carDetentionInfo.setCertPhotoUrl(String.valueOf((jsonObject.get("certPhotoUrl"))));}
        //抵押受理小票附件
        if(jsonObject.get("billAttachUrl")!=null){carBussMortgageInfo.setBillAttachUrl(String.valueOf((jsonObject.get("billAttachUrl"))));}
        if(jsonObject.get("registerPhotoUrl5")!=null){carBussMortgageInfo.setRegisterPhotoUrl5(String.valueOf((jsonObject.get("registerPhotoUrl5"))));}
        if(jsonObject.get("registerPhotoUrl6")!=null){carBussMortgageInfo.setRegisterPhotoUrl6(String.valueOf((jsonObject.get("registerPhotoUrl6"))));}
        if(jsonObject.get("registerPhotoUrl7")!=null){carBussMortgageInfo.setRegisterPhotoUrl7(String.valueOf((jsonObject.get("registerPhotoUrl7"))));}
        if(jsonObject.get("registerPhotoUrl8")!=null){carBussMortgageInfo.setRegisterPhotoUrl8(String.valueOf((jsonObject.get("registerPhotoUrl8"))));}
        carBussMortgageInfoService.saveOrUpdate(carBussMortgageInfo);
        carDetentionInfoService.saveOrUpdate(carDetentionInfo);

        CarDetentionInfo carDetentionInfo1 = carDetentionInfoService.selectByApplyId(applyId);
        carDetentionInfo1.setSettleConfirem(1);
        carDetentionInfo1.setSettleDate(new Date());
        try{
            ResultVO<Object> resultVO = processBussiness.detentionCarApply(applyId, userTokenInfoModel.getId(), 0, "");
            if (resultVO.getStatus().equals(99999)) {resultVO.setMsg("当前操作不在该结点");}
            return resultVO;
        }catch(Exception e){
            LOG.error(e.getMessage(), e);
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
        //return ResultVO.build(ErrorCode.SUCCESS);
    }

    @Override
    public ResultVO<Object> carDetentionInfoShow(String tokenId, Long applyId) {
        if(StringUtils.isEmpty(tokenId)){
            LOG.error("===>[carDetentionInfoShow] 参数为空： tokenId={}", tokenId);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
        ResultVO<UserTokenInfoModel> ret = UserAuthTools.getUserInfo(tokenId);
        if (ret.getStatus() != 0) {
            return ResultVO.build(ret.getStatus(), ret.getMsg());
        }

        CarDetentionInfo carDetentionInfo = carDetentionInfoService.selectByApplyId(applyId);
        //解押申请四张照片
        CarBussMortgageInfo carBussMortgageInfo = carBussMortgageInfoService.selectByApplyId(applyId);
        String registerPhotoUrl5 = carBussMortgageInfo.getRegisterPhotoUrl5();
        String registerPhotoUrl6 = carBussMortgageInfo.getRegisterPhotoUrl6();
        String registerPhotoUrl7 = carBussMortgageInfo.getRegisterPhotoUrl7();
        String registerPhotoUrl8 = carBussMortgageInfo.getRegisterPhotoUrl8();
        CarDetentionInfoVo carDetentionInfoVo = new CarDetentionInfoVo(carDetentionInfo, registerPhotoUrl5, registerPhotoUrl6, registerPhotoUrl7, registerPhotoUrl8);
        return ResultVO.build(ErrorCode.SUCCESS,(Object)carDetentionInfoVo);
    }

    @Override
    public ResultVO<Object> carFAQ(String tokenId) {
        if(StringUtils.isEmpty(tokenId)){
            LOG.error("===>[carFAQ] 参数为空： tokenId={}", tokenId);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
        ResultVO<UserTokenInfoModel> ret = UserAuthTools.getUserInfo(tokenId);
        if (ret.getStatus() != 0) {
            return ResultVO.build(ret.getStatus(), ret.getMsg());
        }
        HashMap<String, Object> msgMap = new HashMap<>();

        return ResultVO.build(ErrorCode.SUCCESS,(Object)msgMap);
    }
    
   /**
    * 活体认证
    */
	@Override
	public ResultVO<Object> saveOrUpdateLivingBody(Map<String, Object> params) {
		LivenessAuthInfo authInfo=new LivenessAuthInfo();
		Long applyId=Long.parseLong(params.get("applyId").toString());
		ApplyInfo applyInfo=applyInfoService.selectById(applyId);
		if (applyInfo!=null) {
			authInfo.setCustId(applyInfo.getCustId());
		}else {
			return ResultVO.build(ErrorCode.PARAM_ERROR);
		}
		Map<String, Object> map=new HashMap<>();
		//App中暂时只有主借人的活体认证因此 type=0
		map.put("type", 0);
		map.put("applyId",applyId);
		LivenessAuthInfo livenInfo=livenssService.selectByApplyIdAndType(map);
		if (livenInfo!=null) {
			return ResultVO.build(ErrorCode.LIVENESS_AUTH_REPEAT);
		}else {
			authInfo.setApplyId(applyId);
			authInfo.setCreateTime(new Date());
			authInfo.setType(0);
			authInfo.setIsLivenessAuth(1);
			//眨眼
			authInfo.setLivenessBlinkPhotoUrl(params.get("blink").toString());
			//点头
			authInfo.setLivenessNodPhotoUrl(params.get("nod").toString());
			//张嘴
			authInfo.setLivenessMouthPhotoUrl(params.get("mouth").toString());
			//摇头
			authInfo.setLivenessYawPhotoUrl(params.get("yaw").toString());
			livenssService.saveOrUpdate(authInfo);
			//同时修改订单表的活体认证
			applyInfo.setSfrzStatus(9999);
			applyInfoService.updateWithOutNull(applyInfo);
			return ResultVO.build(ErrorCode.SUCCESS);
		}
		
		
	}

@Override
public ResultVO<Object> updateMobilePwd(Map<String, Object> params) {
	
	try {
		//首先判断短信验证码是否正确
		String mobile=params.get("mobile").toString();
		String smsCode=params.get("smsCode").toString();
		String smsId=params.get("smsId").toString();
		
		if (!validateSmsCode(mobile,smsCode,smsId)) {
			 LOG.error("===>[updateMobilePwd] mobile={} 验证码错误", mobile);
		     return ResultVO.build(ErrorCode.SMS_CODE_ERROR);
		}
		String pwd=params.get("pwd").toString();
		String reqpwd=params.get("reqpwd").toString();
		if (!pwd.equals(reqpwd)) {
			 LOG.error("===>[updateMobilePwd] 密码和确认密码不一致", pwd,reqpwd);
		     return ResultVO.build(ErrorCode.PWD_NOT_REQPWD);
		}
		String account=params.get("account").toString();
		try {
			User user=userMgrDao.getByAccount(account);
			if (user!=null) {
				LOG.error("===>[updateMobilePwd] 用户名不存在"+account);
				return ResultVO.build(ErrorCode.LOGIN_PASSWD_ERR);
			}
			 String newMd5 = ShiroKit.md5(pwd, user.getSalt());
			userMgrDao.changePwd(user.getId(), newMd5);
			return ResultVO.build(ErrorCode.SUCCESS);
		} catch (Exception e) {
			LOG.error("===>[updateMobilePwd] exception e={}", e);
			return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
		}
	} catch (Exception e) {
		LOG.error("===>[updateMobilePwd] 接口参数错误", e);
	     return ResultVO.build(ErrorCode.PARAM_ERROR);
	}
	
}


}
