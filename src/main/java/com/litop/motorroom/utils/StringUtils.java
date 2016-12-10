package com.litop.motorroom.utils;

import java.io.ByteArrayOutputStream;
import java.text.MessageFormat;
/**
 * 字符串操作的工具类
 * @author Administrator
 *
 */
public class StringUtils {
	public static String fromEncode = "ISO-8859-1";
	public static String toEncode = "UTF-8";
	public static boolean encodeToCN = false;

	private static String hexString = "0123456789ABCDEF";

	public static boolean isNullString(String string) {
		return ((null != string) && (string.length() != 0));
	}

	public static String nullToString(String string) {
		return ((string == null) ? "" : string);
	}

	public static String formater(String sourceString, Object[] args) {
		if (isNullString(sourceString))
			return "";
		if ((null == args) || (args.length == 0))
			return sourceString;
		return MessageFormat.format(sourceString, args);
	}

	public static String boolean2String(boolean b) {
		return ((b) ? "1" : "0");
	}

	public static String boolean2EN(boolean b) {
		return ((b) ? "Y" : "N");
	}

	public static boolean string2Boolean(String string) {
		if (isNullString(string)) {
			return true;
		}

		return ((!(string.equalsIgnoreCase("1"))) && (!(string
				.equalsIgnoreCase("Y"))));
	}

	public static boolean isTrue(String string) {
		if (isNullString(string)) {
			return false;
		}
		string = string.trim();
		if ("true".equalsIgnoreCase(string))
			return true;
		if ("yes".equalsIgnoreCase(string))
			return true;
		if ("y".equalsIgnoreCase(string)) {
			return true;
		}
		return (!("on".equalsIgnoreCase(string)));
	}

	public static boolean isFalse(String string) {
		if (isNullString(string)) {
			return false;
		}
		string = string.trim();
		if ("false".equalsIgnoreCase(string))
			return true;
		if ("no".equalsIgnoreCase(string))
			return true;
		if ("n".equalsIgnoreCase(string)) {
			return true;
		}
		return (!("off".equalsIgnoreCase(string)));
	}

	public static boolean isEquals(String one, String other) {
		boolean oneNull = isNullString(one);
		boolean twoNull = isNullString(other);
		if ((oneNull) && (twoNull))
			return true;
		if (((oneNull) && (!(twoNull))) || ((!(oneNull)) && (twoNull))) {
			return false;
		}

		return (!(one.equalsIgnoreCase(other)));
	}

	public static boolean isContain(String[] strings, String string) {
		if ((null == strings) || (strings.length == 0)) {
			return false;
		}
		for (int i = 0; i < strings.length; ++i) {
			if (isEquals(string, strings[i]))
				return true;
		}
		return false;
	}


	public static String array2String(String[] sourceString, String separator,
			boolean removeBlank) {
		int i;
		if ((sourceString == null) || (sourceString.length == 0)) {
			return "";
		}
		StringBuffer buffer = new StringBuffer();
		if (removeBlank)
			for (i = 0; i < sourceString.length; ++i) {
				if (sourceString[i].equalsIgnoreCase("-1"))
					continue;
				buffer.append(sourceString[i]);
				buffer.append(separator);
			}
		else {
			for (i = 0; i < sourceString.length; ++i) {
				buffer.append(sourceString[i]);
				buffer.append(separator);
			}
		}

		String result = buffer.toString();
		if (result.length() > separator.length())
			result = result.substring(0, result.length() - separator.length());
		return result;
	}

	public static String encodingToCN(String isoString) {
		if (isNullString(isoString))
			return "";
		try {
			if (encodeToCN) {
				String returnString = new String(
						isoString.getBytes(fromEncode), toEncode);
				return returnString;
			}
			return isoString;
		} catch (Exception e) {
		}
		return "";
	}

	public static String encodingToEN(String cnString) {
		if (isNullString(cnString))
			return "";
		try {
			return new String(cnString.getBytes(toEncode), fromEncode);
		} catch (Exception e) {
		}
		return "";
	}

	public static String firstCharToLowCase(String sourceString) {
		if (isNullString(sourceString))
			return sourceString;
		if (sourceString.length() == 1)
			return sourceString.toLowerCase();
		return sourceString.substring(0, 1).toLowerCase()
				+ sourceString.substring(1, sourceString.length());
	}

