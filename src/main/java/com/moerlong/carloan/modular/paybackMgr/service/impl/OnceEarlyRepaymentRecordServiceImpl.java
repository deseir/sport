package com.moerlong.carloan.modular.paybackMgr.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.moerlong.carloan.modular.paybackMgr.dao.OnceEarlyRepaymentRecordDao;
import com.moerlong.carloan.modular.paybackMgr.entity.OnceEarlyRepaymentRecord;
import com.moerlong.carloan.modular.paybackMgr.entity.vo.OnceEarlyRepaymentRecordVO;
import com.moerlong.carloan.modular.paybackMgr.service.OnceEarlyRepaymentRecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OnceEarlyRepaymentRecordServiceImpl implements OnceEarlyRepaymentRecordService {

    @Autowired
    private OnceEarlyRepaymentRecordDao mapper;

    @Override
    public void save(OnceEarlyRepaymentRecord entity) {
        mapper.save(entity);
    }

    @Override
    public void delete(Long id) {
        mapper.delete(id);
    }

    @Override
    public void update(OnceEarlyRepaymentRecord entity) {
        mapper.update(entity);
    }

    @Override
    public OnceEarlyRepaymentRecord selectById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public OnceEarlyRepaymentRecord selectByRepaymentId(Long repaymentId) {
        return mapper.selectByRepaymentId(repaymentId);
    }

    @Override
    public List<OnceEarlyRepaymentRecordVO> listByCondition(String custName, String custMobile, String beginTime, String endTime) {
        return mapper.listByCondition(custName, custMobile, beginTime, endTime);
    }

    /**
     * 分页查询
     * @param pageSize	页面大小
     * @param pageNum	第几页
     * @return
     */
    public PageInfo<OnceEarlyRepaymentRecordVO> selectPage(int pageSize, int pageNum, String custName, String custMobile, String beginTime, String endTime){

        PageHelper.startPage(pageNum, pageSize);
        List<OnceEarlyRepaymentRecordVO> pageList = mapper.listByCondition(custName, custMobile, beginTime, endTime);

        PageInfo<OnceEarlyRepaymentRecordVO> pageInfo = new PageInfo<OnceEarlyRepaymentRecordVO>(pageList);

        return pageInfo;
    }
}
