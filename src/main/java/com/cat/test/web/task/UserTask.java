package com.cat.test.web.task;

import com.cat.test.log.BaseLogger;
import com.cat.test.web.entity.User;
import com.cat.test.web.service.UserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserTask implements BaseLogger {

    @Resource
    private UserService userService;

    @Scheduled(cron = "0/30 * * * * ?")   //每5秒执行一次
    public void begin() {
        logger.debug("begin to task >>>>>>>>");
        userService.save(new User(96, "crg", 33));
    }
}
