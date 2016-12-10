package com.litop.motorroom.db.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by hanliyao on 2016.08.30.
 */
@Mapper
public interface URMapper {
    int getRolebyUserID(int id);

    List<Integer> getUserByRoleID(int id);


}
