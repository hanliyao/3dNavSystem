package com.litop.motorroom.db.bean;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private Integer id;

	private String module;

	private String moduleName;

	private String action;

	private String actionName;

	private Integer parentId;

	private String data;

	private Integer status;

	private String remark;

	private Integer sort;

	private Integer authType;

	private Integer groupId;

	private Integer often;

	private Integer isShow;

	private List<Node> childNode;

	public List<Node> getChildNode() {
		return childNode;
	}

	public void setChildNode(List<Node> childNode) {
		this.childNode = childNode;
	}

	public Node( ) {
		this.childNode = new ArrayList<Node>();;
	}

	public void addNode(Node node)
	{
		this.childNode.add(node);
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module == null ? null : module.trim();
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName == null ? null : moduleName.trim();
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action == null ? null : action.trim();
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName == null ? null : actionName.trim();
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data == null ? null : data.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getAuthType() {
		return authType;
	}

	public void setAuthType(Integer authType) {
		this.authType = authType;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getOften() {
		return often;
	}

	public void setOften(Integer often) {
		this.often = often;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	@Override
	public String toString() {
		return "Node{" +
				"id=" + id +
				", module='" + module + '\'' +
				", moduleName='" + moduleName + '\'' +
				", childNode=" + childNode +
				'}';
	}
}