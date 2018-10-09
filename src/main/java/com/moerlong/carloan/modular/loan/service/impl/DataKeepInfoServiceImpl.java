package com.moerlong.carloan.modular.loan.service.impl;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.loan.entity.DataKeepInfo;
import com.moerlong.carloan.modular.loan.dao.DataKeepInfoDao;
import com.moerlong.carloan.modular.loan.service.DataKeepInfoService;

@Service
public class DataKeepInfoServiceImpl implements DataKeepInfoService{

	@Autowired
	DataKeepInfoDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(DataKeepInfo entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.update(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(DataKeepInfo entity) {
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
	public void update(DataKeepInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(DataKeepInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public DataKeepInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	public DataKeepInfo selectByApplyId(Long id) {
		return mapper.selectByApplyId(id);
	}
	public List<DataKeepInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<DataKeepInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<DataKeepInfo> pageList = mapper.selectPage(param);
		PageInfo<DataKeepInfo> pageInfo = new PageInfo<DataKeepInfo>(pageList);
		return pageInfo;
	}

}

