package com.moerlong.carloan.modular.cust.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.List;

import com.moerlong.carloan.common.vo.ErrorCode;
import com.moerlong.carloan.common.vo.ResultVO;
import com.moerlong.carloan.modular.cust.entity.vo.CustAddHistoryInfoVo;
import com.moerlong.carloan.modular.cust.entity.vo.CustomerInfoVo;
import com.moerlong.carloan.modular.loan.entity.ApplyInfo;
import com.moerlong.carloan.modular.loan.service.ApplyInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.CustomerInfo;
import com.moerlong.carloan.modular.cust.dao.CustomerInfoDao;
import com.moerlong.carloan.modular.cust.service.CustomerInfoService;

@Service
public class CustomerInfoServiceImpl implements CustomerInfoService{
	private final Logger log = LoggerFactory.getLogger(CustomerInfoServiceImpl.class);
	@Autowired
	CustomerInfoDao mapper;
	@Autowired
	ApplyInfoService applyInfoService;
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(CustomerInfo entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.update(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(CustomerInfo entity) {
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
	public void update(CustomerInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(CustomerInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public CustomerInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<CustomerInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<CustomerInfo> selectPage(int pageSize,int pageNum, String name) {
		PageHelper.startPage(pageNum, pageSize);
		List<CustomerInfo> pageList = mapper.selectPage(name);
		PageInfo<CustomerInfo> pageInfo = new PageInfo<CustomerInfo>(pageList);
		return pageInfo;
	}


	/**
	 * 客户新增查询
	 * @param certId
	 * @return
	 */
    @Override
    public List<CustAddHistoryInfoVo> searchCustHistory(String certId) {
        return mapper.searchCustHistory(certId);
    }
	/**
	 * 身份证信息录入
	 * cjh
	 */
	public Object custInfoAdd(CustomerInfoVo vo){
		try {
			CustomerInfo info = new CustomerInfo();
			ApplyInfo aapply=applyInfoService.selectById(vo.getApplyId());
				info.setId(aapply.getCustId());
				info.setCertId(vo.getCertId());
				info.setName(vo.getName());
				info.setMobile(vo.getMobile());
				info.setSex(vo.getSex());
				info.setNation(vo.getNation());
				info.setBirthday(vo.getBirthday());
				info.setValidateBegin(vo.getValidateBegin());
				info.setValidateEnd(vo.getValidateEnd());
				info.setSignOrg(vo.getSignOrg());
				info.setEducation(vo.getEducation());
				info.setIdFrontPhotoUrl(vo.getIdFrontPhotoUrl());
				info.setIdBackPhotoUrl(vo.getIdBackPhotoUrl());
				info.setProofOfResidence(vo.getProofOfResidence());
				/*if(!"".equals(vo.getIdFrontPhotoUrl())&&vo.getIdFrontPhotoUrl()!=null){
					String [] str=vo.getIdFrontPhotoUrl().split("/resource");
					info.setIdFrontPhotoUrl("/resource"+str[1]);
				}
				if(!"".equals(vo.getIdBackPhotoUrl())&&vo.getIdBackPhotoUrl()!=null){
					String [] str2=vo.getIdBackPhotoUrl().split("/resource");
					info.setIdBackPhotoUrl("/resource"+str2[1]);
				}*/
				info.setUpdateTime(new Date());
				info.setChildNum(vo.getChildNum());
				info.setChildAdult(vo.getChildAdult());
				info.setLiveAddress(vo.getLiveAddress());
				info.setLiveType(vo.getLiveType());
				info.setTogetherLive(vo.getTogetherLive());
				info.setSpouseName(vo.getSpouseName());
				info.setSpousePhone(vo.getSpousePhone());
				info.setContractName1(vo.getContractName1());
				info.setContractPhone1(vo.getContractPhone1());
				info.setContractRelation1(vo.getContractRelation1());
				info.setContractName2(vo.getContractName2());
				info.setContractPhone2(vo.getContractPhone2());
				info.setContractRelation2(vo.getContractRelation2());
				info.setContractName3(vo.getContractName3());
				info.setContractPhone3(vo.getContractPhone3());
				info.setContractRelation3(vo.getContractRelation3());
				updateWithOutNull(info);
				return ResultVO.build(ErrorCode.SUCCESS);
		}catch (Exception e){
			log.error("====>[saveOrUpdateApplyInfo] exception e={}", e);
			return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
		}
	}
	/**
	 * 身份证信息获取
	 * cjh
	 */
	public CustomerInfo custInfoGetByApplyId(Long param){
		ApplyInfo aapply=applyInfoService.selectById(param);
		return mapper.selectById(aapply.getCustId());

	}

	public PageInfo<CustomerInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<CustomerInfo> pageList = mapper.selectPage(param);
		PageInfo<CustomerInfo> pageInfo = new PageInfo<CustomerInfo>(pageList);
		return pageInfo;
	}
}

