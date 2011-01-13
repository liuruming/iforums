package net.iforums.dao.impl;

import net.iforums.beans.*;
import net.iforums.dao.*;
import net.jforum.security.Role;
import net.jforum.security.RoleCollection;
import net.jforum.security.RoleValueCollection;

public class GroupSecurityDAOImpl extends BaseORMDao<Group> implements GroupSecurityDAO {

	@Override
	public void addRole(int groupId, Role role) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addRole(int id, Role role, RoleValueCollection roleValues) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addRoleValue(int id, Role role, RoleValueCollection rvc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllRoles(int groupId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteForumRoles(int forumId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RoleCollection loadRoles(int groupId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleCollection loadRolesByUserGroups(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
