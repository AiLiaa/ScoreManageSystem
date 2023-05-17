package com.example.stusys.utils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Api统一结果类
 */
@Data
public class R<T> {

	/**
	 * 响应是否成功
	 */
	private Boolean success;

	/**
	 * 响应状态码
	 */
	private Integer code;

	/**
	 * 响应信息
	 */
	private String message;

	/**
	 * 响应数据
	 */
	private Map<String, Object> data = new HashMap<>();

	/**
	 * 构造器私有
	 */
	private R() {
	}

	/**
	 * 通用返回成功
	 */
	public static <T> R<T> ok() {
		R<T> r = new R<>();
		r.setSuccess(ResultCodeEnum.SUCCESS.getSuccess());
		r.setCode(ResultCodeEnum.SUCCESS.getCode());
		r.setMessage(ResultCodeEnum.SUCCESS.getMessage());
		return r;
	}

	/**
	 * 通用返回失败，未知错误
	 */
	public static <T> R<T> error() {
		R<T> r = new R<>();
		r.setSuccess(ResultCodeEnum.UNKNOWN_ERROR.getSuccess());
		r.setCode(ResultCodeEnum.UNKNOWN_ERROR.getCode());
		r.setMessage(ResultCodeEnum.UNKNOWN_ERROR.getMessage());
		return r;
	}

	/**
	 * 通用返回失败
	 */
	public static <T> R<T> fail() {
		R<T> r = new R<>();
		r.setSuccess(ResultCodeEnum.FAIL.getSuccess());
		r.setCode(ResultCodeEnum.FAIL.getCode());
		r.setMessage(ResultCodeEnum.FAIL.getMessage());
		return r;
	}

	/**
	 * 设置结果，形参为结果枚举
	 */
	public static <T> R<T> setResult(ResultCodeEnum result) {
		R<T> r = new R<>();
		r.setSuccess(result.getSuccess());
		r.setCode(result.getCode());
		r.setMessage(result.getMessage());
		return r;
	}

	/*
	 * ------------使用链式编程，返回类本身-----------
	 */

	/**
	 * 自定义返回数据
	 */
	public R<T> data(Map<String, Object> map) {
		this.setData(map);
		return this;
	}

	/**
	 * 通用设置data
	 */
	public R<T> data(String key, Object value) {
		this.data.put(key, value);
		return this;
	}

	/**
	 * 自定义状态信息
	 */
	public R<T> message(String message) {
		this.setMessage(message);
		return this;
	}

	/**
	 * 自定义状态码
	 */
	public R<T> code(Integer code) {
		this.setCode(code);
		return this;
	}

	/**
	 * 自定义返回结果
	 */
	public R<T> success(Boolean success) {
		this.setSuccess(success);
		return this;
	}

}
