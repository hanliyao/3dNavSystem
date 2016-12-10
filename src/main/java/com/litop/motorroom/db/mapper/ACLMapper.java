package com.litop.motorroom.db.mapper;

import com.litop.motorroom.db.bean.ACL;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by hanliyao on 2016.08.29.
 */
@Mapper
public interface ACLMapper {
    //
    List<ACL> getACLLbyGroupID(int id);
    //更新访问控制列表
    int insertModulebyModuleID(int id);

    int deleteModulebyModuleID(int id);

    List<ACL> getACLbyRoleID(int id);
}
