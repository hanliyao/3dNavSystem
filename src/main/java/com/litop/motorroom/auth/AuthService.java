package com.litop.motorroom.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.litop.motorroom.litop.LitopException;
import com.litop.motorroom.db.bean.Admin;

public interface AuthService {

	/**
	 * 用户登录
	 * 
	 * @param adminBean
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public  Admin login(Admin adminBean, HttpServletRequest request) throws LitopException,Exception;

	/**
	 * 用户登出
	 * 
	 * @param adminBean
	 * @param request
	 * @return
	 */
	public boolean logout(Admin adminBean, HttpSession session);

	/**
	 * 获取不需要过滤的文件
	 * 
	 * @return
	 * @throws Exception 
	 */
	public boolean filterUrl(HttpServletRequest request) throws Exception;

	/**
	 * 重新设置用户权限的缓冲
	 * @param contextPath
	 * @throws Exception
	 */
	public void resetCache(String contextPath) throws Exception;


}