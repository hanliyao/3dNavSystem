package com.litop.motorroom.db.bean;

import java.util.Date;

/**
 * Created by litop on 2016/7/18.
 */
public class Admin {

	private Integer id;

	private String userName;

	private String realName;

	private String password;

	private Date addTime;

	private Date lastTime;

	private Integer status;

	private Integer roleId;

	private String roleName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName == null ? null : realName.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {this.addTime = addTime;}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(Integer status) {this.status = status;}

	public Integer getRoleId() {return roleId;}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName == null ? null : roleName.trim();
	}

	@Override
	public String toString() {
		return "Admin{" +
				"id=" + id +
				", userName='" + userName + '\'' +
				", realName='" + realName + '\'' +
				", password='" + password + '\'' +
				", addTime=" + addTime +
				", lastTime=" + lastTime +
				", status=" + status +
				", roleId=" + roleId +
				", roleName='" + roleName + '\'' +
				'}';
	}
}
