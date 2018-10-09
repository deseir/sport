package com.moerlong.carloan;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.moerlong.carloan.modular.system.dao.UserMgrDao;
import com.moerlong.carloan.util.ParamConstants;

@Component
public class CarLoanApplicationRunner implements ApplicationRunner {

    protected final static Logger logger = LoggerFactory.getLogger(CarLoanApplicationRunner.class);

    @Autowired
    private UserMgrDao managerDao;
    
    @Override
    public void run(ApplicationArguments var1) throws Exception{
    	 System.out.println("初始化参数配置");

         /** 1-取得不同角色的人员手机号配置到全局变量 */

        /* //财务人员
         List<String > fina = managerDao.getByDeptIdAndRoleId(ParamConstants.DEPT_FINA,ParamConstants.ROLE_FINA);
         //财务经理
         List<String>fina_ma = managerDao.getByDeptIdAndRoleId(ParamConstants.DEPT_FINA,ParamConstants.ROLE_FINA_MA);
         //业务人员
         List<String> buss = managerDao.getByDeptIdAndRoleId(ParamConstants.DEPT_BUSS,ParamConstants.ROLE_BUSS);
         //业务经理
         List<String> buss_ma = managerDao.getByDeptIdAndRoleId(ParamConstants.DEPT_BUSS,ParamConstants.ROLE_BUSS_MA);
         //开发人员
         List<String> deve = managerDao.getByDeptIdAndRoleId(ParamConstants.DEPT_DEVE,ParamConstants.ROLE_DEVE);
         //成都财务人员
         List<String> fina_cd = managerDao.getByDeptIdAndRoleId(ParamConstants.DEPT_FINA_CD,ParamConstants.ROLE_FINA_CD);

         ParamConstants.mobileList.put(ParamConstants.ROLE_FINA,fina);
         ParamConstants.mobileList.put(ParamConstants.ROLE_FINA_MA,fina_ma);
         ParamConstants.mobileList.put(ParamConstants.ROLE_BUSS,buss);
         ParamConstants.mobileList.put(ParamConstants.ROLE_BUSS_MA,buss_ma);
         ParamConstants.mobileList.put(ParamConstants.ROLE_DEVE,deve);
         ParamConstants.mobileList.put(ParamConstants.ROLE_FINA_CD,fina_cd);

         logger.info("财务人员手机号：{}",fina.toString());
         logger.info("财务经理手机号：{}",fina_ma.toString());
         logger.info("业务人员手机号：{}",buss.toString());
         logger.info("业务经理手机号：{}",buss_ma.toString());
         logger.info("开发人员手机号：{}",deve.toString());
         logger.info("成都财务人员手机号：{}",fina_cd.toString());*/
    }

}
