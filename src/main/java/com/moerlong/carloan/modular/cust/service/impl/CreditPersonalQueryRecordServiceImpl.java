package com.moerlong.carloan.modular.cust.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.CreditPersonalQueryRecord;
import com.moerlong.carloan.modular.cust.dao.CreditPersonalQueryRecordDao;
import com.moerlong.carloan.modular.cust.service.CreditPersonalQueryRecordService;

@Service
public class CreditPersonalQueryRecordServiceImpl implements CreditPersonalQueryRecordService{

	@Autowired
	CreditPersonalQueryRecordDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(CreditPersonalQueryRecord entity) {
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
	public void save(CreditPersonalQueryRecord entity) {
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
	public void update(CreditPersonalQueryRecord entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(CreditPersonalQueryRecord entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public CreditPersonalQueryRecord selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<CreditPersonalQueryRecord> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<CreditPersonalQueryRecord> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<CreditPersonalQueryRecord> pageList = mapper.selectPage(param);
		PageInfo<CreditPersonalQueryRecord> pageInfo = new PageInfo<CreditPersonalQueryRecord>(pageList);
		return pageInfo;
	}

	public Integer selRecent2MonCount(Map<String,Object> param){
		return mapper.selRecent2MonCount(param);
	}

	public CreditPersonalQueryRecord selByApplyIdAndType(Map<String,Object> param){
		return mapper.selByApplyIdAndType(param);
	}

	public List<CreditPersonalQueryRecord> selListByApplyIdAndType (Map<String,Object> param){
		return mapper.selListByApplyIdAndType(param);
	}

	public void deleteByIds(List list){
		mapper.deleteByIds(list);
	}
}

