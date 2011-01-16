package net.iforums.web.controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.iforums.Command;
import net.iforums.ControllerUtils;
import net.iforums.ForumStartup;
import net.iforums.JForumExecutionContext;
import net.iforums.SessionFacade;
import net.iforums.beans.Banlist;
import net.iforums.context.JForumContext;
import net.iforums.context.RequestContext;
import net.iforums.context.ResponseContext;
import net.iforums.context.web.WebRequestContext;
import net.iforums.context.web.WebResponseContext;
import net.iforums.repository.BanlistRepository;
import net.iforums.repository.ModulesRepository;
import net.iforums.repository.SecurityRepository;
import net.iforums.utils.I18n;
import net.iforums.utils.preferences.ConfigKeys;
import net.iforums.utils.preferences.SystemGlobals;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import freemarker.template.SimpleHash;
import freemarker.template.Template;

@Controller
@RequestMapping(value="index.do")
public class ForumController extends AbstractController{

	@Override
	protected ModelAndView handleGetPostRequestInternal(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Writer out = null;
		JForumContext forumContext = null;
		RequestContext requestContext = null;
		ResponseContext responseContext = null;
		String encoding = SystemGlobals.getValue(ConfigKeys.ENCODING);

		try {
			// Initializes the execution context
			JForumExecutionContext ex = JForumExecutionContext.get();

			requestContext = new WebRequestContext(request);
            responseContext = new WebResponseContext(response);

			this.checkDatabaseStatus();

            forumContext = new JForumContext(requestContext.getContextPath(),
                SystemGlobals.getValue(ConfigKeys.SERVLET_EXTENSION),
                requestContext,
                responseContext
            );
            ex.setForumContext(forumContext);

            JForumExecutionContext.set(ex);

			// Setup stuff
			SimpleHash context = JForumExecutionContext.getTemplateContext();
			
			ControllerUtils utils = new ControllerUtils();
			utils.refreshSession();
			
			context.put("logged", SessionFacade.isLogged());
			
			// Process security data
			SecurityRepository.load(SessionFacade.getUserSession().getUserId());

			utils.prepareTemplateContext(context, forumContext);

			String module = requestContext.getModule();
			
			// Gets the module class name
			String moduleClass = module != null ? ModulesRepository.getModuleClass(module) : null;
			
			if (moduleClass == null) {
				// Module not found, send 404 not found response
				responseContext.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			else {
				boolean shouldBan = this.shouldBan(requestContext.getRemoteAddr());
				
				if (!shouldBan) {
					context.put("moduleName", module);
					context.put("action", requestContext.getAction());
				}
				else {
					moduleClass = ModulesRepository.getModuleClass("forums");
					context.put("moduleName", "forums");
					((WebRequestContext)requestContext).changeAction("banned");
				}
				
				if (shouldBan && SystemGlobals.getBoolValue(ConfigKeys.BANLIST_SEND_403FORBIDDEN)) {
					responseContext.sendError(HttpServletResponse.SC_FORBIDDEN);
				}
				else {
					context.put("language", I18n.getUserLanguage());
					context.put("session", SessionFacade.getUserSession());
					context.put("request", request);
					context.put("response", responseContext);
					
					out = this.processCommand(out, requestContext, responseContext, encoding, context, moduleClass);
				}
			}
		}
		catch (Exception e) {
			this.handleException(out, responseContext, encoding, e, requestContext);
		}
		finally {
			this.handleFinally(out, forumContext, responseContext);
		}	
		return null;
	}

	private Writer processCommand(Writer out, RequestContext request, ResponseContext response, 
			String encoding, SimpleHash context, String moduleClass) throws Exception
	{
		// Here we go, baby
		Command c = this.retrieveCommand(moduleClass);
		Template template = c.process(request, response, context);

		if (JForumExecutionContext.getRedirectTo() == null) {
			String contentType = JForumExecutionContext.getContentType();
			
			if (contentType == null) {
				contentType = "text/html; charset=" + encoding;
			}
			
			response.setContentType(contentType);
			
			// Binary content are expected to be fully 
			// handled in the action, including outputstream
			// manipulation
			if (!JForumExecutionContext.isCustomContent()) {
				out = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), encoding));
				template.process(JForumExecutionContext.getTemplateContext(), out);
				out.flush();
			}
		}
		
		return out;
	}

	private void checkDatabaseStatus()
	{
		ForumStartup.startDatabase();
	}

	private void handleFinally(Writer out, JForumContext forumContext, ResponseContext response) throws IOException
	{
		try {
			if (out != null) { out.close(); }
		}
		catch (Exception e) {
		    // catch close error 
		}
		
		String redirectTo = JForumExecutionContext.getRedirectTo();
		JForumExecutionContext.finish();
		
		if (redirectTo != null) {
			if (forumContext != null && forumContext.isEncodingDisabled()) {
				response.sendRedirect(redirectTo);
			} 
			else {
				response.sendRedirect(response.encodeRedirectURL(redirectTo));
			}
		}
	}

	private void handleException(Writer out, ResponseContext response, String encoding, 
		Exception e, RequestContext request) throws IOException
	{
		JForumExecutionContext.enableRollback();
		
		if (e.toString().indexOf("ClientAbortException") == -1) {
			response.setContentType("text/html; charset=" + encoding);
			if (out != null) {
//				new ExceptionWriter().handleExceptionData(e, out, request);
			}
			else {
//				new ExceptionWriter().handleExceptionData(e, new BufferedWriter(new OutputStreamWriter(response.getOutputStream(),encoding)), request);
			}
		}
	}
	
	private boolean shouldBan(String ip)
	{
		Banlist b = new Banlist();
		
		b.setUserId(SessionFacade.getUserSession().getUserId());
		b.setIp(ip);
		
		return BanlistRepository.shouldBan(b);
	}

	private Command retrieveCommand(String moduleClass) throws Exception
	{
		return (Command)Class.forName(moduleClass).newInstance();
	}
}
