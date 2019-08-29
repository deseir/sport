package com.moerlong.carloan.modular.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("taskJob")
public class TaskJob {

	private final Logger log = LoggerFactory.getLogger(TaskJob.class);
	 

    /**
     * 获取车300城市接口
     */
    //@Scheduled(cron = "0/1 * * * * ? ")
    public void fetchCityJob() {
        log.info("获取车300城市任务开始执行>>>>>>>>");

        log.info("获取车300城市任务执行完成>>>>>>>>");
    }


}
