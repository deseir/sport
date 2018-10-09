package com.moerlong.carloan.modular.cust.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.List;

import com.moerlong.carloan.modular.cust.entity.vo.GongjieInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.GongjieInfo;
import com.moerlong.carloan.modular.cust.dao.GongjieInfoDao;
import com.moerlong.carloan.modular.cust.service.GongjieInfoService;

@Service
public class GongjieInfoServiceImpl implements GongjieInfoService{

	@Autowired
	GongjieInfoDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(GongjieInfoVo vo) {
		GongjieInfo entity = new GongjieInfo();
		entity.setCompanyAttachUrl(vo.getCompanyAttachUrl());
		entity.setCompanyAddress(vo.getCompanyAddress());
		entity.setCertId(vo.getCertId());
		entity.setCompanyName(vo.getCompanyName());
		entity.setCompanyTel(vo.getCompanyTel());
		entity.setCompanyType(vo.getCompanyType());
		entity.setCustId(vo.getCustId());
		entity.setDepartment(vo.getDepartment());
		entity.setId(vo.getId());
		entity.setIdBackPhotoUrl(vo.getIdBackPhotoUrl());
		entity.setIdFrontPhotoUrl(vo.getIdFrontPhotoUrl());
		entity.setJob(vo.getJob());
		entity.setLiveAddress(vo.getLiveAddress());
		entity.setMarryStatus(vo.getMarryStatus());
		entity.setMobile(vo.getMobile());
		entity.setMonthIncome(vo.getMonthIncome());
		entity.setOccupationType(vo.getOccupationType());
		entity.setName(vo.getName());
		entity.setRelation(vo.getRelation());
		entity.setRemark(vo.getRemark());
		entity.setSex(vo.getSex());
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			entity.setUpdateTime(new Date());
			this.updateWithOutNull(entity);
		}else {
			entity.setCreateTime(new Date());
			entity.setIsDeleted(0);
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(GongjieInfo entity) {
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
	public void update(GongjieInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(GongjieInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public GongjieInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<GongjieInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<GongjieInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<GongjieInfo> pageList = mapper.selectPage(param);
		PageInfo<GongjieInfo> pageInfo = new PageInfo<GongjieInfo>(pageList);
		return pageInfo;
	}

	public GongjieInfo selectByCustId(Long custId){
		return mapper.selectByCustId(custId);
	}

}

