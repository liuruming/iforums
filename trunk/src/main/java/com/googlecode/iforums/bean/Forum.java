package com.googlecode.iforums.bean;

import java.io.Serializable;

/**
 * 
 * @author zhurx
 *
 */
public class Forum implements Serializable {
	/**
     * 
     */
    private static final long serialVersionUID = -7993267242512244208L;
    private int id;
	private int categoryId;
	private String name;
	private String description;
	private int weight;
	private int topics;
	private int posts;
	private int lastTopicId;
	private boolean moderated;
	
	private Topic lastTopic;
	
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public int getTopics() {
        return topics;
    }
    public void setTopics(int topics) {
        this.topics = topics;
    }
    public int getPosts() {
        return posts;
    }
    public void setPosts(int posts) {
        this.posts = posts;
    }

    public boolean isModerated() {
        return moderated;
    }
    public void setModerated(boolean moderated) {
        this.moderated = moderated;
    }
    public int getLastTopicId() {
        return lastTopicId;
    }
    public void setLastTopicId(int lastTopicId) {
        this.lastTopicId = lastTopicId;
    }
    public Topic getLastTopic() {
        return lastTopic;
    }
    public void setLastTopic(Topic lastTopic) {
        this.lastTopic = lastTopic;
    }

}
