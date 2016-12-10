package com.litop.motorroom.litop;

/**
 * Created by litop on 2016/7/22.
 */
public class LitopMsg<T> {
	/**
	 * 成功标记，默认未失败
	 */
	private boolean successFlag = false;
	/**
	 * 返回码
	 */
	private String retrunCode = "";
	/**
	 * 提示信息，如果successFlag =false 未失败信息，如果successFlag =true 为成功信息
	 */
	private String retrunMsg = "";
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 指向的RUL
	 */
	private String forwardUrl;
	/**
	 * 指向的提示信息，如后退，到XXX页面等
	 */
	private String forwardMsg;
	/**
	 * 返回结果 泛型
	 */
	private T result;
	public LitopMsg(){

	}

	public LitopMsg(boolean successFlag,String retrunMsg){
		if(successFlag){
			this.retrunCode = "1";
		}else{
			this.retrunCode = "0";
		}
		this.successFlag = successFlag;
		this.retrunMsg = retrunMsg;
	}
	public LitopMsg(boolean successFlag,String title,String retrunMsg){
		if(successFlag){
			this.retrunCode = "1";
		}else{
			this.retrunCode = "0";
		}
		this.successFlag = successFlag;
		this.retrunMsg = retrunMsg;
		this.title= title;
	}
	public LitopMsg(boolean successFlag,String title,String retrunMsg,T result){
		if(successFlag){
			this.retrunCode = "1";
		}else{
			this.retrunCode = "0";
		}
		this.successFlag = successFlag;
		this.retrunMsg = retrunMsg;
		this.title= title;
		this.result = result;
	}


	public boolean isSuccessFlag() {
		return successFlag;
	}
	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}
	public String getForwardMsg() {
		return forwardMsg;
	}
	public void setForwardMsg(String forwardMsg) {
		this.forwardMsg = forwardMsg;
	}
	public String getForwardUrl() {
		return forwardUrl;
	}
	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}

	public String getRetrunCode() {
		return retrunCode;
	}

	public void setRetrunCode(String retrunCode) {
		this.retrunCode = retrunCode;
	}

	public String getRetrunMsg() {
		return retrunMsg;
	}

	public void setRetrunMsg(String retrunMsg) {
		this.retrunMsg = retrunMsg;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}


}
