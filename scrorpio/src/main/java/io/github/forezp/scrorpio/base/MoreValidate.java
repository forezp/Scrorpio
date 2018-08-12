package io.github.forezp.scrorpio.base;



/**
 * 参数校验统一使用Apache Common Lange Validate, 补充一些缺少的.
 * 
 * 为什么不用Guava的{@code com.google.common.base.Preconditions} , 一是少打几个字而已, 二是Validate的方法多，比如noNullElements()判断多个元素都不为空
 * 
 * 目前主要参考 {@code com.google.common.math.MathPreconditions} , 补充数字为正数或非负数的校验
 * 
 */
public class MoreValidate {


	public static int positive( String role, int x) {
		if (x <= 0) {
			throw new IllegalArgumentException(role + " (" + x + ") must be > 0");
		}
		return x;
	}


	public static Integer positive( String role, Integer x) {
		if (x.intValue() <= 0) {
			throw new IllegalArgumentException(role + " (" + x + ") must be > 0");
		}
		return x;
	}


	public static long positive( String role, long x) {
		if (x <= 0) {
			throw new IllegalArgumentException(role + " (" + x + ") must be > 0");
		}
		return x;
	}


	public static Long positive( String role, Long x) {
		if (x.longValue() <= 0) {
			throw new IllegalArgumentException(role + " (" + x + ") must be > 0");
		}
		return x;
	}


	public static double positive( String role, double x) {
		if (!(x > 0)) { // not x < 0, to work with NaN.
			throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
		}
		return x;
	}


	public static int nonNegative( String role, int x) {
		if (x < 0) {
			throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
		}
		return x;
	}


	public static Integer nonNegative( String role, Integer x) {
		if (x.intValue() < 0) {
			throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
		}
		return x;
	}


	public static long nonNegative( String role, long x) {
		if (x < 0) {
			throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
		}
		return x;
	}


	public static Long nonNegative( String role, Long x) {
		if (x.longValue() < 0) {
			throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
		}
		return x;
	}


	public static double nonNegative( String role, double x) {
		if (!(x >= 0)) { // not x < 0, to work with NaN.
			throw new IllegalArgumentException(role + " (" + x + ") must be >= 0");
		}
		return x;
	}
}
