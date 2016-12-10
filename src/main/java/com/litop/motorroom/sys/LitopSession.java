package com.litop.motorroom.sys;

import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 封装的session操作
 * 将session的操作都集中操此类，方便管理减少错误。
 * 将session封装到简单的数据类型中，如string int list等。不封装到自建class中，防止tomcat启动后自建class实例被销毁，造成部分session失效的错误，
 *
 */
/**
 * Created by litop on 2016/7/18.
 */
public class LitopSession {

	public LitopSession(HttpSession session) {
		this.session = session;
	}
	public LitopSession(HttpServletRequest request) {
		this.session = request.getSession();
	}

	/***
	 * 从session中移除
	 */
	public void removeFromSession(){
		this.session.removeAttribute("litopAuthUrl");
		this.session.removeAttribute("litopAuthRole");

		this.session.removeAttribute("orgId");
		this.session.removeAttribute("orgName");
		this.session.removeAttribute("rootOrgId");
		this.session.removeAttribute("rootOrgName");

		this.session.removeAttribute("ip");
		this.session.removeAttribute("litopUserId");
		this.session.removeAttribute("litopUserName");
		this.session.removeAttribute("litopRoleId");
		this.session.removeAttribute("litopRoleName");
	}
	/**
	 * session
	 */
	private HttpSession session;

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}


	@SuppressWarnings("unchecked")
	public TreeSet<String> getAuthUrl() {
		return (TreeSet<String>)this.session.getAttribute("litopAuthUrl");
	}

	public int getUserId() {return Integer.parseInt(this.session.getAttribute("litopUserId").toString());}
	public void setUserId(int litopUserId) {
		this.session.setAttribute("litopUserId", litopUserId);
	}

	public String getUserName() {return (String)this.session.getAttribute("litopUserName");}
	public void setUserName(String userNameStr) {
		this.session.setAttribute("litopUserName", userNameStr);
	}

	public int getRoleId() {return Integer.parseInt(this.session.getAttribute("litopRoleId").toString());}
	public void setRoleId(int litopRoleId) {
		this.session.setAttribute("litopRoleId", litopRoleId);
	}

	public String getRoleName() {return (String)this.session.getAttribute("litopRoleName");}
	public void setRoleName(String roleName) {
		this.session.setAttribute("litopRoleName", roleName);
	}

	public String getIp() {
		return (String)this.session.getAttribute("ip");
	}
	public void setIp(String ip) {
		this.session.setAttribute("ip", ip);
	}


}
