package net.iforums;

import java.io.File;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;

import net.iforums.dao.CategoryDao;
import net.iforums.dao.ConfigDao;
import net.iforums.dao.DataAccessDriver;
import net.iforums.dao.ForumDao;
import net.iforums.repository.BBCodeRepository;
import net.iforums.repository.BanlistRepository;
import net.iforums.repository.ForumRepository;
import net.iforums.repository.RankingRepository;
import net.iforums.repository.SmiliesRepository;
import net.iforums.repository.Tpl;
import net.iforums.utils.I18n;
import net.iforums.utils.bbcode.BBCodeHandler;
import net.iforums.utils.preferences.ConfigKeys;
import net.iforums.utils.preferences.SystemGlobals;

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
			SystemGlobals.loadQueries(SystemGlobals.getValue(ConfigKeys.SQL_QUERIES_GENERIC));
			SystemGlobals.loadQueries(SystemGlobals.getValue(ConfigKeys.SQL_QUERIES_DRIVER));
			
			String filename = SystemGlobals.getValue(ConfigKeys.QUARTZ_CONFIG);
			SystemGlobals.loadAdditionalDefaults(filename);

			ConfigLoader.createLoginAuthenticator();
			ConfigLoader.loadDaoImplementation();
			ConfigLoader.listenForChanges();
			ConfigLoader.startSearchIndexer();
			ConfigLoader.startSummaryJob();
		}
		catch (Exception e) {
			throw new RuntimeException("Error while starting JForum", e);
		}
		try {
			ForumDao fm = DataAccessDriver.getInstance().newForumDao();
			CategoryDao cm = DataAccessDriver.getInstance().newCategoryDao();
			ConfigDao configModel = DataAccessDriver.getInstance().newConfigDao();

			ForumRepository.start(fm, cm, configModel);
			
			RankingRepository.loadRanks();
			SmiliesRepository.loadSmilies();
			BanlistRepository.loadBanlist();
		}
		catch (Exception e) {
			logger.error("Unable to bootstrap JForum repository.", e);
			throw new RuntimeException("Error while trying to start ForumRepository: " + e, e);
		}	
		
		try {
			String appPath = "";
			//DOMConfigurator.configure(appPath + "/WEB-INF/log4j.xml");


			ConfigLoader.startSystemglobals(appPath);
			ConfigLoader.startCacheEngine();

			// Configure the template engine
			Configuration templateCfg = new Configuration();
			templateCfg.setTemplateUpdateDelay(2);
			templateCfg.setSetting("number_format", "#");
			templateCfg.setSharedVariable("startupTime", new Long(new Date().getTime()));

			// Create the default template loader
			String defaultPath = SystemGlobals.getApplicationPath() + "/templates";
			FileTemplateLoader defaultLoader = new FileTemplateLoader(new File(defaultPath));

			String extraTemplatePath = SystemGlobals.getValue(ConfigKeys.FREEMARKER_EXTRA_TEMPLATE_PATH);
			
			if (StringUtils.isNotBlank(extraTemplatePath)) {
				// An extra template path is configured, we need a MultiTemplateLoader
				FileTemplateLoader extraLoader = new FileTemplateLoader(new File(defaultPath+"/"+extraTemplatePath));
				TemplateLoader[] loaders = new TemplateLoader[] { extraLoader, defaultLoader };
				MultiTemplateLoader multiLoader = new MultiTemplateLoader(loaders);
				templateCfg.setTemplateLoader(multiLoader);
			} 
			else {
				// An extra template path is not configured, we only need the default loader
				templateCfg.setTemplateLoader(defaultLoader);
			}

			loadConfigStuff();

		}
		catch (Exception e) {
			throw new RuntimeException("Error while starting JForum", e);
		}
	}
	protected static void loadConfigStuff()
	{
		I18n.load();
		Tpl.load(SystemGlobals.getValue(ConfigKeys.TEMPLATES_MAPPING));

		// BB Code
		BBCodeRepository.setBBCollection(new BBCodeHandler().parse());
	}
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		ConfigLoader.stopCacheEngine();
	}

}
