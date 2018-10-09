package com.moerlong.carloan.modular.loan.bussiness.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.loan.bussiness.MyWorkStationBussiness;
import com.moerlong.carloan.modular.loan.entity.MainApproveRecord;
import com.moerlong.carloan.modular.loan.entity.vo.LoanApplyInfoVo;
import com.moerlong.carloan.modular.loan.service.ApplyInfoService;
import com.moerlong.carloan.modular.loan.service.MainApproveRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MyWorkStationBussinessImpl implements MyWorkStationBussiness{

    @Autowired
    private ApplyInfoService applyInfoService;
    @Autowired
    private MainApproveRecordService mainApproveRecordService;

    /**
     * 获取我的待办列表
     * @param pageSize
     * @param pageNum
     * @param operatorId
     * @param custName
     * @param custIdNo
     * @param beginTime
     * @param endTime
     * @return
     */
    @Override
    public PageInfo<LoanApplyInfoVo> getMyTodoWorkList(Integer pageSize, Integer pageNum, Long operatorId, String custName, String custIdNo, String beginTime, String endTime) {

        PageHelper.startPage(pageNum, pageSize);
        List<LoanApplyInfoVo> pageList = applyInfoService.selectTodoApplyList(operatorId, custName, custIdNo, beginTime, endTime);
        PageInfo<LoanApplyInfoVo> pageInfo = new PageInfo<LoanApplyInfoVo>(pageList);
        return pageInfo;
    }

    @Override
    public PageInfo<LoanApplyInfoVo> getMyHandledWorkList(Integer pageSize, Integer pageNum, Long operatorId, String custName, String custIdNo, String beginTime, String endTime) {
        PageHelper.startPage(pageNum, pageSize);
        List<LoanApplyInfoVo> pageList = applyInfoService.selectHandledApplyList(operatorId, custName, custIdNo, beginTime, endTime);
        PageInfo<LoanApplyInfoVo> pageInfo = new PageInfo<LoanApplyInfoVo>(pageList);
        return pageInfo;
    }

    @Override
    public List<MainApproveRecord> getApproveRecordList(Long applyId) {
        return mainApproveRecordService.selectByApplyId(applyId);
    }
}
