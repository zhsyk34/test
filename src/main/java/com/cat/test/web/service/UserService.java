package com.cat.test.web.service;

import com.cat.test.log.BaseLogger;
import com.cat.test.web.dao.UserDao;
import com.cat.test.web.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService implements BaseLogger {

    @Resource
    private UserDao userDao;

    public void save(User user) {
        userDao.save(user);
        logger.debug("user service...");
    }
}