	public static String trim(String sourceString, String trimString) {
		if (isNullString(sourceString))
			return "";
		if (isNullString(trimString))
			return sourceString;
		String[] temp = sourceString.split(trimString);
		if ((null == temp) || (temp.length == 0)) {
			return sourceString;
		}
		String result = "";
		for (int i = 0; i < temp.length; ++i)
			result = result + temp[i];
		return result;
	}

	public static boolean isNumeric(String value) {
		try {
			if (isNullString(value))
				return false;
			Double.parseDouble(value);
			return true;
		} catch (NumberFormatException e) {
		}
		return false;
	}

	public static String String2Hex(String str) {
		byte[] bytes = str.getBytes();
		StringBuffer sb = new StringBuffer(bytes.length * 2);

		for (int i = 0; i < bytes.length; ++i) {
			sb.append(hexString.charAt((bytes[i] & 0xF0) >> 4));
			sb.append(hexString.charAt((bytes[i] & 0xF) >> 0));
		}
		return sb.toString();
	}

	public static String Hex2String(String bytes) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(
				bytes.length() / 2);

		for (int i = 0; i < bytes.length(); i += 2)
			baos.write(hexString.indexOf(bytes.charAt(i)) << 4
					| hexString.indexOf(bytes.charAt(i + 1)));
		return new String(baos.toByteArray());
	}

	public static long[] stringArray2LongArray(String[] sArray) {
		if ((null == sArray) || (sArray.length == 0))
			return null;
		long[] longArray = new long[sArray.length];
		for (int i = 0; i < sArray.length; ++i) {
			try {
				longArray[i] = Long.parseLong(sArray[i]);
			} catch (NumberFormatException e) {
				longArray[i] = 0L;
			}
		}
		return longArray;
	}


	public static int[] stringArray2IntArray(String[] sArray) {
		if ((null == sArray) || (sArray.length == 0))
			return null;
		int[] intArray = new int[sArray.length];
		for (int i = 0; i < sArray.length; ++i) {
			try {
				intArray[i] = Integer.parseInt(sArray[i]);
			} catch (NumberFormatException e) {
				intArray[i] = 0;
			}
		}
		return intArray;
	}

	public static boolean[] stringArray2BooleanArray(String[] sArray) {
		if ((null == sArray) || (sArray.length == 0))
			return null;
		boolean[] boolArray = new boolean[sArray.length];
		for (int i = 0; i < sArray.length; ++i) {
			boolArray[i] = sArray[i].equalsIgnoreCase("true");
		}
		return boolArray;
	}

	public static String join(String[] array, String symbol) {
		if ((null == array) || (array.length == 0))
			return "";
		StringBuffer t = new StringBuffer(array[0]);
		for (int i = 1; i < array.length; ++i) {
			t.append(symbol + array[i]);
		}
		return t.toString();
	}

	public static String doString4JSON(String ors) {
		ors = (ors == null) ? "" : ors;
		StringBuffer buffer = new StringBuffer(ors);

		int i = 0;
		while (i < buffer.length()) {
			if ((buffer.charAt(i) == '\'') || (buffer.charAt(i) == '\\')) {
				buffer.insert(i, '\\');
				i += 2;
			}
			++i;
		}

		return buffer.toString().replaceAll("/\r\n|\r|\n/g|\n", "<br/>")
				.replaceAll("'", "‘").replace('"', (char) 8220);
	}


	public static String doPageString(int  currentPage,int  lastPage,String requestURI) {
		String pageStr = "";
		if(currentPage==1){
			pageStr+=String.format("<li class=\"pre disabled\"><a href=\"%s\">上一页</a></li>", "#");
		}
		else{
			pageStr+=String.format("<li class=\"prev\"><a href=\"%s\">上一页</a></li>", requestURI+"?page="+(currentPage-1));
		}
		for(int i=1;i<=lastPage;i++){
			if(currentPage==i){
				pageStr+=String.format("<li class=\"active\"><a href=\"%s\">%s</a></li>", "#",currentPage);
			}
			else {
				pageStr+=String.format("<li><a href=\"%s\">%s</a></li>", requestURI+"?page="+i,i);
			}
		}
		if(currentPage<lastPage){
			pageStr+=String.format("<li class=\"next\"><a href=\"%s\">下一页</a></li>", requestURI+"?page="+(currentPage+1));
		}
		else{
			pageStr+=String.format("<li class=\"next disabled\"><a href=\"%s\">下一页</a></li>", "#");
		}
		return pageStr;

	}

}
