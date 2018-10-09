package com.moerlong.carloan.modular.loan.service.impl;

import java.math.BigDecimal;
import java.util.*;

import com.moerlong.carloan.common.persistence.model.User;
import com.moerlong.carloan.common.vo.ErrorCode;
import com.moerlong.carloan.common.vo.ResultVO;
import com.moerlong.carloan.core.shiro.ShiroKit;
import com.moerlong.carloan.core.shiro.ShiroUser;
import com.moerlong.carloan.modular.loan.dao.ProcessNodeDao;
import com.moerlong.carloan.modular.loan.entity.vo.AppInforVO;
import com.moerlong.carloan.modular.loan.entity.vo.ApplyBasicInfoVo;
import com.moerlong.carloan.modular.loan.entity.vo.LoanAppAppinfoVO;
import com.moerlong.carloan.modular.loan.entity.vo.LoanApplyInfoVo;
import com.moerlong.carloan.modular.system.dao.UserMgrDao;
import com.moerlong.carloan.modular.system.service.IDeptService;
import com.moerlong.carloan.util.AppInforPage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.loan.entity.ApplyInfo;
import com.moerlong.carloan.modular.loan.dao.ApplyInfoDao;
import com.moerlong.carloan.modular.loan.service.ApplyInfoService;

@Service
public class ApplyInfoServiceImpl implements ApplyInfoService{
	private final Logger log = LoggerFactory.getLogger(ApplyInfoServiceImpl.class);
	@Autowired
	ApplyInfoDao mapper;
	@Autowired
	IDeptService deptService;
	@Autowired
	ProcessNodeDao	processNodeDao;
	@Autowired
	UserMgrDao userDao;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(ApplyInfo entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.updateWithOutNull(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(ApplyInfo entity) {
		entity.setCreateTime(new Date());
		entity.setIsDeleted(0);
		mapper.save(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void delete(Long id) {
		mapper.delete(id);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void deleteLogic(Long id){
		mapper.deleteLogic(id);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void update(ApplyInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(ApplyInfo entity) {
		entity.setUpdateTime(new Date());
		mapper.updateWithOutNull(entity);
	}
	
	public ApplyInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<ApplyInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<ApplyInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<ApplyInfo> pageList = mapper.selectPage(param);
		PageInfo<ApplyInfo> pageInfo = new PageInfo<ApplyInfo>(pageList);
		return pageInfo;
	}

    @Override
    public PageInfo<LoanApplyInfoVo> selectPage(int pageSize, int pageNum, String custName, String custIdNo, String beginTime, String endTime) {
		PageHelper.startPage(pageNum, pageSize);
		List<LoanApplyInfoVo> pageList = mapper.listByCondition(custName, custIdNo, beginTime, endTime);
		PageInfo<LoanApplyInfoVo> pageInfo = new PageInfo<LoanApplyInfoVo>(pageList);
		return pageInfo;
    }
	/**
	 * cjh
	 * 保存内勤资料录入借款基本信息
	 *
	 * @return
	 */
	public Object saveOrUpdateApplyInfo(ApplyBasicInfoVo vo){
		try {
			ApplyInfo info = new ApplyInfo();
			info.setId(vo.getApplyId());
			info.setChannelId(vo.getChannelId());
			info.setProductType(vo.getProductType());
			info.setRepaymentType(vo.getRepaymentType());
			info.setPartnerKnow(vo.getPartnerKnow());
			if(vo.getApplyAmount()!=null&&!"".equals(vo.getApplyAmount())){
				info.setApplyAmount(vo.getApplyAmount().multiply(new BigDecimal(10000)));
			}else{
				info.setApplyAmount(new BigDecimal(0));
			}
			info.setApplyPeriod(vo.getApplyPeriod());
			info.setLoanUsage(vo.getLoanUsage());
			info.setIsCollection(vo.getIsCollection());
			info.setServiceItem(vo.getServiceItem());
			info.setPercent(vo.getPercent());
			info.setMoneyAmount(vo.getMoneyAmount());
			info.setServiceCharge(vo.getServiceCharge());
			info.setFeeInstallment(vo.getFeeInstallment());

			if (!StringUtils.isBlank(vo.getLoanUsageOther())) {
				info.setLoanUsageOther(vo.getLoanUsageOther());
			}
			info.setUpdateTime(new Date());
			updateWithOutNull(info);

			return ResultVO.build(ErrorCode.SUCCESS);
		}catch (Exception e){
			log.error("====>[saveOrUpdateApplyInfo] exception e={}", e);
			return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
		}
	}

	/**
	 * 获取待办列表
	 * @param userId
	 * @param custName
	 * @param custIdNo
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
    @Override
    public List<LoanApplyInfoVo> selectTodoApplyList(Long userId, String custName, String custIdNo, String beginTime, String endTime) {
    	
    	String deptIdList = deptService.getAllSubDept(userId.intValue());
    	System.out.println("-----------------------deptIdList"+deptIdList);
    	List<LoanApplyInfoVo> list = new ArrayList<>();

    	//获取所有待办的列表
    	List<LoanApplyInfoVo> todoList =  mapper.selectTodoApplyList(userId, deptIdList, custName, custIdNo, beginTime, endTime);
		list.addAll(todoList);

		//获取所有同步列表
//    	List<Map<String, Object>> mapList = processNodeDao.selectAllField();
//		for (Map<String, Object> map : mapList) {
//			List<LoanApplyInfoVo> syncTempList = mapper.selectSyncTodoApplyList(userId, deptIdList, map.get("syncFieldName").toString(),
//					custName, custIdNo, beginTime, endTime);
//			list.addAll(syncTempList);
//		}
		
		//如果是内勤，获取所有抵押申请到请款申请的状态之间未提交gps申请的节点
//		ShiroUser shiroUser = ShiroKit.getUser();
//		List<Integer> roleids=shiroUser.getRoleList();
//		for(Integer roleid:roleids) {
//			if(roleid==3) {
//				List<LoanApplyInfoVo> las=mapper.selectUnApplyGps(deptIdList);
//				for (LoanApplyInfoVo loanApplyInfoVo : las) {
//					loanApplyInfoVo.setInterfaceAddress("/process/gpsInstallApply");
//					loanApplyInfoVo.setShowAddress("/cust/gpsInstallApply.html");
//				}
//				list.addAll(las);
//				break;
//			}
//		}
		//获取所有驳回的列表
		//List<LoanApplyInfoVo> backList = mapper.selectBackApplyList(userId, custName, custIdNo, beginTime, endTime);
		//list.addAll(backList);

		Collections.sort(list, new Comparator<LoanApplyInfoVo>() {
			@Override
			public int compare(LoanApplyInfoVo o1, LoanApplyInfoVo o2) {
				return o1.getUpdateTime().compareTo(o2.getUpdateTime());
			}
		});

		return list;
    }
    /**
	 * 获取App待办列表
	 * @param userId
	 * @param custName
	 * @param custIdNo
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
    @Override
    public List<LoanAppAppinfoVO> selectToAppdoApplyList(Long userId) {
    	//获取部门集合信息
    		User user=userDao.selectUserById(userId.intValue());
    	List<LoanAppAppinfoVO> list = new ArrayList<>();
    	//获取所有待办的列表
    	List<LoanAppAppinfoVO> todoList =  mapper.selectToAppdoApplyList(userId, user.getDeptid());
		list.addAll(todoList);

		//获取所有同步列表
    	List<Map<String, Object>> mapList = processNodeDao.selectAllField();
		for (Map<String, Object> map : mapList) {
			List<LoanAppAppinfoVO> syncTempList = mapper.selectSyncToAppDoApplyList(userId, user.getDeptid(), map.get("syncFieldName").toString());
			list.addAll(syncTempList);
		}
		
		//如果是内勤，获取所有抵押申请到请款申请的状态之间未提交gps申请的节点
		
			if(user.getRoleid().equals("3")) {
				List<LoanAppAppinfoVO> las=mapper.selectUnAppApplyGps(user.getDeptid());
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

		return list;
    }
	/**
	 * 获取经办列表
	 * @param userId
	 * @param custName
	 * @param custIdNo
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
    @Override
    public List<LoanApplyInfoVo> selectHandledApplyList(Long userId, String custName, String custIdNo, String beginTime, String endTime) {

		//获取所有待办的列表
		return mapper.selectHandledApplyList(userId, custName, custIdNo, beginTime, endTime);
    }

    /**
	 * cjh
	 *获取内勤资料录入基本信息
	 *
	 * @return
	 */
	public ApplyInfo getBackApplyInfo(Long param){
		return mapper.selectById(param);
	}

    @Override
    public Integer setSyncStatus(Long applyId, String syncFieldName, Integer processStatus) {
        return mapper.setSyncStatus(applyId, syncFieldName, processStatus);
    }

    @Override
    public Integer isComplateSyncTask(Long applyId, String syncFieldName, Integer processStatus) {
        return mapper.isComplateSyncTask(applyId, syncFieldName, processStatus);
    }

	public ApplyInfo selectByCustId(Long custId){
		return mapper.selectByCustId(custId);
	}
	/**
	 * 回退
	 */
	@Override
	public List<LoanAppAppinfoVO> selectToAppDoApplyList(Long userId,Integer pageNum,Integer pageSize) {
		String deptIdList = deptService.getAllSubDept(userId.intValue());
    	List<LoanAppAppinfoVO> list = new ArrayList<>();

    	//获取所有待办的列表
    	List<LoanAppAppinfoVO> todoList =  mapper.selectToAppdoApplyList(userId, deptIdList);
		list.addAll(todoList);

		//获取所有同步列表
    	List<Map<String, Object>> mapList = processNodeDao.selectAllField();
		for (Map<String, Object> map : mapList) {
			List<LoanAppAppinfoVO> syncTempList = mapper.selectSyncToAppDoApplyList(userId, deptIdList, map.get("syncFieldName").toString());
			list.addAll(syncTempList);
		}
		
		//如果是内勤，获取所有抵押申请到请款申请的状态之间未提交gps申请的节点
		ShiroUser shiroUser = ShiroKit.getUser();
		List<Integer> roleids=shiroUser.getRoleList();
		for(Integer roleid:roleids) {
			if(roleid==3) {
				List<LoanAppAppinfoVO> las=mapper.selectUnAppApplyGps(deptIdList);
				for (LoanAppAppinfoVO loanApplyInfoVo : las) {
					loanApplyInfoVo.setInterfaceAddress("/process/gpsInstallApply");
					loanApplyInfoVo.setShowAddress("/cust/gpsInstallApply.html");
				}
				list.addAll(las);
				break;
			}
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

		return list;
	}
	/**
	 * 获取App的订单（经办列表)
	 */
	@Override
	public List<LoanAppAppinfoVO> selectAppHandledApplyList(Map<String, Object> map) {
		
		return mapper.selectAppHandledApplyList(map);
	}

	@Override
	public AppInforPage<LoanAppAppinfoVO> getAppHandleApplyPage(Map<String, Object> map) {
		int num=Integer.parseInt(map.get("pageNum").toString());
        int size=Integer.parseInt(map.get("pageSize").toString());
        if (num==0) {
			num=1;
		}
        map.put("index", (num-1)*size);
        map.put("size", size);
        List<LoanAppAppinfoVO> list=new ArrayList<>();
		List<LoanAppAppinfoVO> pageList = mapper.selectAppHandledApplyList(map);
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
        Integer count=mapper.countAppHandledApply(map);
        AppInforPage<LoanAppAppinfoVO> page=new AppInforPage<>();
        page.setPageSize(size);
        page.setCount(count);
        page.setPageNum(num);
        page.setRows(list);
        return page;
	}
	/**
	 * 贷款详情信息
	 */
	@Override
	public AppInforVO selectAppAppinforById(Long id) {
		return mapper.selectAppAppinforById(id);
	}
}

