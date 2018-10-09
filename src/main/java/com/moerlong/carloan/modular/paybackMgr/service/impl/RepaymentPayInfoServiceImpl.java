package com.moerlong.carloan.modular.paybackMgr.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.moerlong.carloan.modular.paybackMgr.entity.enums.PayStatus;
import com.moerlong.carloan.modular.paybackMgr.entity.vo.RepaymentPayInfoVO;
import com.moerlong.carloan.modular.paybackMgr.entity.vo.RepaymentPayReportInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentPayInfo;
import com.moerlong.carloan.modular.paybackMgr.dao.RepaymentPayInfoDao;
import com.moerlong.carloan.modular.paybackMgr.service.RepaymentPayInfoService;

@Service
public class RepaymentPayInfoServiceImpl implements RepaymentPayInfoService{

	@Autowired
	RepaymentPayInfoDao mapper;
	
	@Transactional
	public void saveOrUpdate(RepaymentPayInfo entity) {
		if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
			this.update(entity);
		}else {
			this.save(entity);
		}
	}
	
	@Transactional
	public void save(RepaymentPayInfo entity) {
		mapper.save(entity);
	}
	
	@Transactional
	public void delete(Long id) {
		mapper.delete(id);
	}

	@Transactional
	public void update(RepaymentPayInfo entity) {
		mapper.update(entity);
	}
	
	public RepaymentPayInfo selectById(Long id) {
		return mapper.selectById(id);
	}

	@Override
	public RepaymentPayInfo selectInfoByPrimaryKeyForUpdate(Long id) {
		return mapper.selectInfoByPrimaryKeyForUpdate(id);
	}

	public PageInfo<RepaymentPayInfoVO> selectPage(int pageSize,int pageNum, String payCode,String batchNo, String beginTime, String endTime, String status, Integer payType) {
		PageHelper.startPage(pageNum, pageSize);
		List<RepaymentPayInfoVO> pageList = mapper.listByCondition(payCode,batchNo,beginTime,endTime,status, payType);
		PageInfo<RepaymentPayInfoVO> pageInfo = new PageInfo<RepaymentPayInfoVO>(pageList);
		return pageInfo;
	}

    @Override
    public PageInfo<RepaymentPayInfoVO> selectPageMrl(int pageSize, int pageNum, String payCode, String batchNo, String beginTime, String endTime, String status, Integer payType) {
		PageHelper.startPage(pageNum, pageSize);
		List<RepaymentPayInfoVO> pageList = mapper.listByConditionMrl(payCode,batchNo,beginTime,endTime,status, payType);
		PageInfo<RepaymentPayInfoVO> pageInfo = new PageInfo<RepaymentPayInfoVO>(pageList);
		return pageInfo;
    }

    public PageInfo<RepaymentPayInfo> selectPage(int pageSize,int pageNum, String orderCondition) {
		PageHelper.startPage(pageNum, pageSize);
		List<RepaymentPayInfo> pageList = mapper.selectPage(orderCondition);
		PageInfo<RepaymentPayInfo> pageInfo = new PageInfo<RepaymentPayInfo>(pageList);
		return pageInfo;
	}

	/**
	 * 根据条件查询代扣订单
	 * @return
	 */
	public List<RepaymentPayInfoVO> listByCondition(String payCode,String batchNo, String beginTime, String endTime, String status, Integer payType){

		return mapper.listByCondition(payCode,batchNo,beginTime,endTime,status,payType);
	}

	/**
	 * 根据时间条件查询历史订单报表
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	@Override
	public List<RepaymentPayReportInfoVO> listReport(String beginTime, String endTime){
		return mapper.listReport(beginTime, endTime);
	}

	@Override
	public List<RepaymentPayInfo> selectRepaymentPayingsRecord(Long repaymentId) {
		return mapper.selectInfoByRepaymentIdBetweenStatus(repaymentId, PayStatus.PAYING.getValue(),
				PayStatus.REPAYMENT_FAIL.getValue());
	}

	@Override
	public List<RepaymentPayInfo> selectPaysByStatus(Integer payStatus) {
		return mapper.selectPaysByStatus(payStatus);
	}

	/**
	 * 获取昨天（T-1) 成功还款金额总计
	 * @return
	 */
	@Override
	public List<Map<String ,Object>> getPaysCountLastDaySuccess() {
		return mapper.getPaysCountLastDaySuccess();
	}

	/**
	 * 获取昨天（T-1)成功收取前期费用总计
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getPreFeePaysCountLastDaySuccess() {
		return mapper.getPreFeePaysCountLastDaySuccess();
	}

	/**
	 * 统计累计收款金额总计
	 * @return
	 */
	@Override
	public List<Map<String, Object>> countAllRepaymentMoney() {
		return mapper.countAllRepaymentMoney();
	}

	/**
	 * 统计累计收取前期费用总计
	 * @return
	 */
	@Override
	public List<Map<String, Object>> countAllRepaymentPreFee() {
		return mapper.countAllRepaymentPreFee();
	}

	/**
	 * 根据还款id统计已还金额
	 * @return
	 */
	@Override
	public List<Map<String, Object>> countRepaymentMoneyByRepaymentId(Long repaymentId) {
		return mapper.countRepaymentMoneyByRepaymentId(repaymentId);
	}
	
	@Override
	public BigDecimal countRepaymentMoneyByRepaymentId2(Long repaymentId) {
		return mapper.countRepaymentMoneyByRepaymentId2(repaymentId);
	}

	/**
	 * 根据提前还款类型获取提前还款的统计数据
	 * @param payType  10 11 12 13
	 * @return
	 */
    @Override
    public List<Map<String, Object>> countAllOnceEarlyRepayment(Integer payType) {
        return mapper.countAllOnceEarlyRepayment(payType);
    }


}

