/*
 * Created on 04/09/2006 21:59:39
 */
package net.iforums.api.rest;

import net.iforums.dao.ApiDao;
import net.jforum.dao.DataAccessDriver;

/**
 * @author Rafael Steil
 * @version $Id: RESTAuthentication.java,v 1.2 2006/10/10 00:49:04 rafaelsteil Exp $
 */
public class RESTAuthentication
{
	public boolean validateApiKey(String apiKey)
	{
		ApiDao dao = DataAccessDriver.getInstance().newApiDao();
		return dao.isValid(apiKey);
	}
}
