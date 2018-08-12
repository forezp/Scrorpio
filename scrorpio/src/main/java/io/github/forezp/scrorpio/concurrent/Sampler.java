package io.github.forezp.scrorpio.concurrent;


import io.github.forezp.scrorpio.number.RandomUtil;
import org.apache.commons.lang3.Validate;

/**
 * 采样器
 * 
 * 移植 Twitter Common, 优化使用ThreadLocalRandom
 * 
 * https://github.com/twitter/commons/blob/master/src/java/com/twitter/common/util/Sampler.java
 * 
 */
public class Sampler {

	private static final Double ALWAYS = Double.valueOf(100);
	private static final Double NEVER = Double.valueOf(0);

	private double threshold;

	protected Sampler() {
	}


	protected Sampler(double selectPercent) {
		Validate.isTrue((selectPercent >= 0) && (selectPercent <= 100),
				"Invalid selectPercent value: " + selectPercent);

		this.threshold = selectPercent / 100;
	}


	public static Sampler create(Double selectPercent) {
		if (selectPercent.equals(ALWAYS)) {
			return new AlwaysSampler();
		} else if (selectPercent.equals(NEVER)) {
			return new NeverSampler();
		} else {
			return new Sampler(selectPercent);
		}
	}


	public boolean select() {
		return RandomUtil.threadLocalRandom().nextDouble() < threshold;
	}


	protected static class AlwaysSampler extends Sampler {
		@Override
		public boolean select() {
			return true;
		}
	}


	protected static class NeverSampler extends Sampler {
		@Override
		public boolean select() {
			return false;
		}
	}
}
