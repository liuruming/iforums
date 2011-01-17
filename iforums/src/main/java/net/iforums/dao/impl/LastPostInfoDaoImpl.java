package net.iforums.dao.impl;

import java.util.HashMap;
import java.util.Map;

import net.iforums.beans.LastPostInfo;
import net.iforums.dao.BaseORMDao;
import net.iforums.dao.LastPostInfoDao;
import net.sf.cglib.beans.BeanMap;

import org.springframework.stereotype.Repository;

@Repository
public class LastPostInfoDaoImpl extends BaseORMDao<LastPostInfo> implements LastPostInfoDao {

	public LastPostInfoDaoImpl(){
		this.setNamespace("LastPostInfo");
	}
	@Override
	public LastPostInfo getLastPostInfo(int forumId) {
        Map<String,Object> params = new HashMap<String,Object>(2);
        params.put("forumId", forumId);
        logger.info(params);
        update("getLastPostInfo",params);    
		return null;
	}

}
