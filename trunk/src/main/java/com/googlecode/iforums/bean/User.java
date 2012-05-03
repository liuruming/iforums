/*
 * Copyright (c) JForum Team
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, 
 * with or without modification, are permitted provided 
 * that the following conditions are met:
 * 
 * 1) Redistributions of source code must retain the above 
 * copyright notice, this list of conditions and the 
 * following  disclaimer.
 * 2)  Redistributions in binary form must reproduce the 
 * above copyright notice, this list of conditions and 
 * the following disclaimer in the documentation and/or 
 * other materials provided with the distribution.
 * 3) Neither the name of "Rafael Steil" nor 
 * the names of its contributors may be used to endorse 
 * or promote products derived from this software without 
 * specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT 
 * HOLDERS AND CONTRIBUTORS "AS IS" AND ANY 
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, 
 * BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR 
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL 
 * THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE 
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES 
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, 
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER 
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER 
 * IN CONTRACT, STRICT LIABILITY, OR TORT 
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN 
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF 
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE
 * 
 * This file creating date: Feb 17, 2003 / 10:25:04 PM
 * The JForum Project
 * http://www.jforum.net 
 * 
 * $Id: User.java,v 1.21 2007/07/25 03:08:15 rafaelsteil Exp $
 */
package com.googlecode.iforums.bean;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.iforums.util.JacksonUtils;

/**
 * Represents a single user in the system.
 * An user is every person which uses the forum. Well,
 * every registered user. Anonymous users does not have
 * a specific ID, for example. This class contains all information
 * about some user configuration options and preferences.
 * 
 * @author Rafael Steil
 */
