package com.litop.motorroom.db.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanliyao on 2016.08.27.
 */
public class ModuleTree {
    public String moduleName;
    public String moduleID;
    List<ModuleTree> child;
    public int status;//0标识这个模块没有被启用，1标识这个模块已经被启用

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleID() {
        return moduleID;
    }

    public void setModuleID(String moduleID) {
        this.moduleID = moduleID;
    }

    public List<ModuleTree> getChild() {
        return child;
    }

    public void addChild(ModuleTree child) {
        this.child.add(child);
    }

    public ModuleTree() {
        this.child = new ArrayList<ModuleTree>();
        this.status = 0;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ModuleTree{" +
                "moduleName='" + moduleName + '\'' +
                ", moduleID='" + moduleID + '\'' +
                ", status=" + status +
                ", child=" + child +
                '}';
    }
}
