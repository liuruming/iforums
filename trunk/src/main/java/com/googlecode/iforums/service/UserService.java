package com.googlecode.iforums.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.googlecode.iforums.bean.User;
import com.googlecode.iforums.dao.UserMapper;

/**
 * 
 * @author zhurx
 * 
 */
@Service
public class UserService extends BaseService<User> {
	
	@Resource
	private UserMapper userMapper;

	
	public void setMapperHandlewired() {
		super.setMapper(userMapper);
	}
	

    public User getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    
    public User getUserByUserName(String userName) {
        return userMapper.getUserByUserName(userName);
    }
    
    public User getLatestUser(){
        return userMapper.getLatestUser();
    }

    
    public List<User> searchUser(String email) {
        return userMapper.searchUser(email);
    }

    
    public int searchUserCount(String email) {
        return userMapper.searchUserCount(email);
    }

    
    public boolean isExist(String userName, String email) {
        return userMapper.isExist(userName,email)>0;
    }

    
    public boolean updateUserLevel(int id, int level) {
        return userMapper.updateUserLevel(id, level);
    }

    
    public boolean updateUserPassword(int id, String password) {
        return userMapper.updateUserPassword(id, password);
    }
    
    public int getTotal(){
        return userMapper.getTotal();
    }
}
