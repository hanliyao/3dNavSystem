package com.litop.motorroom.db.mapper;
import java.util.List;

import com.litop.motorroom.db.bean.Admin;
import org.apache.ibatis.annotations.Mapper;

/**
 * 权限控制的Mapper
 * @author
 *
 */
@Mapper
public interface AuthMapper {
	/**
	 * 根据roleId可以访问的URL
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<String> getUrlByRoleId(int roleId) throws Exception;

	/**
	 * 根据userId查可以访问的RoleId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<String> getRoleByUserId(int userId) throws Exception;

	/**
	 * 根据userName查询登录用户
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public Admin getAuthByUserName(String userName) throws Exception;

}
