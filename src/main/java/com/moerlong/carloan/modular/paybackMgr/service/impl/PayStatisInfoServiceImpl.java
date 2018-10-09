package com.moerlong.carloan.modular.paybackMgr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.paybackMgr.dao.PayStatisInfoDao;
import com.moerlong.carloan.modular.paybackMgr.entity.PayStatisInfo;
import com.moerlong.carloan.modular.paybackMgr.service.PayStatisInfoService;

@Service
public class PayStatisInfoServiceImpl implements PayStatisInfoService{

	@Autowired
	PayStatisInfoDao mapper;
	
	@Transactional
	public void saveOrUpdate(PayStatisInfo entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.update(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional
	public void save(PayStatisInfo entity) {
		mapper.save(entity);
	}
	
	@Transactional
	public void delete(Long id) {
		mapper.delete(id);
	}

	@Transactional
	public void update(PayStatisInfo entity) {
		mapper.update(entity);
	}
	
	public PayStatisInfo selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public PageInfo<PayStatisInfo> selectPage(int pageSize,int pageNum, String orderCondition ) {
		PageHelper.startPage(pageNum, pageSize);
		List<PayStatisInfo> pageList = mapper.selectPage( orderCondition);
		PageInfo<PayStatisInfo> pageInfo = new PageInfo<PayStatisInfo>(pageList);
		return pageInfo;
	}

	/**
	 * 根据条件查询日报表记录
	 * @return
	 */
	public PageInfo<PayStatisInfo> listByCondition(Integer pageSize, Integer pageNum,String beginTime, String endTime,String deptId){
		PageHelper.startPage(pageNum, pageSize);
		List<PayStatisInfo> pageList = mapper.listByCondition( beginTime, endTime,deptId);
		PageInfo<PayStatisInfo> pageInfo = new PageInfo<PayStatisInfo>(pageList);
		return pageInfo;
	}

}

