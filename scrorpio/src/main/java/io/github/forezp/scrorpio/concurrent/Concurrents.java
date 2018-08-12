package io.github.forezp.scrorpio.concurrent;

import com.google.common.util.concurrent.RateLimiter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * 并发常用工具类
 */
public class Concurrents {



	public static CountDownLatch countDownLatch(int count) {
		return new CountDownLatch(count);
	}


	public static CyclicBarrier cyclicBarrier(int count) {
		return new CyclicBarrier(count);
	}


	public static Semaphore nonFairSemaphore(int permits) {
		return new Semaphore(permits);
	}
	

	public static Semaphore fairSemaphore(int permits) {
		return new Semaphore(permits,true);
	}

	/////////// 限流采样 //////

	public static RateLimiter rateLimiter(int permitsPerSecond) {
		return RateLimiter.create(permitsPerSecond);
	}


	public static Sampler sampler(double selectPercent) {
		return Sampler.create(selectPercent);
	}
}
