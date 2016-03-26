package com.zhao.esayui.domain;

import java.io.Serializable;

public class ResultEntity implements Serializable{
	private String status;//状态0成功,1失败
	private String msg;
	private Object data;//要传出去的数据
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
