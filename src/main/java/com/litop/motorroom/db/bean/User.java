package com.litop.motorroom.db.bean;

import java.util.Date;

/**
 * Created by litop on 2016/7/18.
 */
public class User {

  private int id;
  private String userName;
  private String password;
  private Date createTime;

  private Date updateTime;

  private int status;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getStatus() {return status;}

  public void setStatus(int status) {
    this.status = status;
  }
}
