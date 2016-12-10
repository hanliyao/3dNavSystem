package com.litop.motorroom.db.mapper;
import java.util.List;
import java.util.Map;

import com.litop.motorroom.db.bean.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by litop on 2016/7/18.
 */
@Mapper
public interface RoleMapper {

    List<Role> getAll();

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);


    List<Role> selectRoleListPage(Map paramMap);
    int getCounts(Map paramMap);
}