package net.iforums.dao.impl;

import java.util.List;

import net.iforums.beans.*;
import net.iforums.dao.*;

public class BookmarkDaoImpl extends BaseORMDao<Bookmark> implements BookmarkDAO{

	@Override
	public List<Bookmark> selectByUser(int userId, int relationType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bookmark> selectByUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Bookmark selectForUpdate(int relationId, int relationType, int userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
