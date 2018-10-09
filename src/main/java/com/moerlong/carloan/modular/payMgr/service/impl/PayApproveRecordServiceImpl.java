package com.moerlong.carloan.modular.payMgr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.payMgr.dao.PayApproveRecordDao;
import com.moerlong.carloan.modular.payMgr.entity.PayApproveRecord;
import com.moerlong.carloan.modular.payMgr.service.PayApproveRecordService;

@Service
public class PayApproveRecordServiceImpl implements PayApproveRecordService{

	@Autowired
	PayApproveRecordDao mapper;
	
	@Transactional
	public void saveOrUpdate(PayApproveRecord entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.update(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional
	public void save(PayApproveRecord entity) {
		mapper.save(entity);
	}
	
	@Transactional
	public void delete(Long id) {
		mapper.delete(id);
	}

	@Transactional
	public void update(PayApproveRecord entity) {
		mapper.update(entity);
	}
	
	public PayApproveRecord selectById(Long id) {
		return mapper.selectById(id);
	}
	
	public PageInfo<PayApproveRecord> selectPage(int pageSize,int pageNum, String orderCondition ) {
		PageHelper.startPage(pageNum, pageSize);
		List<PayApproveRecord> pageList = mapper.selectPage( orderCondition);
		PageInfo<PayApproveRecord> pageInfo = new PageInfo<PayApproveRecord>(pageList);
		return pageInfo;
	}

	/**
	 * 按Payid查询
	 * @param payId
	 * @return
	 */
	public List<PayApproveRecord> listByPayId(Long payId){
		return mapper.listByPayId(payId);
	}

}

