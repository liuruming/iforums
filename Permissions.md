# jforum的用户权限 #
(来源：http://opensource.csdn.net/bbs/thread/3587)<br />
JForum默认匿名用户是可以发帖的，要限制这个操作首先建立一个匿名用户组，然后给这个用户组指派权限不允许所有的论坛发帖，然后将JForum自动创建的匿名用户放到这个组中即可。<br />

最近的项目用到了jforum,于是就研究了下jorum的权限管理机制。<br />

刚刚看到jforum的ER图还真有点奇怪，从ER图上看，jforum是使用group和role来管理权限，但是只有gorup和user有关联关系， 竟然和forum没有关联关系，后来才明白是在jforum\_role\_values这张表里，把forumId作为role\_value字段保 存了，估计设计者也是为了简少表之间的外键关联关系。<br />

jforum里对权限分得很细，大体有一下几种：<br />

Jforum 权限对应表<br />

perm\_administration$single 是否为越级管理员<br />
perm\_category 限制的分类<br />
perm\_forum 限制的版块<br />
perm\_anonymous\_post 禁止匿名用户发帖<br />
perm\_reply\_only 禁止发主体帖,允许回复帖子<br />
perm\_create\_sticky\_announcement\_topics$single 允许将主题设置为公告或置顶主题<br />
perm\_create\_poll$single 可发起投票帖<br />
perm\_vote$single 允许投票<br />
perm\_read\_only\_forums 只读版块.选择允许所有表示禁止标签，也就是可回复。<br />
perm\_reply\_without\_moderation 选择 '允许全部'将不限制对所有版块的回复,选中指定的版块代表不允许在选择的版本中回复信息.<br />
perm\_html\_disabled 选中的版块代表不允许使用HTML标签 .选择‘允许所有’代表允许所有版面使用HTML标签<br />
perm\_karma\_enabled$single 使用文章评分<br />
perm\_bookmarks\_enabled$single 允许使用书签<br />
perm\_attachments\_enabled 是否允许使用附件,选择'允许全部'代表可以使用,选择指定版块代表不允许在选中的版块中使用附件<br />
perm\_attachments\_download 允许下载附件<br />
perm\_moderation\_log$single 可以查阅管理活动日志<br />
perm\_full\_moderation\_log$single 可以查阅完整的管理活动日志<br />
perm\_moderation$single 是否允许设置为版主<br />
perm\_moderation\_approve\_messages$single 是否在允许的版块里审核/封锁贴子<br />
perm\_moderation\_forums 不能修改的论坛<br />
perm\_moderation\_post\_remove$single 删除贴子<br />
perm\_moderation\_post\_edit$single 修改贴子<br />
perm\_moderation\_topic\_move$single 移动贴子<br />
perm\_moderation\_topic\_lockUnlock$single 锁定和解锁主题<br />

以上都定义在SecurityConstants中，全部权限其实可以大体分为三部分，管理员，游客，会员。<br />
通过不同的组合可以定义不同的group，不过最终的权限(role)，只能分配给gorup,然后把用户加入不同的group来获得role.<br />
(在jforum的管理界面看到group有层次关系，可以有parent，也激动 了下，难道group的role也可以继承？后来实验了下发现并没有
这样的机制~。~ 不过一个用户可以属于不同的gorup，role是全部gorup的合集，通过这个还是可以做些文章的 <sup>.</sup> )<br />

jforum并没有为二次开发提供比较好的文档和API只有读源代码了，jforum并没有使用流行的MVC框架，似乎是自己实现了一个~后台使用的
是JDBC，所以想要进行二次开发还是得花点力气的。但是jforum的代码比较好 读，处处都有封装，虽然注释不多，看懂代码还是比较简单的。<br />

下面终结些权限控制可以用的方法，jforum并没有提供API，很多好用的方法在 action里面都是private的，所以最好自己可以写个类，这样使用起来比较方便：<br />


添加一个权限：<br />
GroupAction >><br />
添加权限控制为group控制 permissionsSave<br />
GroupSecurityDAO gmodel = DataAccessDriver.getInstance().newGroupSecurityDAO(); （提示使用gorup管理，看来以后还有扩展）
```

PermissionControl pc = new PermissionControl();
pc.setSecurityModel(gmodel);

PermissionProcessHelper

Role role = new Role();
role.setName(paramName);
this.pc.addRole(this.groupId, role, roleValues);  》》》 pc = PermissionControl

this.addRole(pc, SecurityConstants.PERM_FORUM, f.getId(), groups);
```
> >> Forumaction (推荐用这个比较方便)<br />

roleValues 是forum id， 可以访问的话就加上。(注意这里和管理界面上的概念是相反的，界面上选中的是禁止，而在DB中存在这个字段就表允许)<br />

添加一个组：<br />
GroupAction >> insertSave<br />

把一个人加入某一个群组：<br />
UserAction 》》 groupsSave
GenericUserDAO 》》 addToGroup<br />

添加一个Forum:<br />
ForumAction >> insertSave
```


this.logNewRegisteredUserIn(userId, u ); // add the user info to session

private void addManagerRoleForGroup(Forum f, PermissionControl pc,
int[] gorupIds) {
this.addRole(pc, SecurityConstants.PERM_MODERATION_LOG, f.getId(), gorupIds);
this.addRole(pc, SecurityConstants.PERM_FULL_MODERATION_LOG, f.getId(), gorupIds);
this.addRole(pc, SecurityConstants.PERM_MODERATION, f.getId(), gorupIds);
this.addRole(pc, SecurityConstants.PERM_MODERATION_APPROVE_MESSAGES, f.getId(), gorupIds);
this.addRole(pc, SecurityConstants.PERM_MODERATION_POST_REMOVE, f.getId(), gorupIds);
this.addRole(pc, SecurityConstants.PERM_MODERATION_POST_EDIT, f.getId(), gorupIds);
this.addRole(pc, SecurityConstants.PERM_MODERATION_TOPIC_MOVE, f.getId(), gorupIds);
this.addRole(pc, SecurityConstants.PERM_MODERATION_TOPIC_LOCK_UNLOCK, f.getId(), gorupIds);
//------manager role
}

private void addNormalRoleForGroup(Forum f, PermissionControl pc,
int[] gorupIds) {
this.addRole(pc, SecurityConstants.PERM_ANONYMOUS_POST, f.getId(), gorupIds);
this.addRole(pc, SecurityConstants.PERM_REPLY_ONLY, f.getId(), gorupIds);
this.addRole(pc, SecurityConstants.PERM_CREATE_STICKY_ANNOUNCEMENT_TOPICS, f.getId(), gorupIds);
this.addRole(pc, SecurityConstants.PERM_CREATE_POLL, f.getId(), gorupIds);
this.addRole(pc, SecurityConstants.PERM_VOTE, f.getId(), gorupIds);
this.addRole(pc, SecurityConstants.PERM_BOOKMARKS_ENABLED, f.getId(), gorupIds);
this.addRole(pc, SecurityConstants.PERM_ATTACHMENTS_ENABLED, f.getId(), gorupIds);
this.addRole(pc, SecurityConstants.PERM_ATTACHMENTS_DOWNLOAD, f.getId(), gorupIds);
this.addRole(pc, SecurityConstants.PERM_ATTACHMENTS_DOWNLOAD, f.getId(), gorupIds);
}
```
在jforum的论坛看到作者正在为jforum3努力，期待 jforum3啊<br />

现在最新的jforum2.1.8功能相比一些PHP的论坛还是比较简单的。<br />