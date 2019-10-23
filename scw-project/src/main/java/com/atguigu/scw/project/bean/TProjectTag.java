package com.atguigu.scw.project.bean;

public class TProjectTag {
	private Integer id;

	private Integer projectid;

	private Integer tagid;

	public TProjectTag() {
		super();
	}

	public TProjectTag(Integer id, Integer projectid, Integer tagid) {
		super();
		this.id = id;
		this.projectid = projectid;
		this.tagid = tagid;
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

	public Integer getTagid() {
		return tagid;
	}

	public void setTagid(Integer tagid) {
		this.tagid = tagid;
	}
}