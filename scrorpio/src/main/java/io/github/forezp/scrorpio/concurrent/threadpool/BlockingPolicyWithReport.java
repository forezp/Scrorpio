package io.github.forezp.scrorpio.concurrent.threadpool;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 使用阻塞生产者的饱和策略，不抛弃任务，也不抛出异常，当队列满时改为调用BlockingQueue.put来实现生产者的阻塞
 */

public class BlockingPolicyWithReport implements RejectedExecutionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(BlockingPolicyWithReport.class);

    private String threadName;

    public BlockingPolicyWithReport() {
        this(null);
    }

    public BlockingPolicyWithReport(String threadName) {
        this.threadName = threadName;
    }


    @Override
    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
        if (threadName != null) {
            LOG.error("Thread pool [{}] is exhausted, executor={}", threadName, executor.toString());
        }

        if (!executor.isShutdown()) {
            try {
                // 添加一个元素， 如果队列满，则阻塞
                executor.getQueue().put(runnable);
            } catch (InterruptedException e) {
                // should not be interrupted
            }
        }
    }
}