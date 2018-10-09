package com.moerlong.carloan.modular.cust.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.cust.entity.TelecomFriendCircle;
import com.moerlong.carloan.modular.cust.dao.TelecomFriendCircleDao;
import com.moerlong.carloan.modular.cust.service.TelecomFriendCircleService;

@Service
public class TelecomFriendCircleServiceImpl implements TelecomFriendCircleService{

	@Autowired
	TelecomFriendCircleDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(TelecomFriendCircle entity) {
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
	public void save(TelecomFriendCircle entity) {
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
	public void update(TelecomFriendCircle entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(TelecomFriendCircle entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public TelecomFriendCircle selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<TelecomFriendCircle> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<TelecomFriendCircle> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<TelecomFriendCircle> pageList = mapper.selectPage(param);
		PageInfo<TelecomFriendCircle> pageInfo = new PageInfo<TelecomFriendCircle>(pageList);
		return pageInfo;
	}

	public List<TelecomFriendCircle> selectByApplyIdAndType (Map<String,Object> param){
		return mapper.selectByApplyIdAndType(param);
	}

}

