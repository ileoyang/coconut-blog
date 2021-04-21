package io.leo.coconut.common;

import io.leo.coconut.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author Leo
 */
public class ScheduleTask {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private int i;

    @Autowired
    RedisUtil redisUtil;

    @Scheduled(cron = "0 */1 * * * ?")
    public void syncPageView() {
        logger.info("thread id:{}, synView execute times:{}", Thread.currentThread().getId(), ++i);
        redisUtil.syncView();
    }

}
