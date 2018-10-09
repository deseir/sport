package com.moerlong.carloan.modular.task;

import com.moerlong.carloan.modular.paybackMgr.business.RepaymentBusiness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class FinanceStatisJob {

    private static Logger LOG = LoggerFactory.getLogger(FinanceStatisJob.class);

    @Resource
    private RepaymentBusiness repaymentBusiness;

    private boolean isRunning = false;

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    @Scheduled(cron = "0 01 0 * * ?")
    //@Scheduled(cron = "0/20 * * * * ?")
    public void run(){
        if(isRunning){
            LOG.error("[===>>>---财务统计---作业  上次还未执行完毕======]");
            return ;
        }
        isRunning = true;

        try{
            LOG.info("[===>>>开始执行---财务统计---作业======]");
            repaymentBusiness.statisFinance();
            LOG.info("[===>>>结束执行---财务统计---作业======]");

            LOG.info("[===>>>开始执行---财务核账---作业======]");
            repaymentBusiness.checkFinance();
            LOG.info("[===>>>结束执行---财务核账---作业======]");
        }catch (Throwable e){
            e.printStackTrace();
            LOG.error("run FinanceStatisJob error [{}]", e);
        }finally {
            isRunning = false;
        }
    }
}
