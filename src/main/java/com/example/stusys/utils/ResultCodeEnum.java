package com.example.stusys.utils;

import lombok.Getter;

/**
 * 状态码枚举类
 *
 * @author Jiutwo
 */
@Getter
public enum ResultCodeEnum {

	/**
	 * 成功
	 */
	SUCCESS(true, 10000, "成功"),

	/**
	 * 失败
	 */
	FAIL(false, 1, "失败"),

	/**
	 * 未知错误
	 */
	UNKNOWN_ERROR(false, -1, "未知错误"),

	/**
	 * 暂无数据
	 */
	NO_DATA(true, 10001, "暂无数据");

	/**
	 * 响应是否成功
	 */
	private final Boolean success;

	/**
	 * 响应状态码
	 */
	private final Integer code;

	/**
	 * 响应信息
	 */
	private final String message;

	ResultCodeEnum(boolean success, Integer code, String message) {
		this.success = success;
		this.code = code;
		this.message = message;
	}

}
