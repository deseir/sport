package com.moerlong.carloan.modular.loan.service.impl;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.loan.entity.RoleApply;
import com.moerlong.carloan.modular.loan.dao.RoleApplyDao;
import com.moerlong.carloan.modular.loan.service.RoleApplyService;

@Service
public class RoleApplyServiceImpl implements RoleApplyService{

	@Autowired
	RoleApplyDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(RoleApply entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.update(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(RoleApply entity) {
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
	public void update(RoleApply entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(RoleApply entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public RoleApply selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<RoleApply> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<RoleApply> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<RoleApply> pageList = mapper.selectPage(param);
		PageInfo<RoleApply> pageInfo = new PageInfo<RoleApply>(pageList);
		return pageInfo;
	}

}

