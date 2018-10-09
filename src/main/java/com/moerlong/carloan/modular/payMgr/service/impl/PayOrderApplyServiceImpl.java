package com.moerlong.carloan.modular.payMgr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.payMgr.dao.PayOrderApplyDao;
import com.moerlong.carloan.modular.payMgr.entity.PayOrderApply;
import com.moerlong.carloan.modular.payMgr.service.PayOrderApplyService;

@Service
public class PayOrderApplyServiceImpl implements PayOrderApplyService{

	@Autowired
	PayOrderApplyDao mapper;
	
	@Transactional
	public void saveOrUpdate(PayOrderApply entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.update(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional
	public void save(PayOrderApply entity) {
		mapper.save(entity);
	}
	
	@Transactional
	public void delete(Long id) {
		mapper.delete(id);
	}

	@Transactional
	public void update(PayOrderApply entity) {
		mapper.update(entity);
	}
	
	public PayOrderApply selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public PageInfo<PayOrderApply> selectPage(int pageSize,int pageNum, String orderCondition ) {
		PageHelper.startPage(pageNum, pageSize);
		List<PayOrderApply> pageList = mapper.selectPage( orderCondition);
		PageInfo<PayOrderApply> pageInfo = new PageInfo<PayOrderApply>(pageList);
		return pageInfo;
	}

	/**
	 * 根据条件查询审核订单
	 * @return
	 */
	public List<PayOrderApply> listByCondition(String batchNo, String beginTime, String endTime, String status){

		return  mapper.listByCondition(batchNo,beginTime,endTime,status);
	}

}

