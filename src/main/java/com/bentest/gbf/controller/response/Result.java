package com.bentest.gbf.controller.response;

public class Result<T> {
	
	private String code;
	
	private String msg;
	
	private T data;

	
	public Result(String code, String msg, T data)
	{
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public static Result<String> success()
	{
		return new Result<String>("0", "success", "");
	}
	
	public static <T> Result<T> success(T data)
	{
		return new Result<T>("0", "success", data);
	}
	
	public static Result<String> failure(ErrorCode errorCode)
	{
		return new Result<String>(errorCode.getCode(), errorCode.getMessage(), "");
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	
}
