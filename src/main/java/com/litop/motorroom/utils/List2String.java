package com.litop.motorroom.utils;

import com.litop.motorroom.db.bean.ACL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hanliyao on 2016.08.29.
 */
public class List2String {
    public static List<String> ACL2String(List<ACL> list)
    {
        List<String> result = new ArrayList<String>();
        for(ACL acl : list)
        {
            result.add(acl.getModuleID()+"");
        }
        return result;
    }

    public static Map<String,String> String2Map(String message)
    {
        Map<String,String> map = new HashMap<String, String>();
        if(message.length()>0)
        {
            String[] list = message.split(";");
            for(String item : list)
            {
                if(item.length()>0)
                {
                    String[] node = item.split(",");
                    map.put(node[0],node[1]);
                }
            }
        }
        return map;
    }

}
