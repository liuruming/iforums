package com.googlecode.iforums.bean;

import java.io.Serializable;
import java.util.Date;

public class Topic implements Serializable {
	/**
     * 
     */
    private static final long serialVersionUID = -9077030223726774545L;
    
    private int id;
    private int  forumId;
    private String  title;
    private int  userId;
    private Date  time;
    private int  views;
    private int  replies;
    private int  status;
    private int  voteId;
    private int  type;
    private int  firstPostId;
    private int  lastPostId;
    private int  movedId;
    private boolean  moderated;
    
    private Forum forum;
    
    private User user;
    
    private Post firstPost;
    
    private Post lastPost;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getForumId() {
        return forumId;
    }
    public void setForumId(int forumId) {
        this.forumId = forumId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
        this.time = time;
    }
    public int getViews() {
        return views;
    }
    public void setViews(int views) {
        this.views = views;
    }
    public int getReplies() {
        return replies;
    }
    public void setReplies(int replies) {
        this.replies = replies;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public int getVoteId() {
        return voteId;
    }
    public void setVoteId(int voteId) {
        this.voteId = voteId;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public int getFirstPostId() {
        return firstPostId;
    }
    public void setFirstPostId(int firstPostId) {
        this.firstPostId = firstPostId;
    }
    public int getLastPostId() {
        return lastPostId;
    }
    public void setLastPostId(int lastPostId) {
        this.lastPostId = lastPostId;
    }
    public int getMovedId() {
        return movedId;
    }
    public void setMovedId(int movedId) {
        this.movedId = movedId;
    }
    public boolean isModerated() {
        return moderated;
    }
    public void setModerated(boolean moderated) {
        this.moderated = moderated;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Post getFirstPost() {
        return firstPost;
    }
    public void setFirstPost(Post firstPost) {
        this.firstPost = firstPost;
    }
    public Post getLastPost() {
        return lastPost;
    }
    public void setLastPost(Post lastPost) {
        this.lastPost = lastPost;
    }
    public Forum getForum() {
        return forum;
    }
    public void setForum(Forum forum) {
        this.forum = forum;
    }
}
