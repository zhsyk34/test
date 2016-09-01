package com.cat.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MyTask {
    private static final Logger logger = LoggerFactory.getLogger(MyTask.class);

    @Resource
    private TestDao testDao;

    @Scheduled(cron = "0/30 * * * * ?")   //每30秒执行一次
    public void begin() {
        logger.info("begin to task >>>>>>>>");
        testDao.dao();
    }
}
