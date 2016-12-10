package com.litop.motorroom.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.math.RandomUtils;


public class IdFactory {

	private static final DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	private IdFactory(){
		
	}
	/**
	 * 获取时间戳转换的ID
	 * @return
	 * @throws InterruptedException
	 */
    public  static synchronized String getId() throws InterruptedException  
    {  
    	Date date = new Date();
    	Thread.sleep(1);
    	String dateStr = df.format(date);
    	int num = RandomUtils.nextInt(899)+100;
    	return dateStr+num;
    	
    }  
  
    public static void main(String[] xiaoe) throws InterruptedException  
  
    {  
     
        for (int i = 0; i < 1000; i++)  
            System.out.println(IdFactory.getId());  
    }  

}
