package net.iforums.service.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;

import net.iforums.beans.User;
import net.iforums.dao.UserDao;
import net.iforums.service.SSOService;
import net.iforums.utils.DbUtils;
import net.iforums.utils.MD5;

import org.springframework.stereotype.Service;

@Service
public class SSOServiceImpl extends BaseServiveImpl implements SSOService {
	
	@Resource
	private UserDao userDao;
	
	public User validateLogin(String username, String password)
	{
		
		User user = userDao.getUserByName(username,MD5.crypt(password));

		if (user != null && !user.isDeleted() && (user.getActivationKey() == null || user.isActive())) {
			return user;
		}

		return null;
	}
}
