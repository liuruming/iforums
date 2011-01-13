package net.iforums.dao.impl;

import java.util.List;

import net.iforums.beans.*;
import net.iforums.dao.*;

public class GroupDaoImpl extends BaseORMDao<Group> implements GroupDAO{

	@Override
	public boolean canDelete(int groupId) {
		return false;
	}

	@Override
	public List selectUsersIds(int groupId) {
		return null;
	}

}
