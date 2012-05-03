package com.googlecode.iforums.bean;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1966444533537439862L;
    private int id;
    private int topicId;
    private int forumId;
    private int userId;
    private Date postTime;
    private String posterIp;
    private boolean enableBbcode;
    private boolean enableHtml;
    private boolean enableSmilies;
    private boolean enableSig;
    private Date postEditTime;
    private int postEditCount;
    private boolean status;
    private boolean attach;
    private boolean needModerate;
    
    private User user;
    
    private PostText postText;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getTopicId() {
        return topicId;
    }
    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }
    public int getForumId() {
        return forumId;
    }
    public void setForumId(int forumId) {
        this.forumId = forumId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public Date getPostTime() {
        return postTime;
    }
    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }
    public String getPosterIp() {
        return posterIp;
    }
    public void setPosterIp(String posterIp) {
        this.posterIp = posterIp;
    }
    public boolean isEnableBbcode() {
        return enableBbcode;
    }
    public void setEnableBbcode(boolean enableBbcode) {
        this.enableBbcode = enableBbcode;
    }
    public boolean isEnableHtml() {
        return enableHtml;
    }
    public void setEnableHtml(boolean enableHtml) {
        this.enableHtml = enableHtml;
    }
    public boolean isEnableSmilies() {
        return enableSmilies;
    }
    public void setEnableSmilies(boolean enableSmilies) {
        this.enableSmilies = enableSmilies;
    }
    public boolean isEnableSig() {
        return enableSig;
    }
    public void setEnableSig(boolean enableSig) {
        this.enableSig = enableSig;
    }
    public Date getPostEditTime() {
        return postEditTime;
    }
    public void setPostEditTime(Date postEditTime) {
        this.postEditTime = postEditTime;
    }
    public int getPostEditCount() {
        return postEditCount;
    }
    public void setPostEditCount(int postEditCount) {
        this.postEditCount = postEditCount;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public boolean isAttach() {
        return attach;
    }
    public void setAttach(boolean attach) {
        this.attach = attach;
    }
    public boolean isNeedModerate() {
        return needModerate;
    }
    public void setNeedModerate(boolean needModerate) {
        this.needModerate = needModerate;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public PostText getPostText() {
        return postText;
    }
    public void setPostText(PostText postText) {
        this.postText = postText;
    }
}
