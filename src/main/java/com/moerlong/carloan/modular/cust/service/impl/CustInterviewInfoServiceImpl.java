package com.moerlong.carloan.modular.cust.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.CustInterviewInfo;
import com.moerlong.carloan.modular.cust.dao.CustInterviewInfoDao;
import com.moerlong.carloan.modular.cust.service.CustInterviewInfoService;

@Service
public class CustInterviewInfoServiceImpl implements CustInterviewInfoService{

	@Autowired
	CustInterviewInfoDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(CustInterviewInfo entity) {
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
	public void save(CustInterviewInfo entity) {
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
	public void update(CustInterviewInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(CustInterviewInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public CustInterviewInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<CustInterviewInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<CustInterviewInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<CustInterviewInfo> pageList = mapper.selectPage(param);
		PageInfo<CustInterviewInfo> pageInfo = new PageInfo<CustInterviewInfo>(pageList);
		return pageInfo;
	}

	public CustInterviewInfo selByApplyId(Map<String,Object> param){
		return mapper.selByApplyId(param);
	}

	/**
	 * 更新内勤状态
	 * @param applyId
	 */
	public void updateNqStatus(Long applyId){
		mapper.updateNqStatus(applyId);
	}

	/**
	 * 更新验车师状态
	 * @param applyId
	 */
	public void updateYcStatus(Long applyId){
		mapper.updateYcStatus(applyId);
	}
}

