package com.moerlong.carloan.modular.biz.service.impl;

import com.moerlong.carloan.common.persistence.model.Test;
import com.moerlong.carloan.common.annotion.DataSource;
import com.moerlong.carloan.common.constant.DSEnum;
import com.moerlong.carloan.common.persistence.dao.TestMapper;
import com.moerlong.carloan.modular.biz.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 测试服务
 *
 * @author hwl
 * @date 2017-06-23 23:02
 */
@Service
public class TestServiceImpl implements ITestService {


    @Autowired
    TestMapper testMapper;

    @Override
    @DataSource(name = DSEnum.DATA_SOURCE_BIZ)
    public void testBiz() {
        Test test = testMapper.selectById(1);
        test.setId(22);
        test.insert();
    }


    @Override
    @DataSource(name = DSEnum.DATA_SOURCE_fund)
    public void testfund() {
        Test test = testMapper.selectById(1);
        test.setId(33);
        test.insert();
    }

    @Override
    @Transactional
    public void testAll() {
        testBiz();
        testfund();
        //int i = 1 / 0;
    }

}
