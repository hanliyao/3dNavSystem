package com.litop.motorroom.auth;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * 密码工具类
 * @author
 *
 */
public class PasswordUtils {
	/**
	 * 加密密码
	 * @param username
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
    public static String encryptPassword(String username,String password) throws NoSuchAlgorithmException {         
        MessageDigest messageDigestSha = MessageDigest.getInstance("MD5");
        String str = username+password;
        messageDigestSha.update(str.getBytes());
        byte b[] = messageDigestSha.digest();
        int i;
		StringBuffer buf = new StringBuffer("");
		for (int offset = 0; offset < b.length; offset++) {
			i = b[offset];
			if (i < 0)
				i += 256;
			if (i < 16)
				buf.append("0");
			buf.append(Integer.toHexString(i));
		}
		return buf.toString();
    }
    /**
     * 比较密码
     * @param username
     * @param password
     * @param encryptPassword
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static boolean equalsPassword(String username,String password,String encryptPassword) throws NoSuchAlgorithmException{
    	String passwordInput = PasswordUtils.encryptPassword(username, password);
    	if(encryptPassword.equals(passwordInput)){
    		return true;
    	}else{
    		return false;
    	}
    }

} 
