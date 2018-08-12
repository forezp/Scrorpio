package io.github.forezp.scrorpio.concurrent.threadpool;

import com.google.common.collect.Maps;
import io.github.forezp.scrorpio.enums.ThreadQueueType;
import io.github.forezp.scrorpio.enums.ThreadRejectedPolicyType;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static io.github.forezp.scrorpio.enums.ThreadQueueType.LINKED_BLOCKING_QUEUE;
import static io.github.forezp.scrorpio.enums.ThreadRejectedPolicyType.CALLER_RUNS_POLICY_WITH_REPORT;


/**
 * 线程池构造类
 *
 * @author fangzhipeng create 2018-07-05
 **/
public class ThreadPoolFactory {

    private static Map<String, ThreadPoolExecutor> threadPoolMap       = Maps.newConcurrentMap();
    private static final String                    DEFAULT_THREAD_POOL = "defaultThreadPool";

    public static ThreadPoolExecutor createDefaultPoolExecutor() {
        return createThreadPoolExecutor(DEFAULT_THREAD_POOL, 50, 100, 30000L, LINKED_BLOCKING_QUEUE.getValue(), 5000,
                CALLER_RUNS_POLICY_WITH_REPORT.getValue());
    }

    public static ThreadPoolExecutor createThreadPoolExecutor(String identifier, int coreSize, int maxSize,
                                                              Long keepAliveTime, String queneType, int queueSize,
                                                              String rejectType) {
        if (threadPoolMap.get(identifier) != null) {
            return threadPoolMap.get(identifier);
        } else {
            ThreadPoolExecutor newThreadPool = new ThreadPoolExecutor(coreSize, maxSize, keepAliveTime,
                    TimeUnit.MILLISECONDS, createQuene(queneType, queueSize), createThreadFactory(identifier),
                    createRejectedExecutionHandler(rejectType));
            if (newThreadPool != null) {
                threadPoolMap.putIfAbsent(identifier, newThreadPool);
            }
            return newThreadPool;
        }
    }

    private static RejectedExecutionHandler createRejectedExecutionHandler(String rejectedPolicy) {

        ThreadRejectedPolicyType rejectedPolicyType = ThreadRejectedPolicyType.fromString(rejectedPolicy);

        switch (rejectedPolicyType) {
            case BLOCKING_POLICY_WITH_REPORT:
                return new BlockingPolicyWithReport();
            case CALLER_RUNS_POLICY_WITH_REPORT:
                return new CallerRunsPolicyWithReport();
            case ABORT_POLICY_WITH_REPORT:
                return new AbortPolicyWithReport();
            case REJECTED_POLICY_WITH_REPORT:
                return new RejectedPolicyWithReport();
            case DISCARDED_POLICY_WITH_REPORT:
                return new DiscardedPolicyWithReport();
        }
        return null;
    }

    private static ThreadFactory createThreadFactory(final String threadName) {

        return new ThreadFactory() {
            private AtomicInteger number = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, threadName + "-" + number.getAndIncrement());
            }
        };
    }

    private static BlockingQueue<Runnable> createQuene(String queue, int queueCapacity) {

        ThreadQueueType queueType = ThreadQueueType.fromString(queue);
        switch (queueType) {
            case LINKED_BLOCKING_QUEUE:
                return new LinkedBlockingQueue<>(queueCapacity);
            case ARRAY_BLOCKING_QUEUE:
                return new ArrayBlockingQueue<>(queueCapacity);
            case SYNCHRONOUS_QUEUE:
                return new SynchronousQueue<>();
        }
        return null;
    }

}
