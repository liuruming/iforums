/*
 * Copyright (c) JForum Team
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, 
 * with or without modification, are permitted provided 
 * that the following conditions are met:
 * 
 * 1) Redistributions of source code must retain the above 
 * copyright notice, this list of conditions and the 
 * following  disclaimer.
 * 2)  Redistributions in binary form must reproduce the 
 * above copyright notice, this list of conditions and 
 * the following disclaimer in the documentation and/or 
 * other materials provided with the distribution.
 * 3) Neither the name of "Rafael Steil" nor 
 * the names of its contributors may be used to endorse 
 * or promote products derived from this software without 
 * specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT 
 * HOLDERS AND CONTRIBUTORS "AS IS" AND ANY 
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, 
 * BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR 
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL 
 * THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE 
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES 
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, 
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER 
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER 
 * IN CONTRACT, STRICT LIABILITY, OR TORT 
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN 
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF 
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE
 * 
 * This file creation date: Mar 3, 2003 / 11:07:02 AM
 * The JForum Project
 * http://www.jforum.net
 */
package net.iforums.view.admin;

import java.util.ArrayList;
import java.util.List;

import net.iforums.beans.Group;
import net.iforums.dao.DataAccessDriver;
import net.iforums.dao.GroupDao;
import net.iforums.dao.GroupSecurityDao;
import net.iforums.repository.ForumRepository;
import net.iforums.repository.RolesRepository;
import net.iforums.repository.SecurityRepository;
import net.iforums.security.PermissionControl;
import net.iforums.security.XMLPermissionControl;
import net.iforums.utils.I18n;
import net.iforums.utils.TreeGroup;
import net.iforums.utils.preferences.ConfigKeys;
import net.iforums.utils.preferences.SystemGlobals;
import net.iforums.utils.preferences.TemplateKeys;

/**
 * ViewHelper class for group administration.
 * 
 * @author Rafael Steil
 * @version $Id: GroupAction.java,v 1.24 2007/09/21 03:47:40 rafaelsteil Exp $
 */
public class GroupAction extends AdminCommand 
{
	// Listing
	public void list()
	{
		this.context.put("groups", new TreeGroup().getNodes());
		this.setTemplateName(TemplateKeys.GROUP_LIST);
	}
	
	// Insert
	public void insert()
	{
		this.context.put("groups", new TreeGroup().getNodes());
		this.context.put("action", "insertSave");
		this.context.put("selectedList", new ArrayList());
		this.setTemplateName(TemplateKeys.GROUP_INSERT);
	}
	
	// Save information for an existing group
	public void editSave()
	{
		int groupId = this.request.getIntParameter("group_id");
			
		Group g = new Group();
		g.setDescription(this.request.getParameter("group_description"));
		g.setId(groupId);
		
		int parentId = this.request.getIntParameter("parent_id");
		
		if (parentId == g.getId()) {
			parentId = 0;
		}
		
		g.setParentId(parentId);
		g.setName(this.request.getParameter("group_name"));

		DataAccessDriver.getInstance().newGroupDao().update(g);
			
		this.list();
	}
	
	// Edit a group
	public void edit()
	{
		int groupId = this.request.getIntParameter("group_id");
		GroupDao gm = DataAccessDriver.getInstance().newGroupDao();
		
		this.setTemplateName(TemplateKeys.GROUP_EDIT);
					
		this.context.put("group", gm.getObjectById(groupId));
		this.context.put("groups", new TreeGroup().getNodes());
		this.context.put("selectedList", new ArrayList());
		this.context.put("action", "editSave");	
	}
	
	// Deletes a group
	public void delete() 
	{		
		String groupId[] = this.request.getParameterValues("group_id");
		
		if (groupId == null) {
			this.list();
			
			return;
		}
		
		List errors = new ArrayList();
		GroupDao gm = DataAccessDriver.getInstance().newGroupDao();
			
		for (int i = 0; i < groupId.length; i++) {
			int id = Integer.parseInt(groupId[i]);
			
			if (gm.canDelete(id)) {
				gm.deleteObjectById(id);
			}
			else {
				errors.add(I18n.getMessage(I18n.CANNOT_DELETE_GROUP, new Object[] { new Integer(id) }));
			}
		}
		
		if (errors.size() > 0) {
			this.context.put("errorMessage", errors);
		}
			
		this.list();
	}
	
	// Saves a new group
	public void insertSave()
	{
		GroupDao gm = DataAccessDriver.getInstance().newGroupDao();
		
		Group g = new Group();
		g.setDescription(this.request.getParameter("group_description"));
		g.setParentId(this.request.getIntParameter("parent_id"));
		g.setName(this.request.getParameter("group_name"));
			
		gm.insert(g);			
			
		this.list();
	}
	
	// Permissions
	public void permissions()
	{
		int id = this.request.getIntParameter("group_id");
		
		PermissionControl pc = new PermissionControl();
		pc.setRoles(DataAccessDriver.getInstance().newGroupSecurityDao().loadRoles(id));
		
		String xmlconfig = SystemGlobals.getValue(ConfigKeys.CONFIG_DIR) + "/permissions.xml"; 
		List sections = new XMLPermissionControl(pc).loadConfigurations(xmlconfig); 
		
		GroupDao gm = DataAccessDriver.getInstance().newGroupDao();

		this.context.put("sections", sections);
		this.context.put("group", gm.getObjectById(id));
		this.setTemplateName(TemplateKeys.GROUP_PERMISSIONS);
	}
	
	public void permissionsSave()
	{
		int id = this.request.getIntParameter("id");
		
		GroupSecurityDao gmodel = DataAccessDriver.getInstance().newGroupSecurityDao();
		
		PermissionControl pc = new PermissionControl();
		pc.setSecurityModel(gmodel);
		
		new PermissionProcessHelper(pc, id).processData();

		SecurityRepository.clean();
		RolesRepository.clear();
		ForumRepository.clearModeratorList();
		
		this.list();
	}
}
