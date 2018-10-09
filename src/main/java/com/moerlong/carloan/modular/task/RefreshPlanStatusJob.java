package com.moerlong.carloan.modular.task;

import com.moerlong.carloan.modular.paybackMgr.business.RepaymentBusiness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RefreshPlanStatusJob {

    private static Logger LOG = LoggerFactory.getLogger(RefreshPlanStatusJob.class);

    @Resource
    private RepaymentBusiness repaymentBusiness;

    private boolean isRunning = false;

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void run(){
        if(isRunning){
            LOG.error("[===>>>---更新还款计划状态---作业 上次还未执行完毕======]");
            return ;
        }
        isRunning = true;

        try{
            LOG.info("[===>>>开始执行---更新还款计划状态---作业======]");

            repaymentBusiness.refreshRepaymentPlanStatus();

            LOG.info("[===>>>结束执行---更新还款计划状态---作业======]");

            LOG.info("[===>>>开始执行---计算逾期违约金---作业======]");

            repaymentBusiness.calcOverdue();

            LOG.info("[===>>>结束执行---计算逾期违约金---作业======]");
        }catch (Throwable e){
            e.printStackTrace();
            LOG.error("run refreshRepaymentPlanStatus error [{}]", e);
        }finally {
            isRunning = false;
        }
    }
}
