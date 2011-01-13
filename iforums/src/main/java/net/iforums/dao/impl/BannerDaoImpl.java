package net.iforums.dao.impl;

import java.util.List;

import net.iforums.beans.*;
import net.iforums.dao.*;

public class BannerDaoImpl extends BaseORMDao<Banner> implements BannerDAO{

	public boolean canDelete(int bannerId) {
		// TODO Auto-generated method stub
		return false;
	}

	public List selectActiveBannerByPlacement(int placement) {
		// TODO Auto-generated method stub
		return null;
	}
}
