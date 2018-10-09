package com.moerlong.carloan.modular.cust.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.List;
import com.moerlong.carloan.common.vo.ErrorCode;
import com.moerlong.carloan.common.vo.ResultVO;
import com.moerlong.carloan.modular.cust.entity.vo.MarryInfoVo;
import com.moerlong.carloan.modular.loan.entity.ApplyInfo;
import com.moerlong.carloan.modular.loan.service.ApplyInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.MarryInfo;
import com.moerlong.carloan.modular.cust.dao.MarryInfoDao;
import com.moerlong.carloan.modular.cust.service.MarryInfoService;

@Service
public class MarryInfoServiceImpl implements MarryInfoService{
	private final Logger log = LoggerFactory.getLogger(MarryInfoServiceImpl.class);
	@Autowired
	MarryInfoDao mapper;
    @Autowired
    ApplyInfoService applyInfoService;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(MarryInfo entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.update(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(MarryInfo entity) {
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
	public void update(MarryInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(MarryInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public MarryInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<MarryInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<MarryInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<MarryInfo> pageList = mapper.selectPage(param);
		PageInfo<MarryInfo> pageInfo = new PageInfo<MarryInfo>(pageList);
		return pageInfo;
	}
	/**
	 * 信息录入
	 * cjh
	 */
	public Object addSaveOrUpdatemarryInfo(MarryInfoVo vo){
		try {
			Date now = new Date();
			MarryInfo info = new MarryInfo();
			ApplyInfo applyInfo = applyInfoService.selectById(vo.getApplyId());
			if(vo.getMarryInfoId() == null){
				//新增
				info.setDivorceSex(vo.getDivorceSex());
				info.setDivorceDate(vo.getDivorceDate());
				info.setDivorceCertId(vo.getDivorceCertId());
				info.setDivorceName(vo.getDivorceName());

				info.setDivorcePhotoUrl(vo.getDivorcePhotoUrl());
				info.setDeathCertPhotoUrl(vo.getDeathCertPhotoUrl());
				/*System.out.println("--------------------->>>>>>>>>>>"+vo.getDivorcePhotoUrl().split("/resource")[1]);
				System.out.println("--------------------->>>>>>>>>>>"+vo.getDivorcePhotoUrl().split("/resource")[1]);
				if(!"".equals(vo.getDivorcePhotoUrl())&&null!=vo.getDivorcePhotoUrl()){
					info.setDivorcePhotoUrl("/resource"+vo.getDivorcePhotoUrl().split("/resource")[1]);
				}
				if(!"".equals(vo.getDeathCertPhotoUrl())&&null!=vo.getDeathCertPhotoUrl()){
					info.setDeathCertPhotoUrl("/resource"+vo.getDeathCertPhotoUrl().split("/resource")[1]);
				}*/
				info.setMarryStatus(vo.getMarryStatus());
				info.setCustId(applyInfo.getCustId());
				info.setSpouseSex(vo.getSpouseSex());
				info.setMarryDate(vo.getMarryDate());
				info.setValidateBegin(vo.getSpousevalidateBegin());
				info.setSpouseName(vo.getSpouseName());
				info.setSpouseCertId(vo.getSpouseCertId());
				info.setValidateEnd(vo.getSpousevalidateEnd());
				info.setSignOrg(vo.getSpouseSignOrg());

				info.setIdFrontPhotoUrl(vo.getSpouseidFrontPhotoUrl());
				info.setMarryPhotoUrl(vo.getMarryPhotoUrl());
				info.setIdBackPhotoUrl(vo.getSpouseidBackPhotoUrl());
				/*if(!"".equals(vo.getSpouseidFrontPhotoUrl())&&null!=vo.getSpouseidFrontPhotoUrl()){
					info.setIdFrontPhotoUrl("/resource"+vo.getSpouseidFrontPhotoUrl().split("/resource")[1]);
				}
				if(!"".equals(vo.getMarryPhotoUrl())&&null!=vo.getMarryPhotoUrl()){
					info.setMarryPhotoUrl("/resource"+vo.getMarryPhotoUrl().split("/resource")[1]);
				}
				if(!"".equals(vo.getSpouseidBackPhotoUrl())&&null!=vo.getSpouseidBackPhotoUrl()){
					info.setIdBackPhotoUrl("/resource"+vo.getSpouseidBackPhotoUrl().split("/resource")[1]);
				}*/
				info.setCreateTime(now);
				info.setUpdateTime(now);
				info.setIsDeleted(0);
				info.setSpousePhone(vo.getSpousePhone());
				this.mapper.save(info);
			}else{
				//更新
				info.setId(vo.getMarryInfoId());
				info.setMarryStatus(vo.getMarryStatus());
				info.setCustId(applyInfo.getCustId());
				info.setSpouseSex(vo.getSpouseSex());
				info.setMarryDate(vo.getMarryDate());
				info.setValidateBegin(vo.getSpousevalidateBegin());
				info.setSpouseName(vo.getSpouseName());
				info.setSpouseCertId(vo.getSpouseCertId());
				info.setValidateEnd(vo.getSpousevalidateEnd());
				info.setSignOrg(vo.getSpouseSignOrg());

				info.setDivorcePhotoUrl(vo.getDivorcePhotoUrl());
				info.setDeathCertPhotoUrl(vo.getDeathCertPhotoUrl());
				info.setIdFrontPhotoUrl(vo.getSpouseidFrontPhotoUrl());
				info.setMarryPhotoUrl(vo.getMarryPhotoUrl());
				info.setIdBackPhotoUrl(vo.getSpouseidBackPhotoUrl());
				/*if(!"".equals(vo.getDivorcePhotoUrl())&&null!=vo.getDivorcePhotoUrl()){
					info.setDivorcePhotoUrl("/resource"+vo.getDivorcePhotoUrl().split("/resource")[1]);
				}
				if(!"".equals(vo.getDeathCertPhotoUrl())&&null!=vo.getDeathCertPhotoUrl()){
					info.setDeathCertPhotoUrl("/resource"+vo.getDeathCertPhotoUrl().split("/resource")[1]);
				}
				if(!"".equals(vo.getSpouseidFrontPhotoUrl())&&null!=vo.getSpouseidFrontPhotoUrl()){
					info.setIdFrontPhotoUrl("/resource"+vo.getSpouseidFrontPhotoUrl().split("/resource")[1]);
				}
				if(!"".equals(vo.getMarryPhotoUrl())&&null!=vo.getMarryPhotoUrl()){
					info.setMarryPhotoUrl("/resource"+vo.getMarryPhotoUrl().split("/resource")[1]);
				}
				if(!"".equals(vo.getSpouseidBackPhotoUrl())&&null!=vo.getSpouseidBackPhotoUrl()){
					info.setIdBackPhotoUrl("/resource"+vo.getSpouseidBackPhotoUrl().split("/resource")[1]);
				}*/
				info.setDivorceSex(vo.getDivorceSex());
				info.setDivorceDate(vo.getDivorceDate());
				info.setDivorceCertId(vo.getDivorceCertId());
				info.setDivorceName(vo.getDivorceName());
				info.setUpdateTime(now);
				info.setIsDeleted(0);
				info.setSpousePhone(vo.getSpousePhone());
				updateWithOutNull(info);
			}
			return ResultVO.build(ErrorCode.SUCCESS);
		}catch (Exception e){
			log.error("====>[saveOrUpdateApplyInfo] exception e={}", e);
			return ResultVO.build(ErrorCode.SYSTEM_INNER_FAILED);
		}
	}
	/**
	 * 获取内勤资料录入婚姻基本信息
	 * cjh
	 */
	public MarryInfoVo findmarryInfoByApplyId(Long ApplyId){
		MarryInfoVo marryInfoVo = new MarryInfoVo();
		ApplyInfo applyInfo = applyInfoService.selectById(ApplyId);
		marryInfoVo.setApplyId(ApplyId);
		List<MarryInfo> marryInfoVoList=mapper.MarryInfoVoByApplyId(ApplyId);
		if(marryInfoVoList.size()>0){
			marryInfoVo.setMarryInfoId(marryInfoVoList.get(0).getId());
			marryInfoVo.setMarryStatus(marryInfoVoList.get(0).getMarryStatus());
			marryInfoVo.setSpouseSex(marryInfoVoList.get(0).getSpouseSex());
			marryInfoVo.setMarryDate(marryInfoVoList.get(0).getMarryDate());
			marryInfoVo.setSpousevalidateBegin(marryInfoVoList.get(0).getValidateBegin());
			marryInfoVo.setSpousevalidateEnd(marryInfoVoList.get(0).getValidateEnd());
			marryInfoVo.setSpouseName(marryInfoVoList.get(0).getSpouseName());
			marryInfoVo.setSpouseCertId(marryInfoVoList.get(0).getSpouseCertId());
			marryInfoVo.setSpouseidFrontPhotoUrl(marryInfoVoList.get(0).getIdFrontPhotoUrl());
			marryInfoVo.setSpouseidBackPhotoUrl(marryInfoVoList.get(0).getIdBackPhotoUrl());
			marryInfoVo.setSpouseSignOrg(marryInfoVoList.get(0).getSignOrg());
			marryInfoVo.setMarryPhotoUrl(marryInfoVoList.get(0).getMarryPhotoUrl());
			marryInfoVo.setDivorceSex(marryInfoVoList.get(0).getDivorceSex());
			marryInfoVo.setDivorceDate(marryInfoVoList.get(0).getDivorceDate());
			marryInfoVo.setDivorceCertId(marryInfoVoList.get(0).getDivorceCertId());
			marryInfoVo.setDivorceName(marryInfoVoList.get(0).getDivorceName());
			marryInfoVo.setDivorcePhotoUrl(marryInfoVoList.get(0).getDivorcePhotoUrl());
			marryInfoVo.setDeathCertPhotoUrl(marryInfoVoList.get(0).getDeathCertPhotoUrl());
			marryInfoVo.setSpousePhone(marryInfoVoList.get(0).getSpousePhone());
		}
		return marryInfoVo;
	}
}

