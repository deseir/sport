package com.moerlong.carloan.modular.paybackMgr.service.impl;

import com.moerlong.carloan.modular.paybackMgr.dao.RepaymentChangeRecordDao;
import com.moerlong.carloan.modular.paybackMgr.entity.RepaymentChangeRecord;
import com.moerlong.carloan.modular.paybackMgr.service.RepaymentChangeRecordService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RepaymentChangeRecordServiceImpl implements RepaymentChangeRecordService {
    private static final Logger LOG = LoggerFactory.getLogger(RepaymentChangeRecordServiceImpl.class);

    @Autowired
    RepaymentChangeRecordDao mapper;

    @Override
    @Transactional
    public void saveOrUpdate(RepaymentChangeRecord entity) {
        if(entity.getId()!=null && this.selectById(entity.getId())!=null) {
            this.update(entity);
        }else {
            this.save(entity);
        }
    }

    @Override
    @Transactional
    public void save(RepaymentChangeRecord entity) {
        mapper.save(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        mapper.delete(id);
    }

    @Override
    @Transactional
    public void update(RepaymentChangeRecord entity) {
        mapper.update(entity);
    }

    @Override
    public RepaymentChangeRecord selectById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public List<RepaymentChangeRecord> selectByRepaymentId(Long repaymentId) {
        return mapper.selectByRepaymentId(repaymentId);
    }

    @Override
    public List<RepaymentChangeRecord> selectByRepaymentPlanId(Long repaymentPlanId) {
        return mapper.selectByRepaymentPlanId(repaymentPlanId);
    }

    @Override
    public List<RepaymentChangeRecord> listByCondition(String beginTime, String endTime, Integer changeType) {
        return mapper.listByCondition(beginTime, endTime, changeType);
    }
}
