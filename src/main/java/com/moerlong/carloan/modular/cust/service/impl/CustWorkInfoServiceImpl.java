package com.moerlong.carloan.modular.cust.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.List;

import com.moerlong.carloan.common.vo.ErrorCode;
import com.moerlong.carloan.common.vo.ResultVO;
import com.moerlong.carloan.modular.cust.entity.vo.CustWorkInfoVo;
import com.moerlong.carloan.modular.loan.entity.ApplyInfo;
import com.moerlong.carloan.modular.loan.service.ApplyInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.CustWorkInfo;
import com.moerlong.carloan.modular.cust.dao.CustWorkInfoDao;
import com.moerlong.carloan.modular.cust.service.CustWorkInfoService;

@Service
public class CustWorkInfoServiceImpl implements CustWorkInfoService{
	private final Logger log = LoggerFactory.getLogger(CustWorkInfoServiceImpl.class);
	@Autowired
	CustWorkInfoDao mapper;
	@Autowired
	ApplyInfoService applyInfoService;
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(CustWorkInfo entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.update(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(CustWorkInfo entity) {
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
	public void update(CustWorkInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(CustWorkInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public CustWorkInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<CustWorkInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<CustWorkInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<CustWorkInfo> pageList = mapper.selectPage(param);
		PageInfo<CustWorkInfo> pageInfo = new PageInfo<CustWorkInfo>(pageList);
		return pageInfo;
	}
	/**
	 * 保存或更新内存录入申请人工作信息
	 * cjh
	 */
	public Object addSaveOrUpdatemarryInfo(CustWorkInfoVo vo){
		try {
			Date now = new Date();
			CustWorkInfo info = new CustWorkInfo();
			ApplyInfo applyInfo = applyInfoService.selectById(vo.getApplyId());
			if(vo.getCustWorkInfoId() == null){
				//新增
				info.setCustId(applyInfo.getCustId());
				info.setIncomeType(vo.getIncomeType());
				info.setCompanyName(vo.getCompanyName());
				info.setCompanyType(vo.getCompanyType());
				info.setCompanyAddress(vo.getCompanyAddress());
				info.setCompanyTel(vo.getCompanyTel());
				info.setDepartment(vo.getDepartment());
				info.setJob(vo.getJob());
				info.setWorkAge(vo.getWorkAge());
				if(vo.getMonthIncome()!=null){
					info.setMonthIncome(vo.getMonthIncome().multiply(new BigDecimal(10000)));
				}
				info.setSpouseIncomeType(vo.getSpouseIncomeType());
				info.setSpouseCompanyName(vo.getSpouseCompanyName());
				info.setSpouseCompanyType(vo.getSpouseCompanyType());
				info.setSpouseCompanyAddress(vo.getSpouseCompanyAddress());
				info.setSpouseCompanyTel(vo.getSpouseCompanyTel());
				info.setSpouseDepartment(vo.getSpouseDepartment());
				info.setSpouseJob(vo.getSpouseJob());
				info.setSpouseWorkAge(vo.getSpouseWorkAge());
				if(vo.getSpouseMonthIncome()!=null){
					info.setSpouseMonthIncome(vo.getSpouseMonthIncome().multiply(new BigDecimal(10000)));
				}
				info.setCreateTime(now);
				info.setIsDeleted(0);
				this.mapper.save(info);
			}else{
				//更新
				info.setId(vo.getCustWorkInfoId());
				info.setCustId(applyInfo.getCustId());
				info.setIncomeType(vo.getIncomeType());
				info.setCompanyName(vo.getCompanyName());
				info.setCompanyType(vo.getCompanyType());
				info.setCompanyAddress(vo.getCompanyAddress());
				info.setCompanyTel(vo.getCompanyTel());
				info.setDepartment(vo.getDepartment());
				info.setJob(vo.getJob());
				info.setWorkAge(vo.getWorkAge());
				if(vo.getMonthIncome()!=null){
					info.setMonthIncome(vo.getMonthIncome().multiply(new BigDecimal(10000)));
				}
				info.setSpouseIncomeType(vo.getSpouseIncomeType());
				info.setSpouseCompanyName(vo.getSpouseCompanyName());
				info.setSpouseCompanyType(vo.getSpouseCompanyType());
				info.setSpouseCompanyAddress(vo.getSpouseCompanyAddress());
				info.setSpouseCompanyTel(vo.getSpouseCompanyTel());
				info.setSpouseDepartment(vo.getSpouseDepartment());
				info.setSpouseJob(vo.getSpouseJob());
				info.setSpouseWorkAge(vo.getSpouseWorkAge());
				if(vo.getSpouseMonthIncome()!=null){
					info.setSpouseMonthIncome(vo.getSpouseMonthIncome().multiply(new BigDecimal(10000)));
				}

				info.setIsDeleted(0);
				info.setUpdateTime(now);
				updateWithOutNull(info);
			}
			return ResultVO.build(ErrorCode.SUCCESS);
		}catch (Exception e){
			log.error("====>[saveOrUpdateApplyInfo] exception e={}", e);
			return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
		}
	}
	/**
	 * 获取内勤录入申请人工作信息
	 * cjh
	 */
	public CustWorkInfo findCustWorkInfoByApplyId(Long ApplyId){
		return mapper.findCustWorkInfoByApplyId(ApplyId);
	}
}

