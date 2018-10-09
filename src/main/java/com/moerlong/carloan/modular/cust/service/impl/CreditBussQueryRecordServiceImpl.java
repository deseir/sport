package com.moerlong.carloan.modular.cust.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.CreditBussQueryRecord;
import com.moerlong.carloan.modular.cust.dao.CreditBussQueryRecordDao;
import com.moerlong.carloan.modular.cust.service.CreditBussQueryRecordService;

@Service
public class CreditBussQueryRecordServiceImpl implements CreditBussQueryRecordService{

	@Autowired
	CreditBussQueryRecordDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(CreditBussQueryRecord entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			entity.setUpdateTime(new Date());
			this.update(entity);
		}else {
			entity.setCreateTime(new Date());
			entity.setIsDeleted(0);
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(CreditBussQueryRecord entity) {
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
	public void update(CreditBussQueryRecord entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(CreditBussQueryRecord entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public CreditBussQueryRecord selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<CreditBussQueryRecord> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<CreditBussQueryRecord> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<CreditBussQueryRecord> pageList = mapper.selectPage(param);
		PageInfo<CreditBussQueryRecord> pageInfo = new PageInfo<CreditBussQueryRecord>(pageList);
		return pageInfo;
	}

	public Integer selRecent2MonCount(Map<String,Object> param){
		return mapper.selRecent2MonCount(param);
	}

	public CreditBussQueryRecord selByApplyIdAndType(Map<String,Object> param){
		return mapper.selByApplyIdAndType(param);
	}
	public List<CreditBussQueryRecord> selListByApplyIdAndType(Map<String,Object> param){
		return mapper.selListByApplyIdAndType(param);
	}

	public void deleteByIds(List list){
		mapper.deleteByIds(list);
	}

}

