package com.moerlong.carloan.modular.paybackMgr.service;


import java.util.List;

import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentApporveRecord;

public interface RepaymentApproveRecordService {

    /**
     * 保存
     * @param entity
     */
    public void save(RepaymentApporveRecord entity);

    /**
     * 删除
     * @param id
     */
    public void delete(Long id);

    /**
     * 更新
     * @param entity
     */
    public void update(RepaymentApporveRecord entity);

    /**
     * 按id查询
     * @param id
     * @return
     */
    public RepaymentApporveRecord selectById(Long id);

    public List<RepaymentApporveRecord> selectByRepaymentId(Long repaymentId);


}
