package com.moerlong.carloan.modular.paybackMgr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.paybackMgr.service.CostOrderApplyService;
import com.moerlong.carloan.modular.paybackMgr.dao.CostOrderApplyDao;
import com.moerlong.carloan.modular.paybackMgr.entity.CostOrderApply;

@Service
public class CostOrderApplyServiceImpl implements CostOrderApplyService{

	@Autowired
	CostOrderApplyDao mapper;
	
	@Transactional
	public void saveOrUpdate(CostOrderApply entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.update(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional
	public void save(CostOrderApply entity) {
		mapper.save(entity);
	}
	
	@Transactional
	public void delete(Long id) {
		mapper.delete(id);
	}

	@Transactional
	public void update(CostOrderApply entity) {
		mapper.update(entity);
	}
	
	public CostOrderApply selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public PageInfo<CostOrderApply> selectPage(int pageSize,int pageNum, String orderCondition ) {
		PageHelper.startPage(pageNum, pageSize);
		List<CostOrderApply> pageList = mapper.selectPage( orderCondition);
		PageInfo<CostOrderApply> pageInfo = new PageInfo<CostOrderApply>(pageList);
		return pageInfo;
	}

	/**
	 * 根据条件查询审核订单
	 * @return
	 */
	public List<CostOrderApply> listByCondition(String batchNo, String beginTime, String endTime, String status){

		return  mapper.listByCondition(batchNo,beginTime,endTime,status);
	}

}

