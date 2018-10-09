package com.moerlong.carloan.modular.app.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.common.constant.tips.SuccessTip;
import com.moerlong.carloan.common.exception.BizExceptionEnum;
import com.moerlong.carloan.common.exception.BussinessException;
import com.moerlong.carloan.common.persistence.dao.UserMapper;
import com.moerlong.carloan.common.persistence.model.User;
import com.moerlong.carloan.common.vo.ErrorCode;
import com.moerlong.carloan.common.vo.ResultVO;
import com.moerlong.carloan.core.shiro.ShiroKit;
import com.moerlong.carloan.modular.app.business.AppBussiness;
import com.moerlong.carloan.modular.app.utils.UserAuthTools;
import com.moerlong.carloan.modular.app.utils.UserTokenInfoModel;
import com.moerlong.carloan.modular.car.entity.*;
import com.moerlong.carloan.modular.loan.entity.vo.LoanAppAppinfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Api(tags = { "App相关类" })
public class AppLoginController {
    private static final Logger LOG = LoggerFactory.getLogger(AppLoginController.class);
    
    protected static SuccessTip SUCCESS_TIP = new SuccessTip();

    @Value("${file.identity_pic_path}")
    private String idPicPath;
    @Value("${file.liveness_pic_path}")
    private String livenessPicPath;
    @Value("${file.protobuf_path}")
    private String protobufPath;
    @Value("${file.vehicle_pic_path}")
    private String vehiclePicPath;
    @Value("${file.register_pic_path}")
    private String registerPicPath;
    @Value("${file.strong_insure_pic_path}")
    private String strongPicPath;
    @Value("${file.buss_insure_pic_path}")
    private String bussPicPath;


    @Value("${file.identity_pic_url}")
    private String idPicUrl;
    @Value("${file.identity_pic_urlsx}")
    private String idPicUrlsx;
    @Value("${file.liveness_pic_url}")
    private String livenessPicUrl;
    @Value("${file.protobuf_url}")
    private String protobufUrl;
    @Value("${file.vehicle_pic_url}")
    private String vehiclePicUrl;
    @Value("${file.register_pic_url}")
    private String registerPicUrl;
    @Value("${file.strong_insure_pic_url}")
    private String strongPicUrl;
    @Value("${file.buss_insure_pic_url}")
    private String bussPicUrl;

    @Resource
    private AppBussiness appBussiness;

    @Resource
    private UserMapper userMapper;

