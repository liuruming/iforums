package net.iforums.service;

import net.iforums.beans.User;

public interface SSOService {

	/**
	 * 验证用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	public User validateLogin(String username, String password);
	
}
