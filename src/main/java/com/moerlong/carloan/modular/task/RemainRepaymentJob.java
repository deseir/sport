package com.moerlong.carloan.modular.task;


import com.moerlong.carloan.modular.paybackMgr.business.RepaymentBusiness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * 客户还款短信提前提醒
 */
@Component
public class RemainRepaymentJob {
    private static Logger LOG = LoggerFactory.getLogger(RemainRepaymentJob.class);

    @Resource
    private RepaymentBusiness repaymentBusiness;

    private boolean isRunning = false;

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    @Scheduled(cron = "0 0 10 * * ?")
    public void run(){
        if(isRunning){
            LOG.error("[===>>>---客户还款短信提前提醒---作业  上次还未执行完毕======]");
            return ;
        }
        isRunning = true;

        try{
            LOG.info("[===>>>开始执行---客户还款短信提前提醒---作业======]");

            repaymentBusiness.autoRemainCustomerForRepayment();

            LOG.info("[===>>>结束执行---客户还款短信提前提醒---作业======]");
        }catch (Throwable e){
            e.printStackTrace();
            LOG.error("run autoRemainCustomerForRepayment error [{}]", e);
        }finally {
            isRunning = false;
        }
    }

}
