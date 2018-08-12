package io.github.forezp.scrorpio.time;

import java.util.Date;

/**
 * 日期提供者, 使用它而不是直接取得系统时间, 方便测试.
 * 
 * 平时使用DEFAULT，测试时替换为DummyClock，可准确控制时间变化而不用Thread.sleep()等待时间流逝.
 */
public class ClockUtil {

	private static Clock instance = new DefaultClock();

	public static long elapsedTime(long beginTime) {
		return currentTimeMillis() - beginTime;
	}


	public static synchronized DummyClock useDummyClock() {
		instance = new DummyClock();
		return (DummyClock) instance;
	}

	public static synchronized DummyClock useDummyClock(long timeStampMills) {
		instance = new DummyClock(timeStampMills);
		return (DummyClock) instance;
	}


	public static synchronized DummyClock useDummyClock(Date date) {
		instance = new DummyClock(date);
		return (DummyClock) instance;
	}


	public static synchronized void useDefaultClock() {
		instance = new DefaultClock();
	}


	public static Date currentDate() {
		return instance.currentDate();
	}


	public static long currentTimeMillis() {
		return instance.currentTimeMillis();
	}


	public static long nanoTime() {
		return instance.nanoTime();
	}

	public interface Clock {


		Date currentDate();


		long currentTimeMillis();


		long nanoTime();
	}


	public static class DefaultClock implements Clock {

		@Override
		public Date currentDate() {
			return new Date();
		}

		@Override
		public long currentTimeMillis() {
			return System.currentTimeMillis();
		}

		@Override
		public long nanoTime() {
			return System.nanoTime();
		}
	}


	public static class DummyClock implements Clock {

		private long time;
		private long nanoTme;

		public DummyClock() {
			this(System.currentTimeMillis());
		}

		public DummyClock(Date date) {
			this(date.getTime());
		}

		public DummyClock(long time) {
			this.time = time;
			this.nanoTme = System.nanoTime();
		}

		@Override
		public Date currentDate() {
			return new Date(time);
		}

		@Override
		public long currentTimeMillis() {
			return time;
		}


		@Override
		public long nanoTime() {
			return nanoTme;
		}


		public void updateNow(Date newDate) {
			time = newDate.getTime();
		}


		public void updateNow(long newTime) {
			this.time = newTime;
		}


		public void increaseTime(int millis) {
			time += millis;
		}


		public void decreaseTime(int millis) {
			time -= millis;
		}


		public void setNanoTime(long nanoTime) {
			this.nanoTme = nanoTime;
		}
	}
}
