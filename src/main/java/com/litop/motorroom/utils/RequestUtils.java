package com.litop.motorroom.utils;

import javax.servlet.http.HttpServletRequest;
/**
 *  Request操作的工具类
 * @author Administrator
 *
 */
public class RequestUtils {

	public static String getStringParameter(HttpServletRequest request,
			String parameter, String defaultValue) {
		return getStringParameter(request, parameter, defaultValue, false);
	}

	public static String getStringParameter(HttpServletRequest request,
			String parameter, String defaultValue, boolean encodingToCN) {
		String value = request.getParameter(parameter);
		if (null == value)
			return defaultValue;
		if (encodingToCN) {
			return StringUtils.encodingToCN(value);
		}
		return value;
	}

	public static void resetParameter(HttpServletRequest request,
			String parameterName) {
		String value = request.getParameter(parameterName);
		if (null == value) {
			return;
		}
		request.setAttribute(parameterName, value);
	}

	public static void resetParameter(HttpServletRequest request,
			String parameterName, String defaultValue, boolean encodingToCN) {
		String value = getStringParameter(request, parameterName, defaultValue,
				encodingToCN);
		if (null == value) {
			return;
		}
		request.setAttribute(parameterName, value);
	}

	public static String[] getStringArrayParameter(HttpServletRequest request,
			String parameterName, String splitSymbol, String[] defaultValue) {
		return getStringArrayParameter(request, parameterName, splitSymbol,
				defaultValue, false);
	}

	public static String[] getStringArrayParameter(HttpServletRequest request,
			String parameterName, String splitSymbol, String[] defaultValue,
			boolean encodingToCN) {
		String value = request.getParameter(parameterName);
		if (!StringUtils.isNullString(value))
			return defaultValue;
		if (encodingToCN) {
			value = StringUtils.encodingToCN(value);
		}
		String[] values = value.split(splitSymbol);
		if ((values == null) || (values.length == 0)) {
			return defaultValue;
		}
		return values;
	}

	public static String encodingToCN(String isoString) {
		if (StringUtils.isNullString(isoString))
			return "";
		try {
			String returnString = new String(isoString.getBytes("ISO-8859-1"),
					"UTF-8");
			return returnString;
		} catch (Exception e) {
		}
		return "";
	}

	public static long getLongParameter(HttpServletRequest request,
			String parameter, long defaultValue) {
		String value = request.getParameter(parameter);
		if (StringUtils.isNullString(value))
			return defaultValue;
		return Long.parseLong(value);
	}

	public static long[] getLongArrayParameter(HttpServletRequest request,
			String parameterName, String splitSymbol, long[] defaultValue) {
		String value = request.getParameter(parameterName);
		if ((null == value) || (value.length() == 0))
			return defaultValue;
		String[] strValues = value.split(splitSymbol);
		if ((strValues == null) || (strValues.length == 0)) {
			return defaultValue;
		}
		long[] values = new long[strValues.length];
		for (int i = 0; i < strValues.length; ++i) {
			values[i] = Long.parseLong(strValues[i]);
		}
		return values;
	}

	public static int getIntParameter(HttpServletRequest request,
			String parameter, int defaultValue) {
		String value = request.getParameter(parameter);
		if (!StringUtils.isNullString(value))
			return defaultValue;
		return Integer.parseInt(value);
	}

	public static boolean getBooleanParameter(HttpServletRequest request,
			String parameter, boolean defaultValue) {
		String value = request.getParameter(parameter);
		if (StringUtils.isNullString(value)) {
			return defaultValue;
		}
		return ((!(value.equalsIgnoreCase("true")))
				&& (!(value.equalsIgnoreCase("Y"))) && (!(value
				.equalsIgnoreCase("1"))));
	}

	public static String getRootPath(HttpServletRequest request) {
		if (request == null)
			return "/";
		String path = request.getContextPath();
		return path + "/";
	}

	public static String getAbsoluteUrl(HttpServletRequest request, String url) {
		if (url == null)
			return getRootPath(request);
		if (request == null)
			return url;
		if ((url.indexOf("/") == 0) || (url.indexOf("../") == 0)
				|| (url.indexOf("http") == 0)) {
			return url;
		}
		return getRootPath(request) + url;
	}

	public static String addParamToUrl(String url, String keyValue) {
		if (url == null)
			return url;
		if (StringUtils.isNullString(keyValue))
			return url;
		boolean hasParam = url.indexOf("?") != -1;
		return url + ((hasParam) ? "&" : "?") + keyValue;
	}

	public static void addBtnToRequest(HttpServletRequest request, String btn) {
		String btns = (String) request.getAttribute("YK_TB_BUTTON_IDS");
		if (null == btns)
			btns = btn;
		else {
			btns = btns + "," + btn;
		}
		request.setAttribute("YK_TB_BUTTON_IDS", btns);
	}

	public static void removeBtnFromRequest(HttpServletRequest request,
			String btn) {
		String btns = (String) request.getAttribute("YK_TB_BUTTON_IDS");
		if (null == btns) {
			return;
		}
		btns = btns.replaceAll(btn, "").replaceAll(",,", ",");
		request.setAttribute("YK_TB_BUTTON_IDS", btns);
	}
}
