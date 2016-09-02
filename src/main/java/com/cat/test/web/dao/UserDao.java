package com.cat.test.web.dao;

import com.cat.test.web.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

    public void save(User user) {
        logger.info("user dao<<<<<<<<");
    }
}
