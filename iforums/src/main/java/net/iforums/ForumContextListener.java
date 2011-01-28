package net.iforums;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.jboss.logging.Logger;

import net.iforums.dao.CategoryDao;
import net.iforums.dao.ConfigDao;
import net.iforums.dao.DataAccessDriver;
import net.iforums.dao.ForumDao;
import net.iforums.repository.ForumRepository;

/**
 * 论坛启动的时候自动加载
 * 
 * @author Rafael Steil
 * @version $Id: $
 */
public class ForumContextListener implements ServletContextListener {
	
	static Logger logger = Logger.getLogger(ForumContextListener.class);
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			ForumDao fm = DataAccessDriver.getInstance().newForumDao();
			CategoryDao cm = DataAccessDriver.getInstance().newCategoryDao();
			ConfigDao configModel = DataAccessDriver.getInstance().newConfigDao();

			ForumRepository.start(fm, cm, configModel);
		}
		catch (Exception e) {
			logger.error("Unable to bootstrap JForum repository.", e);
			throw new RuntimeException("Error while trying to start ForumRepository: " + e, e);
		}		
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

}
