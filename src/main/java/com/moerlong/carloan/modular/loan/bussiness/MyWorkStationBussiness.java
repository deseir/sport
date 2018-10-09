package com.moerlong.carloan.modular.loan.bussiness;


import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.loan.entity.MainApproveRecord;
import com.moerlong.carloan.modular.loan.entity.vo.LoanApplyInfoVo;

import java.util.List;
import java.util.Map;

public interface MyWorkStationBussiness {


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
    public PageInfo<LoanApplyInfoVo> getMyTodoWorkList(Integer pageSize, Integer pageNum, Long operatorId, String custName, String custIdNo, String beginTime, String endTime);


    /**
     * 获取我的经办列表
     * @param pageSize
     * @param pageNum
     * @param operatorId
     * @param custName
     * @param custIdNo
     * @param beginTime
     * @param endTime
     * @return
     */
    public PageInfo<LoanApplyInfoVo> getMyHandledWorkList(Integer pageSize, Integer pageNum, Long operatorId, String custName, String custIdNo, String beginTime, String endTime);

    /**
     * 获取审批进度列表
     * @return
     */
    public List<MainApproveRecord> getApproveRecordList(Long applyId);
}
