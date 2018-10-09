package com.moerlong.carloan.modular.paybackMgr.service.impl;

import com.moerlong.carloan.modular.paybackMgr.dao.RepaymentApproveRecordDao;
import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentApporveRecord;
import com.moerlong.carloan.modular.paybackMgr.service.RepaymentApproveRecordService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class RepaymentApproveRecordServiceImpl implements RepaymentApproveRecordService {

    @Resource
    private RepaymentApproveRecordDao mapper;

    @Override
    @Transactional
    public void save(RepaymentApporveRecord entity) {

        Date now = new Date();
        entity.setCreateTime(now);
        entity.setUpdateTime(now);
        entity.setIsDeleted(0);

        mapper.save(entity);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        mapper.delete(id);
    }

    @Override
    @Transactional
    public void update(RepaymentApporveRecord entity) {
        mapper.update(entity);
    }

    @Override
    public RepaymentApporveRecord selectById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public List<RepaymentApporveRecord> selectByRepaymentId(Long repaymentId) {
        return mapper.selectByRepaymentId(repaymentId);
    }
}
