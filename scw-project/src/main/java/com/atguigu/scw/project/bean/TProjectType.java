package com.atguigu.scw.project.bean;

public class TProjectType {
	private Integer id;

	private Integer projectid;

	private Integer typeid;

	public TProjectType() {
		super();
	}

	public TProjectType(Integer id, Integer projectid, Integer typeid) {
		super();
		this.id = id;
		this.projectid = projectid;
		this.typeid = typeid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProjectid() {
		return projectid;
	}

	public void setProjectid(Integer projectid) {
		this.projectid = projectid;
	}

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
}