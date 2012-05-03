package com.googlecode.iforums.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 论坛分区
 * @author zhurx
 *
 */
public class Category implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = 8708461659704617674L;
    private int id;
	private String name;
	private int weight;
	private boolean moderated;
	private List<Forum> forumList;
	
	public Category() {}
	
	public Category(int id) {
		this.id = id;
	}
	
	public Category(String name, int id) {
		this.name = name;
		this.id = id;
	}
	
	public Category(Category c) {
		this.name = c.getName();
		this.id = c.getId();
		this.weight = c.getWeight();
		this.moderated = c.isModerated();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void Weight(int weight) {
		this.weight = weight;
	}

	public boolean isModerated() {
		return moderated;
	}

	public void setModerated(boolean moderated) {
		this.moderated = moderated;
	}

	public List<Forum> getForumList() {
		return forumList;
	}

	public void setForumList(List<Forum> forumList) {
		this.forumList = forumList;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
