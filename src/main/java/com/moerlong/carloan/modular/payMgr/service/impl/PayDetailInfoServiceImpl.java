package com.moerlong.carloan.modular.payMgr.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.moerlong.carloan.modular.paybackMgr.entity.enums.PayStatus;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.payMgr.dao.PayDetailInfoDao;
import com.moerlong.carloan.modular.payMgr.entity.PayDetailInfo;
import com.moerlong.carloan.modular.payMgr.entity.vo.PayDetailInfoVO;
import com.moerlong.carloan.modular.payMgr.service.PayDetailInfoService;

@Service
public class PayDetailInfoServiceImpl implements PayDetailInfoService{

	@Autowired
	PayDetailInfoDao mapper;
	
	@Transactional
	public void saveOrUpdate(PayDetailInfo entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.update(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional
	public void save(PayDetailInfo entity) {
		mapper.save(entity);
	}
	
	@Transactional
	public void delete(Long id) {
		mapper.delete(id);
	}

	@Transactional
	public void update(PayDetailInfo entity) {
		mapper.update(entity);
	}
	
	public PayDetailInfo selectById(Long id) {
		return mapper.selectById(id);
	}

	@Override
	public PayDetailInfo selectByPayIdAndStatus(Long payId, Integer status) {
		return mapper.selectByPayIdAndStatus(payId, status);
	}

	@Override
	public List<PayDetailInfo> selectByStatus(Integer status) {
		return mapper.selectByStatus(status);
	}

	public PageInfo<PayDetailInfo> selectPage(int pageSize,int pageNum, String orderCondition ) {
		PageHelper.startPage(pageNum, pageSize);
		List<PayDetailInfo> pageList = mapper.selectPage( orderCondition);
		PageInfo<PayDetailInfo> pageInfo = new PageInfo<PayDetailInfo>(pageList);
		return pageInfo;
	}

	public PageInfo<PayDetailInfoVO> selectPage(int pageSize, int pageNum, String payCode, String batchNo, String beginTime, String endTime, String status, Integer payType) {
		PageHelper.startPage(pageNum, pageSize);
		List<PayDetailInfoVO> pageList = mapper.listByCondition(payCode, batchNo, beginTime, endTime, status, payType);
		PageInfo<PayDetailInfoVO> pageInfo = new PageInfo<PayDetailInfoVO>(pageList);
		return pageInfo;
	}

    @Override
    public List<PayDetailInfoVO> listByCondition(String payCode, String batchNo, String beginTime, String endTime, String status, Integer payType) {
		return mapper.listByCondition(payCode,batchNo,beginTime,endTime,status,payType);
    }


    /**
	 * 获取昨天（T-1)成功放款金额总计
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getPaysCountLastDaySuccess() {
		return mapper.getPaysCountLastDaySuccess();
	}

	/**
	 * 统计上线后累计放款总金额
	 * @return
	 */
	@Override
	public List<Map<String, Object>> countAllPayMoney() {
		return mapper.countAllPayMoney(PayStatus.PAY_SUCCESS.getValue());
	}

	@Override
	public List<Map<String, Object>> countPayMoneyByPayId(Long payId) {
		return mapper.countPayMoneyByPayId(payId);
	}
	
	public BigDecimal countPayMoneyByPayId2(Long payId) {
		return mapper.countPayMoneyByPayId2(payId);
	}


	/**
	 * 根据条件查询放款详情
	 * @return
	 */
	public List<PayDetailInfo> listByPayId(Long payId){
		return mapper.listByPayId(payId);
	}

}

