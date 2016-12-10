package com.litop.motorroom.litop;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.litop.motorroom.utils.JsonUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * LitopController 本系统中有些类需要Controller 继承此类
 * 
 * @author Administrator
 * 
 */
public class LitopController {
	private static final Log log = LogFactory.getLog(LitopController.class);
	/**
	 * 默认编码方式
	 */
	private static final String DEFAULT_CONTENT_TYPE = "text/plain;charset=UTF-8";

	/**
	 * 以json的方式传递object
	 * 
	 * @param response
	 * @param object
	 * @return
	 */
	protected void render(HttpServletResponse response, Object object) {
		String str = JsonUtil.beanToJsonString(object);
		System.out.println(str);
		render(response, str);
	}
	protected void render(HttpServletResponse response, JSONObject jsonObject) {
		render(response,jsonObject.toString());
	}
	protected void render(HttpServletResponse response, JSONArray jsonArray) {
		render(response,jsonArray.toString());
	}
	protected void render(HttpServletResponse response, String text) {
		render(response, text, DEFAULT_CONTENT_TYPE);
	}
	protected void render(HttpServletResponse response, String text,
			String contentType) {
		try {
			if(StringUtils.isEmpty(contentType)){
				response.setContentType(DEFAULT_CONTENT_TYPE);
			}else{
				response.setContentType(contentType);
			}
			response.getWriter().write(text);
		} catch (IOException e) {
			log.error("发送jsong数据IO异常", e);
		}
	}

	/**
	 * 对ajax请求放回成功
	 * 
	 * @param response
	 * @param retrunCode
	 *            返回码
	 * @param retrunMsg
	 *            返回的成功信息
	 */
	protected void renderOk(HttpServletResponse response, String retrunCode,String retrunMsg) {
		LitopMsg<String> litopMsg = new LitopMsg<String>(true,retrunCode, retrunMsg);
		render(response, litopMsg);
	}

	/**
	 * 对ajax请求放回成功
	 * 
	 * @param response
	 * @param retrunMsg
	 *            返回的成功信息
	 */
	protected void renderOk(HttpServletResponse response, String retrunMsg) {
		this.renderOk(response, "1", retrunMsg);
	}

	/**
	 * 对ajax请求放回成功
	 * 
	 * @param response
	 */
	protected void renderOk(HttpServletResponse response) {
		this.renderOk(response, "1", "成功");
	}

	/**
	 * 返回带结果的成功
	 * 
	 * @param response
	 * @param result
	 */
	protected void renderOkWithResult(HttpServletResponse response,
			Object result) {
		this.renderOkWithResult(response, "1", "成功", result);
	}

	/**
	 * 返回带结果的成功
	 * 
	 * @param response
	 * @param retrunCode
	 * @param retrunMsg
	 * @param result
	 */
	protected void renderOkWithResult(HttpServletResponse response,
			String retrunCode, String retrunMsg, Object result) {
		LitopMsg<Object> LitopMsg = new LitopMsg<Object>(true,
				retrunCode, retrunMsg, result);
		render(response, LitopMsg);
	}

	/**
	 * 对ajax请求返回异常
	 * 
	 * @param response
	 * @param e
	 */
	protected void renderException(HttpServletResponse response, Exception e) {
		LitopMsg<String> LitopMsg = new LitopMsg<String>(false,e.getMessage());
		render(response, LitopMsg);
	}

}
