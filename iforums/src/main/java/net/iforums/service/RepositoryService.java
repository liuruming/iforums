package net.iforums.service;

import java.util.List;

import net.iforums.beans.Smilie;
import net.iforums.utils.bbcode.BBCodeHandler;

public interface RepositoryService {

	public List<Smilie> getSmilies();
	
	public BBCodeHandler getBBCollection();
}
