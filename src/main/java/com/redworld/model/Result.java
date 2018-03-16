package com.redworld.model;

public class Result {
	
	public static Result OK = new Result(0, "OK");
	
	public static Result ok(Object data){
		return new Result(0, "OK", data);
	}
	
	public static Result fail(String errMessage){
		return new Result(1, errMessage);
	}
	
	public static Result fail(int errCode, String errMessage){
		return new Result(errCode, errMessage);
	}
	
	public static Result fail(Exception e){
		return new Result(1, e.getMessage(), null);
	}

	private int errCode;
	private String errMessage;
	private Object data;
	
	public Result(){}
	
	public Result(int errCode, String errMessage){
		this(errCode, errMessage, null);
	}
	
	public Result(int errCode, String errMessage, Object data){
		this.errCode = errCode;
		this.errMessage = errMessage;
		this.data = data;
	}

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
