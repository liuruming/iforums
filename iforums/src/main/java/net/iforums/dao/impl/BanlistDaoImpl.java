package net.iforums.dao.impl;

import org.springframework.stereotype.Repository;

import net.iforums.beans.Banlist;
import net.iforums.dao.BanlistDAO;
import net.iforums.dao.BaseORMDao;

@Repository
public class BanlistDaoImpl extends BaseORMDao<Banlist> implements BanlistDAO{

}