public class User implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8783648264066118687L;

	private int id;
	private boolean active;
	private String userName;
	private String password;
	private long sessionTime;
	private int sessionPage;
	private Date lastVisit;
	private Date registrationDate;
	private int level;
	private int posts;
	private String timezone;
	private String style;
	private String lang;
	private String dateFormat;
	
	private int newMessage;
	private int unreadMessage;
	private Date lastMessage;
	private Date emailTime;
	private boolean viewEmail;
	private boolean attach;
	private boolean htmlEnabled;
	private boolean bbcodeEnabled;
	private boolean smiliesEnabled;
	private boolean avatarEnabled;
	private boolean pmEnabled;
	private boolean viewonlineEnabled;
	private boolean notify;
	private boolean notifyalways;
	private boolean notifytext;
	private boolean notifypm;
	private boolean popuppm;
	
	private int rankId;
	private String avatar;
	private int avatarType;
	private String email;
	private String icq;
	private String website;
	private String from;
	private String sig;
	private String bbcodeUid;
	private String aim;
	private String yim;
	private String msnm;
	private String occ;
	private String interests;
	private String biography;
	private String actkey;
	private String gender;
	private int themesId;
	private boolean deleted;
	private boolean viewOnline;
	private String securityhash;
	private boolean karmaPoints;
	private String authHash;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getSessionTime() {
		return sessionTime;
	}

	public void setSessionTime(long sessionTime) {
		this.sessionTime = sessionTime;
	}

	public int getSessionPage() {
		return sessionPage;
	}

	public void setSessionPage(int sessionPage) {
		this.sessionPage = sessionPage;
	}

	public Date getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(Date lastVisit) {
		this.lastVisit = lastVisit;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getPosts() {
		return posts;
	}

	public void setPosts(int posts) {
		this.posts = posts;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public int getNewMessage() {
		return newMessage;
	}

	public void setNewMessage(int newMessage) {
		this.newMessage = newMessage;
	}

	public int getUnreadMessage() {
		return unreadMessage;
	}

	public void setUnreadMessage(int unreadMessage) {
		this.unreadMessage = unreadMessage;
	}

	public Date getLastMessage() {
		return lastMessage;
	}

	public void setLastMessage(Date lastMessage) {
		this.lastMessage = lastMessage;
	}

	public Date getEmailTime() {
		return emailTime;
	}

	public void setEmailTime(Date emailTime) {
		this.emailTime = emailTime;
	}

	public boolean isViewEmail() {
		return viewEmail;
	}

	public void setViewEmail(boolean viewEmail) {
		this.viewEmail = viewEmail;
	}

	public boolean isAttach() {
		return attach;
	}

	public void setAttach(boolean attach) {
		this.attach = attach;
	}

	public boolean isHtmlEnabled() {
		return htmlEnabled;
	}

	public void setHtmlEnabled(boolean htmlEnabled) {
		this.htmlEnabled = htmlEnabled;
	}

	public boolean isBbcodeEnabled() {
		return bbcodeEnabled;
	}

	public void setBbcodeEnabled(boolean bbcodeEnabled) {
		this.bbcodeEnabled = bbcodeEnabled;
	}

	public boolean isSmiliesEnabled() {
		return smiliesEnabled;
	}

	public void setSmiliesEnabled(boolean smiliesEnabled) {
		this.smiliesEnabled = smiliesEnabled;
	}

	public boolean isAvatarEnabled() {
		return avatarEnabled;
	}

	public void setAvatarEnabled(boolean avatarEnabled) {
		this.avatarEnabled = avatarEnabled;
	}

	public boolean isPmEnabled() {
		return pmEnabled;
	}

	public void setPmEnabled(boolean pmEnabled) {
		this.pmEnabled = pmEnabled;
	}

	public boolean isViewonlineEnabled() {
		return viewonlineEnabled;
	}

	public void setViewonlineEnabled(boolean viewonlineEnabled) {
		this.viewonlineEnabled = viewonlineEnabled;
	}

	public boolean isNotify() {
		return notify;
	}

	public void setNotify(boolean notify) {
		this.notify = notify;
	}

	public boolean isNotifyalways() {
		return notifyalways;
	}

	public void setNotifyalways(boolean notifyalways) {
		this.notifyalways = notifyalways;
	}

	public boolean isNotifytext() {
		return notifytext;
	}

	public void setNotifytext(boolean notifytext) {
		this.notifytext = notifytext;
	}

	public boolean isNotifypm() {
		return notifypm;
	}

	public void setNotifypm(boolean notifypm) {
		this.notifypm = notifypm;
	}

	public boolean isPopuppm() {
		return popuppm;
	}

	public void setPopuppm(boolean popuppm) {
		this.popuppm = popuppm;
	}

	public int getRankId() {
		return rankId;
	}

	public void setRankId(int rankId) {
		this.rankId = rankId;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getAvatarType() {
		return avatarType;
	}

	public void setAvatarType(int avatarType) {
		this.avatarType = avatarType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIcq() {
		return icq;
	}

	public void setIcq(String icq) {
		this.icq = icq;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSig() {
		return sig;
	}

	public void setSig(String sig) {
		this.sig = sig;
	}

	public String getBbcodeUid() {
		return bbcodeUid;
	}

	public void setBbcodeUid(String bbcodeUid) {
		this.bbcodeUid = bbcodeUid;
	}

	public String getAim() {
		return aim;
	}

	public void setAim(String aim) {
		this.aim = aim;
	}

	public String getYim() {
		return yim;
	}

	public void setYim(String yim) {
		this.yim = yim;
	}

	public String getMsnm() {
		return msnm;
	}

	public void setMsnm(String msnm) {
		this.msnm = msnm;
	}

	public String getOcc() {
		return occ;
	}

	public void setOcc(String occ) {
		this.occ = occ;
	}

	public String getInterests() {
		return interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getActkey() {
		return actkey;
	}

	public void setActkey(String actkey) {
		this.actkey = actkey;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getThemesId() {
		return themesId;
	}

	public void setThemesId(int themesId) {
		this.themesId = themesId;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isViewOnline() {
		return viewOnline;
	}

	public void setViewOnline(boolean viewOnline) {
		this.viewOnline = viewOnline;
	}

	public String getSecurityhash() {
		return securityhash;
	}

	public void setSecurityhash(String securityhash) {
		this.securityhash = securityhash;
	}

	public boolean isKarmaPoints() {
		return karmaPoints;
	}

	public void setKarmaPoints(boolean karmaPoints) {
		this.karmaPoints = karmaPoints;
	}

	public String getAuthHash() {
		return authHash;
	}

	public void setAuthHash(String authHash) {
		this.authHash = authHash;
	}

	public String toString(){
		return JacksonUtils.toString(this);
	}
}
