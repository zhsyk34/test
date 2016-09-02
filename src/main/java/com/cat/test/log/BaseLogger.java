package com.cat.test.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface BaseLogger {
    Logger logger = LoggerFactory.getLogger(BaseLogger.class);
}
