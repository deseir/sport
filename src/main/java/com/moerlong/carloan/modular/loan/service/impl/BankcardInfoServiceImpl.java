package com.moerlong.carloan.modular.loan.service.impl;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.loan.entity.BankcardInfo;
import com.moerlong.carloan.modular.loan.dao.BankcardInfoDao;
import com.moerlong.carloan.modular.loan.service.BankcardInfoService;

@Service
public class BankcardInfoServiceImpl implements BankcardInfoService{

	@Autowired
	BankcardInfoDao mapper;
	
	@Transactional(rollbackFor=Throwable.class)
	public void saveOrUpdate(BankcardInfo entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.update(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void save(BankcardInfo entity) {
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
	public void update(BankcardInfo entity) {
		mapper.update(entity);
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public void updateWithOutNull(BankcardInfo entity) {
		mapper.updateWithOutNull(entity);
	}
	
	public BankcardInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public List<BankcardInfo> listAll() {
		return mapper.listAll();
	}
	
	public PageInfo<BankcardInfo> selectPage(int pageSize,int pageNum, Map<String,Object> param) {
		PageHelper.startPage(pageNum, pageSize);
		List<BankcardInfo> pageList = mapper.selectPage(param);
		PageInfo<BankcardInfo> pageInfo = new PageInfo<BankcardInfo>(pageList);
		return pageInfo;
	}

	@Override
	public BankcardInfo selectByCusId(Long cusid) {
		// TODO Auto-generated method stub
		return mapper.selectByCusId(cusid);
	}

}

