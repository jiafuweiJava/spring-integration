package com.jiafuwei.spring.po;
public class JsonResult {
	
	/**
	 * 请求结果，0为失败，1为成功
	 */
	private int res; 
	
	
	/**
	 * 请求结果信息
	 */
	private String meg;
	
	
	/**
	 * 请求的返回数据对象，也将被转为json格式
	 */
	private Object data;
	
	
	
	public int getRes() {
		return res;
	}
	public void setRes(int res) {
		this.res = res;
	}
	public String getMeg() {
		return meg;
	}
	public void setMeg(String meg) {
		this.meg = meg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	
	
}