    /**
     * 用户密码登录
     * @return
     */
    @ApiOperation(value = "用户密码登录")
    @ApiImplicitParam(paramType = "body", name = "params", required = true, dataType = "Map", value = "参数")
    @RequestMapping(value = "/app/user/loginWithPwd", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<Object> loginWithPwd(@RequestBody Map<String, Object> params){
        try {
            String account = params.get("account").toString();
            String pwd = params.get("pwd").toString();

            return appBussiness.loginWithPwd(account, pwd);
        }catch(Exception e){
            LOG.error("===>[loginWithPwd] 参数异常 e={}", e);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
    }
    
    /**
     * 密码修改
     */
    @ApiOperation(value = "密码修改")
    @RequestMapping(value ="/app/user/changePwd",method = RequestMethod.POST)
    @ApiImplicitParam(paramType = "body", name = "params", required = true, dataType = "Map", value = "参数")
    @ResponseBody
    public Object changePwd(@RequestBody Map<String, Object> params) {
    	String tokenId = params.get("tokenId").toString();
    	String newPwd=params.get("newPwd").toString();
    	String rePwd=params.get("rePwd").toString();
    	String oldPwd=params.get("oldPwd").toString();
        if (!newPwd.equals(rePwd)) {
            throw new BussinessException(BizExceptionEnum.TWO_PWD_NOT_MATCH);
        }
        ResultVO<UserTokenInfoModel> ret = UserAuthTools.getUserInfo(tokenId);
        UserTokenInfoModel userModel = ret.getData();
        User user = userMapper.selectById(userModel.getId());
        String oldMd5 = ShiroKit.md5(oldPwd, user.getSalt());
        if (user.getPassword().equals(oldMd5)) {
            String newMd5 = ShiroKit.md5(newPwd, user.getSalt());
            user.setPassword(newMd5);
            user.updateById();
            
            return ResultVO.build(ErrorCode.SUCCESS);
        } else {
        	return ResultVO.build(ErrorCode.OLD_PWD_NOT_RIGHT);
            //throw new BussinessException(BizExceptionEnum.OLD_PWD_NOT_RIGHT);
        }
    }

    /**
     * 用户(商户)登出
     * @return
     */
    @ApiOperation(value = "注销")
    @ApiImplicitParam(paramType = "body", name = "params", required = true, dataType = "map", value = "参数")
    @RequestMapping(value ="/app/logout", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<Object> logout(@RequestBody Map<String, Object> params){
        try {
            String tokenId = params.get("tokenId").toString();
            return appBussiness.logout(tokenId);
        }catch(Exception e){
            LOG.error("===>[logout] 参数异常 e={}", e);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
    }

    /**
     * 获取用户信息
     * @return
     */
    @ApiOperation(value = "获取用户信息")
    @ApiImplicitParam(paramType = "body", name = "params", required = true, dataType = "map", value = "参数")
    @RequestMapping(value="/app/user/getUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<Object> getUserInfo(@RequestBody Map<String, Object> params){
        try{
            String tokenId = params.get("tokenId").toString();
            return appBussiness.getUserInfo(tokenId);
        }catch(Exception e){
            LOG.error("===>[getUserInfo] 参数异常 e={}", e);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
    }

    /**
     * 发送验证码
     flag  0--注册  1--重置密码
     * @return
     */
    @ApiOperation(value = "发送验证码")
    @ApiImplicitParam(paramType = "body", name = "params", required = true, dataType = "map", value = "参数")
    @RequestMapping(value="/app/sendVerifyCode", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<Object> sendVerifyCode(@RequestBody Map<String, Object> params){
        try {
            String mobile = params.get("mobile").toString();
            Integer flag = Integer.parseInt(params.get("flag").toString());
            return appBussiness.sendVerifyCode(mobile, flag);
        }catch(Exception e){
            LOG.error("===>[sendVerifyCode] 参数异常 e={}", e);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
    }

    /**
     * 短信验证码登录
     * @return
     */
    @ApiOperation(value = "短信验证码登录")
    @ApiImplicitParam(paramType = "body", name = "params", required = true, dataType = "map", value = "参数")
    @RequestMapping(value="/app/loginWithVerifyCode", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<Object> loginWithVerifyCode(@RequestBody Map<String, Object> params){
        try {
            String mobile = params.get("mobile").toString();
            String smsCode = params.get("smsCode").toString();
            String smsId = params.get("smsId").toString();
            return appBussiness.loginWithVerifyCode(mobile, smsCode, smsId);
        }catch(Exception e){
            LOG.error("===>[loginWithVerifyCode] 参数异常 e={}", e);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
    }

    /**
     * 获取待办列表
     * @return
     */
    @ApiOperation(value = "获取待办列表")
    @ApiImplicitParam(paramType = "body", name = "params", required = true, dataType = "map", value = "参数")
    @RequestMapping(value="/app/getTodoWorkList", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<Object> getTodoWorkList(@RequestBody Map<String, Object> params){
        try {
            String tokenId = params.get("tokenId").toString();
            Integer num=Integer.parseInt(params.get("pageNum").toString());
            int size=Integer.parseInt(params.get("pageSize").toString());
            return  appBussiness.getTodoWorkList(tokenId, num, size);
        }catch(Exception e){
            LOG.error("===>[getTodoWorkList] 参数异常 e={}", e);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
    }
   
    /**
     * 获取验车师初始化信息
     * @param params
     * @return
     */
    @ApiOperation(value = "获取验车师初始化信息")
    @ApiImplicitParam(paramType = "body", name = "params", required = true, dataType = "map", value = "参数")
    @RequestMapping(value="/app/carInfo/selInitCarverifyByCustId", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<Object> selInitCarverifyByCustId(@RequestBody Map<String, Object> params){
        try{
            String tokenId = params.get("tokenId").toString();
            Long applyId = Long.parseLong(params.get("applyId").toString());
            return appBussiness.selInitCarverifyByCustId(tokenId,applyId);
        }catch(Exception e){
            LOG.error("===>[selInitCarverifyByCustId] 参数异常 e={}", e);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
    }
   
    /**
     * 车辆图片保存或更新
     * @param params
     * @return
     */
    @ApiOperation(value = "车辆图片保存或更新")
    @ApiImplicitParam(paramType = "body", name = "params", required = true, dataType = "CarPhotoInfo", value = "明细")
    @RequestMapping(value = "/app/carPhotoInfo/saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<Object> carPhotoSaveOrUpdate(@RequestBody Map<String,Object> params) {
        try {
            LOG.error("===>[carPhotoSaveOrUpdate] params={}", params);
            String tokenId = params.get("tokenId").toString();
            JSONArray jsonArray = (JSONArray)params.get("list");
            List<CarPhotoInfo> list = new ArrayList<>();
            for (int i=0;i<jsonArray.size();i++){
                CarPhotoInfo carPhotoInfo = new CarPhotoInfo();
//                carPhotoInfo.setBigClassId(Long.parseLong(((JSONObject)jsonArray.get(i)).get("bigClassId").toString()));//图片大类，暂时没有，先不用set
                carPhotoInfo.setCarId(Long.parseLong(((JSONObject)jsonArray.get(i)).get("carId").toString()));
                carPhotoInfo.setPhotoName(((JSONObject)jsonArray.get(i)).get("photoName").toString());
                carPhotoInfo.setPhotoUrl(((JSONObject)jsonArray.get(i)).get("photoUrl").toString());
                carPhotoInfo.setCreateTime(new Date());
                carPhotoInfo.setIsDeleted(0);
                list.add(carPhotoInfo);
            }
            return appBussiness.carPhotoSave(list,tokenId);
        } catch (Throwable e) {
            LOG.error("===>[carPhotoSaveOrUpdate] 参数异常 e={}", e);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
    }

	/**
	 * GPS安装信息提交或更新 TODO
	 * @param params
	 * @return
	 */
    @ApiOperation(value = "GPS安装信息提交或更新")
    @ApiImplicitParam(paramType = "body", name = "params", required = true, dataType = "carGpsInstallInfoSaveOrUpdate", value = "明细")
    @RequestMapping(value = "/app/carGpsInstallInfo/saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<Object> carGpsInstallInfoSaveOrUpdate(@RequestBody Map<String,Object> params){
        try {
            LOG.error("===>[carGpsInstallInfoSaveOrUpdate] params={}", params);
            Long applyId = (params.get("applyId") == null) ? null : Long.parseLong(String.valueOf(params.get("applyId")));
            String tokenId = String.valueOf(params.get("tokenId"));
            //JSONArray jsonArray = (JSONArray)params.get("gpsGpsDetailInfoList"); old
            JSONArray jsonArray = JSONArray.parseArray(String.valueOf(params.get("gpsGpsDetailInfoList")));
            List<CarGpsDetailInfo> list = new ArrayList<>();
            for (int i=0;i<jsonArray.size();i++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                Long strId = jsonObject.get("id")==null ? null:Long.parseLong(String.valueOf(jsonObject.get("id")));
                Integer isWiredless = jsonObject.get("isWiredless")==null ? null:Integer.parseInt(String.valueOf(jsonObject.get("isWiredless")));
                String gpsWiredNo = jsonObject.get("gpsWiredNo")==null ? null:String.valueOf(jsonObject.get("gpsWiredNo"));
                String remark = jsonObject.get("remark")==null ? null:String.valueOf(jsonObject.get("remark"));
                String gpsPhotoUrl = jsonObject.get("gpsPhotoUrl")==null ? null:String.valueOf(jsonObject.get("gpsPhotoUrl"));
                Integer isDeleted = jsonObject.get("isDeleted")==null ? null:Integer.parseInt(String.valueOf(jsonObject.get("isDeleted")));
                list.add(new CarGpsDetailInfo(strId,isWiredless,gpsWiredNo,gpsPhotoUrl,remark,isDeleted));
            }
            return appBussiness.carGpsInstallInfoSaveOrUpdate(list,tokenId,applyId,(JSONObject) JSONObject.parse(params.get("gpsInfo").toString()));
        } catch (Throwable e) {
            LOG.error("===>[carGpsInstallInfoSaveOrUpdate] 参数异常 e={}", e);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
    }

    /**
     * GPS安装信息
     * */
    @ApiOperation(value = "GPS安装信息")
    @ApiImplicitParam(paramType = "body", name = "params", required = true, dataType = "map", value = "参数")
    @RequestMapping(value="/app/carGpsInstallInfo/show", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<Object> carGpsInstallInfoShow(@RequestBody Map<String, Object> params){
        try {
            LOG.error("===>[carGpsInstallInfoShow] params={}", params);
            String tokenId = String.valueOf(params.get("tokenId"));
            Long applyId = (params.get("applyId") == null) ? null : Long.parseLong(String.valueOf(params.get("applyId")));
            return  appBussiness.carGpsInstallInfoShow(tokenId,applyId,params);
        } catch (NumberFormatException e) {
            LOG.error("===>{updateByUser} 参数异常："+e.getMessage());
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
    }

    /**
     * GPS拆卸信息
     * */
    @ApiOperation(value = "GPS拆卸信息")
    @ApiImplicitParam(paramType = "body", name = "params", required = true, dataType = "map", value = "参数")
    @RequestMapping(value="/app/carGpsUninstallInfo/show", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<Object> carGpsUninstallInfoShow(@RequestBody Map<String, Object> params){
        try {
            LOG.error("===>[carGpsUninstallInfoShow] params={}", params);
            String tokenId = String.valueOf(params.get("tokenId"));
            Long applyId = (params.get("applyId") == null) ? null : Long.parseLong(String.valueOf(params.get("applyId")));

            return  appBussiness.carGpsUnInstallInfoShow(tokenId,applyId);
        } catch (NumberFormatException e) {
            LOG.error("===>{updateByUser} 参数异常："+e.getMessage());
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
    }

    /**
     * GPS拆卸信息提交 TODO
     * @param params
     * @return
     */
    @ApiOperation(value = "GPS拆卸信息提交")
    @ApiImplicitParam(paramType = "body", name = "params", required = true, dataType = "carGpsUninstallInfo", value = "明细")
    @RequestMapping(value = "/app/carGpsUninstallInfo/saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<Object> carGpsUninstallInfo(@RequestBody Map<String,Object> params) {
        try {
            LOG.error("===>[carGpsUninstallInfoSaveOrUpdate] params={}", params);
            Long applyId = (params.get("applyId") == null) ? null : Long.parseLong(String.valueOf(params.get("applyId")));
            String tokenId = String.valueOf(params.get("tokenId"));
            //JSONArray jsonArray = (JSONArray)params.get("gpsUninstallInfoList"); old
            JSONArray jsonArray = JSONArray.parseArray(String.valueOf(params.get("gpsUninstallInfoList")));

            List<CarGpsUnInstallPhotoInfo> list = new ArrayList<>();

            for (int i=0;i<jsonArray.size();i++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                Long id = jsonObject.get("id")==null ? null:Long.parseLong(String.valueOf(jsonObject.get("id")));
                Long bigClassId = jsonObject.get("bigClassId")==null ? null: Long.parseLong(String.valueOf(jsonObject.get("bigClassId")));
                String photoName = jsonObject.get("photoName")==null ? null: String.valueOf(jsonObject.get("photoName"));
                String photoUrl = jsonObject.get("photoUrl")==null ? null: String.valueOf(jsonObject.get("photoUrl"));
                String remark = jsonObject.get("remark")==null ? null: String.valueOf(jsonObject.get("remark"));
                Integer isDeleted = jsonObject.get("isDeleted")==null? null: Integer.parseInt(String.valueOf(jsonObject.get("isDeleted")));
                list.add(new CarGpsUnInstallPhotoInfo(id,photoName,photoUrl,remark,isDeleted,bigClassId));
            }

            return appBussiness.carGpsUnInstallInfoSaveOrUpdate(list,tokenId,applyId);
        } catch (Throwable e) {
            LOG.error("===>[carGpsUninstallInfoSaveOrUpdate] 参数异常 e={}", e);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
    }

    /**
     * 验车信息提交 TODO
     * @param params
     * @return
     */
    @ApiOperation(value = "验车信息提交")
    @ApiImplicitParam(paramType = "body", name = "params", required = true, dataType = "map", value = "参数")
    @RequestMapping(value="/app/carVerifyInfo/saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<Object> carVerifyInfoSaveOrUpdate(@RequestBody Map<String, Object> params){
        try {
            LOG.error("===>[carVerifyInfoSaveOrUpdate] params={}", params);
            String tokenId = String.valueOf(params.get("tokenId"));
            Long applyId = (params.get("applyId") == null) ? null : Long.parseLong(String.valueOf(params.get("applyId")));

            //JSONArray jsonArray = (JSONArray)params.get("photoInfoList"); old
            JSONArray jsonArray = JSONArray.parseArray(String.valueOf(params.get("photoInfoList")));

            List<CarPhotoInfo> list = new ArrayList<>();

            for (int i=0;i<jsonArray.size();i++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                Long id = jsonObject.get("id")==null ? null: Long.parseLong(jsonObject.get("id").toString());
                Long bigClassId = jsonObject.get("bigClassId")==null ? null: Long.parseLong(jsonObject.get("bigClassId").toString());
                String photoName = jsonObject.get("photoName")==null ? null: jsonObject.get("photoName").toString();
                String photeUrl = jsonObject.get("photoUrl")==null ? null: jsonObject.get("photoUrl").toString();
                String remark = jsonObject.get("remark")==null ? null: jsonObject.get("remark").toString();
                Integer isDeleted = jsonObject.get("isDeleted")==null ? null: Integer.parseInt(String.valueOf(jsonObject.get("isDeleted")));
                list.add(new CarPhotoInfo(id,photoName,photeUrl,remark,isDeleted,bigClassId));
            }
            return  appBussiness.carVerifyInfoSaveOrUpdate(list,tokenId,applyId);
        } catch (NumberFormatException e) {
            LOG.error("===>{carVerifyInfoSaveOrUpdate} 参数异常："+e.getMessage());
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
    }

    /**
     *  验车信息
     * */
    @ApiOperation(value = "验车信息")
    @ApiImplicitParam(paramType = "body", name = "params", required = true, dataType = "map", value = "参数")
    @RequestMapping(value="/app/carVerifyInfo/show", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<Object> carVerifyInfoShow(@RequestBody Map<String, Object> params){
        try {
            LOG.error("===>[carVerifyInfoShow] params={}", params);
            String tokenId = String.valueOf(params.get("tokenId"));
            Long applyId = (params.get("applyId") == null) ? null : Long.parseLong(String.valueOf(params.get("applyId")));

            return  appBussiness.carVerifyInfoShow(tokenId,applyId);
        } catch (NumberFormatException e) {
            LOG.error("===>{carVerifyInfoShow} 参数异常："+e.getMessage());
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
    }

    /**
     *  抵押申请提交 TODO
     * */
    @ApiOperation(value = "抵押申请提交")
    @ApiImplicitParam(paramType = "body", name = "params", required = true, dataType = "map", value = "明细")
    @RequestMapping(value = "/app/carBussMortgageInfo/saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<Object> carBussMortgageInfoSaveOrUpdate(@RequestBody Map<String,Object> params) {
        try {
            LOG.error("===>[carGpsUninstallInfoSaveOrUpdate] params={}", params);
            Long applyId = (params.get("applyId") == null) ? null : Long.parseLong(String.valueOf(params.get("applyId")));
            String tokenId = String.valueOf(params.get("tokenId"));
            return appBussiness.carBussMortgageInfoSavaOrUpdate(tokenId,applyId,params);
        } catch (Throwable e) {
            LOG.error("===>[carGpsUninstallInfoSaveOrUpdate] 参数异常 e={}", e);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
    }

    /**
     * 抵押登记信息
     * */
    @ApiOperation(value = "抵押登记信息")
    @ApiImplicitParam(paramType = "body", name = "params", required = true, dataType = "map", value = "参数")
    @RequestMapping(value="/app/carBussMortgageInfo/show", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<Object> carBussMortgageInfoShow(@RequestBody Map<String, Object> params){
        try {
            LOG.error("===>[carGpsUninstallInfoShow] params={}", params);
            String tokenId = String.valueOf(params.get("tokenId"));
            Long applyId = (params.get("applyId") == null) ? null : Long.parseLong(String.valueOf(params.get("applyId")));

            return  appBussiness.carBussMortgageInfoShow(tokenId,applyId);
        } catch (NumberFormatException e) {
            LOG.error("===>{carGpsUninstallInfoShow} 参数异常："+e.getMessage());
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
    }

    /**
     * 解押申请提交 TODO
     * */
    @ApiOperation(value = "解押申请提交")
    @ApiImplicitParam(paramType = "body", name = "params", required = true, dataType = "map", value = "明细")
    @RequestMapping(value = "/app/carDetentionInfo/saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<Object> carDetentionInfoSaveOrUpdate(@RequestBody Map<String,Object> params) {
        try {
            LOG.error("===>[carDetentionInfoSaveOrUpdate] params={}", params);
            Long applyId = (params.get("applyId") == null) ? null : Long.parseLong(String.valueOf(params.get("applyId")));
            String tokenId = String.valueOf(params.get("tokenId"));
            return appBussiness.carDetentionInfoSaveOrUpdate(tokenId,applyId,params);
        } catch (Throwable e) {
            LOG.error("===>[carDetentionInfoSaveOrUpdate] 参数异常 e={}", e);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
    }


    /**
     * 解押申请信息
     * */
    @ApiOperation(value = "解押登记信息")
    @ApiImplicitParam(paramType = "body", name = "params", required = true, dataType = "map", value = "参数")
    @RequestMapping(value="/app/carDetentionInfo/show", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<Object> carDetentionInfoShow(@RequestBody Map<String, Object> params){
        try {
            LOG.error("===>[carDetentionInfoShow] params={}", params);
            String tokenId = String.valueOf(params.get("tokenId"));
            Long applyId = (params.get("applyId") == null) ? null : Long.parseLong(String.valueOf(params.get("applyId")));

            return  appBussiness.carDetentionInfoShow(tokenId,applyId);
        } catch (NumberFormatException e) {
            LOG.error("===>{carDetentionInfoShow} 参数异常："+e.getMessage());
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
    }

    /**
     * 常见问题
     * */
    @ApiOperation(value = "常见问题")
    @ApiImplicitParam(paramType = "body", name = "params", required = true, dataType = "map", value = "参数")
    @RequestMapping(value="/app/FAQ", method = RequestMethod.POST)
    @ResponseBody
    private ResultVO<Object> carFAQ(@RequestBody Map<String, Object> params){
        try {
            LOG.error("===>[carDetentionInfoShow] params={}", params);
            String tokenId = String.valueOf(params.get("tokenId"));

            return  appBussiness.carFAQ(tokenId);
        } catch (NumberFormatException e) {
            LOG.error("===>{carDetentionInfoShow} 参数异常："+e.getMessage());
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
    }


    /**
     * App端上传文件
     * @param myfile
     * @return
     */
    private Object saveFile(MultipartFile myfile, String fileSubPath, String fileUrl){
        String FilePath ="";
        if (myfile != null && !myfile.isEmpty()) {
            // 获取文件的原始名称
            String oldName = myfile.getOriginalFilename();
            // 获取文件大小
            Long fileSize = myfile.getSize();
            // 获取文件的原始流
            // f.getInputStream()

            // 组装文件存储路径
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = sdf.format(new Date());
            String filePath = fileSubPath + File.separator + dateStr;

            // 创建目录
            File f = new File(filePath);
            if (!f.exists()) {
                f.mkdirs();
            }

            // 生成一个新的不会重复的文件名
            // 1.获取后缀
            String suffix = FilenameUtils.getExtension(myfile.getOriginalFilename());
            // 2.生成新的文件名
            String newFileName = UUID.randomUUID().toString() + "." + suffix;
            // 把上传的文件存储指定位置
            try{
                myfile.transferTo(new File(f, newFileName));
            }catch (Exception e){
                LOG.error("===>[saveFile] exception e={}", e);
                return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
            }

            // FilePath=fileUrl+ File.separator + dateStr + File.separator + newFileName;
            FilePath=idPicUrlsx+ File.separator + dateStr + File.separator + newFileName;
            LOG.info("上传成功！！文件路径===》{}",FilePath.replaceAll("\\\\", "/"));
            return ResultVO.build(ErrorCode.SUCCESS, FilePath.replaceAll("\\\\", "/"));

        } else {
            LOG.error("===>[saveFile] file error");
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }
    
    /**
     * App端上传头像
     * @param myfile
     * @return
     */
    private Object saveHeader(MultipartFile myfile, String fileSubPath, String fileUrl,long id){
        String FilePath ="";
        if (myfile != null && !myfile.isEmpty()) {
            // 获取文件的原始名称
            String oldName = myfile.getOriginalFilename();
            // 获取文件大小
            Long fileSize = myfile.getSize();
            // 获取文件的原始流
            // f.getInputStream()

            // 组装文件存储路径
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = sdf.format(new Date());
            String filePath = fileSubPath + File.separator + dateStr;

            // 创建目录
            File f = new File(filePath);
            if (!f.exists()) {
                f.mkdirs();
            }

            // 生成一个新的不会重复的文件名
            // 1.获取后缀
            String suffix = FilenameUtils.getExtension(myfile.getOriginalFilename());
            // 2.生成新的文件名
            String newFileName = UUID.randomUUID().toString() + "." + suffix;
            // 把上传的文件存储指定位置
            try{
                myfile.transferTo(new File(f, newFileName));
            }catch (Exception e){
                LOG.error("===>[saveFile] exception e={}", e);
                return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
            }

            // FilePath=fileUrl+ File.separator + dateStr + File.separator + newFileName;
            FilePath=idPicUrlsx+ File.separator + dateStr + File.separator + newFileName;
            LOG.info("上传成功！！文件路径===》{}",FilePath.replaceAll("\\\\", "/"));
            userMapper.updateAvator(FilePath.replaceAll("\\\\", "/"), id);
            return ResultVO.build(ErrorCode.SUCCESS, FilePath.replaceAll("\\\\", "/"));

        } else {
            LOG.error("===>[saveFile] file error");
            return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
        }
    }
    
    /**
     * App端上传文件多张
     * @param myfile
     * @return
     */
    private Object saveFiles(MultipartFile[] myfile, String fileSubPath, String fileUrl){
    		List<String> list=new ArrayList<>();
    	if (myfile!=null) {
    		for (int i = 0; i < myfile.length; i++) {
            	String FilePath ="";
            	if (myfile != null && !myfile[i].isEmpty()) {
                    // 获取文件的原始名称
                    String oldName = myfile[i].getOriginalFilename();
                    // 获取文件大小
                    Long fileSize = myfile[i].getSize();
                    // 获取文件的原始流
                    // f.getInputStream()

                    // 组装文件存储路径
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String dateStr = sdf.format(new Date());
                    String filePath = fileSubPath + File.separator + dateStr;

                    // 创建目录
                    File f = new File(filePath);
                    if (!f.exists()) {
                        f.mkdirs();
                    }

                    // 生成一个新的不会重复的文件名
                    // 1.获取后缀
                    String suffix = FilenameUtils.getExtension(myfile[i].getOriginalFilename());
                    // 2.生成新的文件名
                    String newFileName = UUID.randomUUID().toString() + "." + suffix;
                    // 把上传的文件存储指定位置
                    try{
                        myfile[i].transferTo(new File(f, newFileName));
                    }catch (Exception e){
                        LOG.error("===>[saveFile] exception e={}", e);
                        return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
                    }

                    // FilePath=fileUrl+ File.separator + dateStr + File.separator + newFileName;
                    FilePath=idPicUrlsx+ File.separator + dateStr + File.separator + newFileName;
                    LOG.info("上传成功！！文件路径===》{}",FilePath.replaceAll("\\\\", "/"));
                    list.add(FilePath.replaceAll("\\\\", "/"));
                   

                } else {
                    LOG.error("===>[saveFile] file error");
                    return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
                }
            	
    		}
    		System.out.println(list);
    		 return ResultVO.build(ErrorCode.SUCCESS, list);
		}else {
			return ResultVO.build(ErrorCode.PARAM_ERROR);
		}
        
        
        
    }

    /**
     * app上传图片接口
     * @param myfile
     * @param tokenId
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value="/app/fileUpload")
    @ResponseBody
    private Object uploadIdCard(@RequestParam(name = "file", required = false) MultipartFile[] myfile,  String tokenId)  {
        if(StringUtils.isEmpty(tokenId)){
            LOG.error("===>[fileUpload] 参数为空： tokenId={}", tokenId);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
        ResultVO<UserTokenInfoModel> ret = UserAuthTools.getUserInfo(tokenId);
        if (ret.getStatus() != 0) {
            return ResultVO.build(ret.getStatus(), ret.getMsg());
        }
        	return saveFiles(myfile, idPicPath, idPicUrl);

    }

    /**
     * app上传头像接口
     * @param myfile
     * @param tokenId
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value="/app/uploadHeaderPic")
    @ResponseBody
    private Object uploadHeaderPic(@RequestParam(name = "file", required = false) MultipartFile myfile,  String tokenId)  {
        if(StringUtils.isEmpty(tokenId)){
            LOG.error("===>[uploadHeaderPic] 参数为空： tokenId={}", tokenId);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
        ResultVO<UserTokenInfoModel> ret = UserAuthTools.getUserInfo(tokenId);
        if (ret.getStatus() != 0) {
            return ResultVO.build(ret.getStatus(), ret.getMsg());
        }
        	UserTokenInfoModel model=ret.getData();
			return saveHeader(myfile, idPicPath, idPicUrl,model.getId());
        
    }
      
    /**
     * 修改我的信息
     * @param params
     * @return
     */
    @ApiOperation(value = "修改我的信息")
    @ApiImplicitParam(paramType = "body", name = "params", required = true, dataType = "map", value = "参数")
    @RequestMapping(value="/app/updateByUser", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<Object> updateByUser(@RequestBody Map<String, Object> params){
    	String tokenId=params.get("tokenId").toString();
    	if (StringUtils.isEmpty(tokenId)) {
    		 LOG.error("===>[updateByUser] 参数为空： tokenId={}", tokenId);
    		 return ResultVO.build(ErrorCode.PARAM_ERROR);
		}
    		try {
    			String idCode=null; 
    			String address=null;
    			if (params.get("idCode")!=null) {
    				idCode=params.get("idCode").toString();
				}if (params.get("address")!=null) {
					 address=params.get("address").toString();
				}
				return  appBussiness.saveOrUpdateUser(idCode, address, tokenId);
			} catch (NumberFormatException e) {
				LOG.error("===>{updateByUser} 参数异常："+e.getMessage());
				return ResultVO.build(ErrorCode.PARAM_ERROR);
			}
    }
    
   /**
    * 获取回退列表
    * @param params
    * @return
    */  
    @ApiOperation(value = "获取回退列表")
    @ApiImplicitParam(paramType = "body", name = "params", required = true, dataType = "map", value = "参数")
    @RequestMapping(value="/app/getBackWorkList", method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<Object> getBackWorkList(@RequestBody Map<String, Object> params){
    	String tokenId=params.get("tokenId").toString();
    	if (StringUtils.isEmpty(tokenId)) {
    		 LOG.error("===>[updateByUser] 参数为空： tokenId={}", tokenId);
    		 return ResultVO.build(ErrorCode.PARAM_ERROR);
		}

    	try {

			System.out.println("--------------------page"+Integer.parseInt(params.get("pageNum").toString())+"===="+Integer.parseInt(params.get("pageSize").toString()));
			return appBussiness.getBackWorkList(tokenId,Integer.parseInt(params.get("pageNum").toString()),Integer.parseInt(params.get("pageSize").toString()));
		} catch (Exception e) {
			LOG.error("===>{getBackWorkList} 参数异常："+e.getMessage());
			return ResultVO.build(ErrorCode.PARAM_ERROR);
		}
    	
    }
    
    /**
     * 获取订单信息
     * @param params
     * @return
     */
    @ApiOperation("获取订单信息")
    @ApiImplicitParam(paramType="body",name="params",required=false,dataType="map",value="参数")
    @RequestMapping(value="/app/getApplyInfor",method=RequestMethod.POST)
    @ResponseBody
    public ResultVO<Object> getApplyInfor(@RequestBody Map<String, Object> params){
    	String tokenId=params.get("tokenId").toString();
    	if(StringUtils.isEmpty(tokenId)){
            LOG.error("===>[getApplyInfor] 参数为空： tokenId={}", tokenId);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
        ResultVO<UserTokenInfoModel> ret = UserAuthTools.getUserInfo(tokenId);
        if (ret.getStatus() != 0) {
            return ResultVO.build(ret.getStatus(), ret.getMsg());
        }
    	return appBussiness.getApplyInfor(params);
    }

    /**
     * 订单详情
     * @param params
     * @return
     */
    @ApiOperation("订单详情")
    @ApiImplicitParam(paramType="body",name="params",required=false,dataType="map",value="参数")
    @RequestMapping(value="/app/getApplyInforById",method=RequestMethod.POST)
    @ResponseBody
    public ResultVO<Object> getApplyInforById(@RequestBody Map<String, Object> params){
    	String tokenId=params.get("tokenId").toString();
    	if(StringUtils.isEmpty(tokenId)){
            LOG.error("===>[getApplyInforById] 参数为空： tokenId={}", tokenId);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
    	return appBussiness.findByAppInfoById(params);
    }

    /**
     * OCR身份证识别
     * @param params
     * @return
     */
    @ApiOperation("OCR身份证识别")
    @ApiImplicitParam(paramType="body",name="params",required=false,dataType="map",value="参数")
    @RequestMapping(value="/app/getUserORC",method=RequestMethod.POST)
    @ResponseBody
    public ResultVO<Object> getUserORC(@RequestBody Map<String, Object> params){
    	String tokenId=params.get("tokenId").toString();
    	if(StringUtils.isEmpty(tokenId)){
            LOG.error("===>[getApplyInforById] 参数为空： tokenId={}", tokenId);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
    	return appBussiness.getUserORC(params);
    }
    
    /**
     * 活体认证
     * @param params
     * @return
     */
    @ApiOperation("活体认证")
    @ApiImplicitParam(paramType="body",name="params",required=false,dataType="map",value="参数")
    @RequestMapping(value="/app/saveOrUpdateLivingBody",method=RequestMethod.POST)
    @ResponseBody
    public ResultVO<Object> saveOrUpdateLivingBody(@RequestBody Map<String, Object> params){
    	String tokenId=params.get("tokenId").toString();
    	if(StringUtils.isEmpty(tokenId)){
            LOG.error("===>[getApplyInforById] 参数为空： tokenId={}", tokenId);
            return ResultVO.build(ErrorCode.PARAM_ERROR);
        }
    	return appBussiness.saveOrUpdateLivingBody(params);
    }

    /**
     * 找回密码
     * @param params
     * @return
     */
    @ApiOperation("找回密码")
    @ApiImplicitParam(paramType="body",name="params",required=false,dataType="map",value="参数")
    @RequestMapping(value="/app/updateMobilePwd",method=RequestMethod.POST)
    @ResponseBody
    public ResultVO<Object> updateMobilePwd(@RequestBody Map<String, Object> params){
    	return appBussiness.updateMobilePwd(params);
    }
 
    
}
