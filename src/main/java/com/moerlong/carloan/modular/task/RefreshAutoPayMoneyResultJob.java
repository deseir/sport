package com.moerlong.carloan.modular.task;

import com.moerlong.carloan.modular.payMgr.business.PayBussiness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RefreshAutoPayMoneyResultJob {

    private static Logger LOG = LoggerFactory.getLogger(RefreshAutoPayMoneyResultJob.class);

    @Resource
    private PayBussiness payBussiness;

    private boolean isRunning = false;

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

//    @Scheduled(cron = "0 0/2 * * * ?")
    public void run(){
        if(isRunning){
            LOG.error("[===>>>---更新放款结果---作业  上次还未执行完毕======]");
            return ;
        }
        isRunning = true;

        try{
            LOG.info("[===>>>开始执行---更新放款结果---作业======]");

            payBussiness.refreshAutoPayMoneyResult();

            LOG.info("[===>>>结束执行---更新放款结果---作业======]");
        }catch (Throwable e){
            e.printStackTrace();
            LOG.error("run refreshAutoPayMoneyResult error [{}]", e);
        }finally {
            isRunning = false;
        }
    }
}
