package net.iforums.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import net.iforums.beans.Attachment;
import net.iforums.beans.AttachmentExtension;
import net.iforums.beans.AttachmentExtensionGroup;
import net.iforums.beans.QuotaLimit;
import net.iforums.dao.AttachmentDAO;
import net.iforums.dao.BaseORMDao;

@Repository
public class AttachmentDaoImpl extends BaseORMDao<Attachment> implements AttachmentDAO{

	
	public void addExtension(AttachmentExtension e) {
		
		
	}

	
	public void addExtensionGroup(AttachmentExtensionGroup g) {
		
		
	}

	
	public void addQuotaLimit(QuotaLimit limit) {
		
		
	}

	
	public void cleanGroupQuota() {
		
		
	}

	
	public void delete(int id, int postId) {
		
		
	}

	
	public Map extensionsForSecurity() {
		
		return null;
	}

	
	public boolean isPhysicalDownloadMode(int extensionGroupId) {
		
		return false;
	}

	
	public void removeExtensionGroups(String[] ids) {
		
		
	}

	
	public void removeExtensions(String[] ids) {
		
		
	}

	
	public void removeQuotaLimit(int id) {
		
		
	}

	
	public void removeQuotaLimit(String[] ids) {
		
		
	}

	
	public List selectAttachments(int postId) {
		
		return null;
	}

	
	public AttachmentExtension selectExtension(String extension) {
		
		return null;
	}

	
	public List selectExtensionGroups() {
		
		return null;
	}

	
	public List selectExtensions() {
		
		return null;
	}

	
	public Map selectGroupsQuotaLimits() {
		
		return null;
	}

	
	public List selectQuotaLimit() {
		
		return null;
	}

	
	public QuotaLimit selectQuotaLimitByGroup(int groupId) {
		
		return null;
	}

	
	public void setGroupQuota(int groupId, int quotaId) {
		
		
	}

	
	public void updateExtension(AttachmentExtension e) {
		
		
	}

	
	public void updateExtensionGroup(AttachmentExtensionGroup g) {
		
		
	}

	
	public void updateQuotaLimit(QuotaLimit limit) {
		
		
	}

}
