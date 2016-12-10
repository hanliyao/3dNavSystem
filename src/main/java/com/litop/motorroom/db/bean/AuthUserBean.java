package com.litop.motorroom.db.bean;

/**
 * 权限上使用的用户Bean
 * @author
 *
 */
public class AuthUserBean {
	private String userId;
	private String password;
	private String safeCode;
	/**
	 * 登陆是否成功
	 */
	private boolean loginFlag;
	/**
	 * 登陆返回信息
	 */
	private String loginMsg;
	/**
	 * 密码过期天数
	 */
	private int days;
	
	
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSafeCode() {
		return safeCode;
	}
	public void setSafeCode(String safeCode) {
		this.safeCode = safeCode;
	}
	public boolean isLoginFlag() {
		return loginFlag;
	}
	public void setLoginFlag(boolean loginFlag) {
		this.loginFlag = loginFlag;
	}
	public String getLoginMsg() {
		return loginMsg;
	}
	public void setLoginMsg(String loginMsg) {
		this.loginMsg = loginMsg;
	}
	
	
}
