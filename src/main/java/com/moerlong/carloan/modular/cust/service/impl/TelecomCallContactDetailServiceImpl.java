package com.moerlong.carloan.modular.cust.service.impl;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.TelecomCallContactDetail;
import com.moerlong.carloan.modular.cust.dao.TelecomCallContactDetailDao;
import com.moerlong.carloan.modular.cust.service.TelecomCallContactDetailService;

@Service
public class TelecomCallContactDetailServiceImpl implements TelecomCallContactDetailService{

	@Autowired
	TelecomCallContactDetailDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(TelecomCallContactDetail entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.update(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(TelecomCallContactDetail entity) {
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
	public void update(TelecomCallContactDetail entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(TelecomCallContactDetail entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public TelecomCallContactDetail selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<TelecomCallContactDetail> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<TelecomCallContactDetail> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<TelecomCallContactDetail> pageList = mapper.selectPage(param);
		PageInfo<TelecomCallContactDetail> pageInfo = new PageInfo<TelecomCallContactDetail>(pageList);
		return pageInfo;
	}

}

