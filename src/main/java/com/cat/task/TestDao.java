package com.cat.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class TestDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void dao() {
        logger.info("dao...");
    }
}
