package com.litop.motorroom.db.bean;

/**
 * Created by hanliyao on 2016.08.29.
 */
public class ACL {
    private int ID;
    private int UserID;
    private int RoleID;
    private int GroupID;
    private int ModuleID;
    private int CreateTime;
    private int Flag;

    public int getId() {
        return ID;
    }

    public void setId(int id) {
        this.ID = id;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getRoleID() {
        return RoleID;
    }

    public void setRoleID(int roleID) {
        RoleID = roleID;
    }

    public int getGroupID() {
        return GroupID;
    }

    public void setGroupID(int groupID) {
        GroupID = groupID;
    }

    public int getModuleID() {
        return ModuleID;
    }

    public void setModuleID(int moduleID) {
        ModuleID = moduleID;
    }

    public int getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(int createTime) {
        CreateTime = createTime;
    }

    public int getFlag() {
        return Flag;
    }

    public void setFlag(int flag) {
        Flag = flag;
    }

    @Override
    public String toString() {
        return "ACL{" + "ID=" + ID + '}';
    }

    public ACL(int userID, int roleID, int groupID, int moduleID, int createTime, int flag) {
        UserID = userID;
        RoleID = roleID;
        GroupID = groupID;
        ModuleID = moduleID;
        CreateTime = createTime;
        Flag = flag;
    }

    public ACL() {

    }
}
