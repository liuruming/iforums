package net.iforums.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.iforums.beans.Banlist;
import net.iforums.beans.Smilie;
import net.iforums.dao.BanlistDao;
import net.iforums.dao.SmilieDao;
import net.iforums.service.RepositoryService;
import net.iforums.utils.bbcode.BBCode;
import net.iforums.utils.bbcode.BBCodeHandler;
import net.iforums.utils.preferences.ConfigKeys;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepositoryServiceImpl extends BaseServiveImpl implements RepositoryService, InitializingBean{
	/**
	 * banlist
	 */
	private static final String BANLIST_FQN = "banlist";
	private static final String BANLIST = "banlistCollection";
	
	@Resource
	private BanlistDao banlistDao;
	
	/**
	 * bbcode
	 */
	private static final String BBCODE_FQN = "bbcode";
	private static final String BBCOLLECTION = "bbCollection";
	
	/**
	 * 表情
	 */
	private static final String SMILIES_FQN = "smilies";
	private static final String ENTRIES = "entries";
	private static boolean contexted = false;
	
	@Resource
	private SmilieDao smilieDao;
	
	public RepositoryServiceImpl(){

	}
	public boolean shouldBan(Banlist b) {
		boolean status = false;
		
		for (Iterator<Banlist> iter = banlist().values().iterator(); iter.hasNext(); ) {
			Banlist current = (Banlist)iter.next();
			
			if (current.matches(b)) {
				status = true;
				break;
			}
		}
		
		return status;
	}

	public void add(Banlist b)
	{
		Map<Integer,Banlist> m = banlist();
		m.put(new Integer(b.getId()), b);
		
		cache.add(BANLIST_FQN, BANLIST, m);
	}
	
	public void remove(int banlistId)
	{
		Map<Integer,Banlist> m = banlist();
		
		Integer key = new Integer(banlistId);
		
		if (m.containsKey(key)) {
			m.remove(key);
		}
		
		cache.add(BANLIST_FQN, BANLIST, m);
	}
	
	private Map<Integer,Banlist> banlist()
	{
		Map<Integer,Banlist> m = (Map<Integer,Banlist>)cache.get(BANLIST_FQN, BANLIST);
		
		if (m == null) {
			m = new HashMap<Integer,Banlist>();
		}
		
		return m;
	}
	
	public void loadBanlist() 
	{
		List<Banlist> banlist = banlistDao.select(0, Integer.MAX_VALUE);
		
		for (Iterator<Banlist> iter = banlist.iterator(); iter.hasNext(); ) {
			add((Banlist)iter.next());
		}
	}
	
	@Autowired
	public void setBBCollection(BBCodeHandler defaultHandler)
	{
		cache.add(BBCODE_FQN, BBCOLLECTION, defaultHandler);
	}
	
	public BBCodeHandler getBBCollection()
	{
		return (BBCodeHandler)cache.get(BBCODE_FQN, BBCOLLECTION);
	}
	
	public BBCode findByName(String tagName)
	{
		return getBBCollection().findByName(tagName);
	}
	
	public void loadSmilies()
	{
		try {
			cache.add(SMILIES_FQN, ENTRIES, smilieDao.select(0, Integer.MAX_VALUE));
			contexted = false;
		}
		catch (Exception e) {
			throw new RuntimeException("Error while loading smilies: " + e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Smilie> getSmilies()
	{
		List<Smilie> list = (List<Smilie>)cache.get(SMILIES_FQN, ENTRIES);
		if(list==null){
			loadSmilies();
			list = (List<Smilie>)cache.get(SMILIES_FQN, ENTRIES);
		}
		if (!contexted) {
			String forumLink = config.getString(ConfigKeys.FORUM_LINK);
			
			for (Iterator<Smilie> iter = list.iterator(); iter.hasNext(); ) {
				Smilie s = (Smilie)iter.next();
				s.setUrl(s.getUrl().replaceAll("#CONTEXT#", forumLink).replaceAll("\\\\", ""));
			}
			
			cache.add(SMILIES_FQN, ENTRIES, list);
			contexted = true;
		}
		
		return list;
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		loadBanlist();
		loadSmilies();
	}
}
