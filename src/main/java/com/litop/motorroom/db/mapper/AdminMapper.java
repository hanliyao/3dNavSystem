package com.litop.motorroom.db.mapper;
import java.util.List;
import java.util.Map;

import com.litop.motorroom.db.bean.Admin;
import com.litop.motorroom.db.bean.Node;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

/**
 * Created by litop on 2016/7/18.
 */

@Mapper
public interface AdminMapper {

    List<Admin> getAll();

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
   //通过用户名获取用户信息
    Admin getAdminByUserName(String userName);

    List<Admin> selectAdminListPage(Map paramMap);

    int getCounts(Map paramMap);

    int initPassword(Map paramMap);
}