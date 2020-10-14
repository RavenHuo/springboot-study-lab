package com.raven.api.encryption.executor;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zlt
 * @date 2018/12/13
 */
@Data
@EnableAsync(proxyTargetClass = true)
@Configuration
public class TTLAsycTaskConfig {
    /**
     *  线程池维护线程的最小数量.
     */
    @Value("${asyc-task.corePoolSize:10}")
    private int corePoolSize;
    /**
     *  线程池维护线程的最大数量
     */
    @Value("${asyc-task.maxPoolSize:50}")
    private int maxPoolSize;
    /**
     *  队列最大长度
     */
    @Value("${asyc-task.queueCapacity:100}")
    private int queueCapacity;
    /**
     *  线程池前缀
     */
    @Value("${asyc-task.threadNamePrefix:ZltExecutor-}")
    private String threadNamePrefix;

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new TTLThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(threadNamePrefix);
        /*
           rejection-policy：当pool已经达到max size的时候，如何处理新任务
           CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
