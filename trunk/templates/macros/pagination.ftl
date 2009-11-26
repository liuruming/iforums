<#macro littlePostPagination topicId postsPerPage totalReplies>
	[ <img class="icon_latest_reply" src="${contextPath}/images/transp.gif" alt="" /> ${I18n.getMessage("goToPage")}: 

	<#assign totalPostPages = ((totalReplies + 1) / postsPerPage)?int/>

	<#if (((totalReplies + 1) % postsPerPage) > 0)>
		<#assign totalPostPages = (totalPostPages + 1)/>
	</#if>

	<#if (totalPostPages > 6)>
		<#assign minTotal = 3/>
	<#else>
		<#assign minTotal = totalPostPages/>
	</#if>

	<#-- ----------- -->
	<#-- First pages -->
	<#-- ----------- -->
	<#assign link = ""/>

	<#list 1 .. minTotal as page>
		<#assign start = postsPerPage * (page - 1)/>

		<#assign link>${link}<a href="${contextPath}/posts/list<#if (start>0)>/${start}</#if>/${topicId}${extension}">${page}</a></#assign>
		<#if (page < minTotal)><#assign link>${link}, </#assign></#if>		
	</#list>

	${link}

	<#assign link = ""/>
	<#if (totalPostPages > 6)>
		&nbsp;...&nbsp;
		<#list totalPostPages - 2 .. totalPostPages as page>
			<#assign start = postsPerPage * (page - 1)/>

			<#assign link>${link}<a href="${contextPath}/posts/list<#if (start>0)>/${start}</#if>/${topicId}${extension}">${page}</a></#assign>
			<#if (page_index + 1 < 3)><#assign link>${link}, </#assign></#if>
		</#list>

		${link}
	</#if>

	]
</#macro>

<#-- ------------------------------------------------------------------------------- -->
<#-- Pagination macro base code inspired from PHPBB's generate_pagination() function -->
<#-- ------------------------------------------------------------------------------- -->
<#macro doPagination action id=-1>
	<div class="pages">
		<#if (totalRecords > recordsPerPage)>
				<#assign link = ""/>
		
				<#-- ------------- -->
				<#-- Previous page -->
				<#-- ------------- -->
				<#if (thisPage > 1)>
					<#assign start = (thisPage - 2) * recordsPerPage/>
					<a href="${contextPath}/${moduleName}/${action}<#if (start > 0)>/${start}</#if><#if (id > -1)>/${id}</#if>${extension}">&#9668;</a>
				<#else>
					<a>&#9668;</a>
				</#if>
		
				<#if (totalPages > 10)>
						<#-- ------------------------------ -->
						<#-- Always write the first 3 links -->
						<#-- ------------------------------ -->
						<#list 1 .. 3 as page>
							<@pageLink page, id/>
						</#list>
		
						<#-- ------------------ -->
						<#-- Intermediate links -->
						<#-- ------------------ -->
						<#if (thisPage > 1 && thisPage < totalPages)>
							<#if (thisPage > 5)><span class="gensmall">...</span></#if>
			
							<#if (thisPage > 4)>
								<#assign min = thisPage - 1/>
							<#else>
								<#assign min = 4/>
							</#if>
			
							<#if (thisPage < totalPages - 4)>
								<#assign max = thisPage + 2/>
							<#else>
								<#assign max = totalPages - 2/>
							</#if>
			
							<#if (max >= min + 1)>
								<#list min .. max - 1 as page>
									<@pageLink page, id/>
								</#list>
							</#if>
			
							<#if (thisPage < totalPages - 4)><span class="gensmall">...</span></#if>
						<#else>
							<span class="gensmall">...</span>
						</#if>
	
						<#-- ---------------------- -->
						<#-- Write the last 3 links -->
						<#-- ---------------------- -->
						<#list totalPages - 2 .. totalPages as page>
							<@pageLink page, id/>
						</#list>
				<#else>
					<#list 1 .. totalPages as page>
						<@pageLink page, id/>
					</#list>
				</#if>
	
				<#-- ------------- -->
				<#-- Next page -->
				<#-- ------------- -->
				<#if (thisPage < totalPages)>
					<#assign start = thisPage * recordsPerPage/>
					<a href="${contextPath}/${moduleName}/${action}<#if (start > 0)>/${start}</#if><#if (id > -1)>/${id}</#if>${extension}">&#9658;</a>
				<#else>
					<a>&#9658;</a>
				</#if>

			<!--
				<a href="#goto" onClick="return overlay(this, 'goToBox', 'rightbottom');">${I18n.getMessage("ForumIndex.goToGo")}</a>
				<div id="goToBox">
					<div class="title">${I18n.getMessage("goToPage")}...</div>
					<div class="form">
						<input type="text" style="width: 50px;" id="pageToGo">
						<input type="button" value=" ${I18n.getMessage("ForumIndex.goToGo")} " onClick="goToAnotherPage(${totalPages}, ${recordsPerPage}, '${contextPath}', '${moduleName}', '${action}', ${id}, '${extension}');">
						<input type="button" value="${I18n.getMessage("cancel")}" onClick="document.getElementById('goToBox').style.display = 'none';">
					</div>
				</div>
			-->
		</#if>
		</div>
		<#if (totalRecords > recordsPerPage)>
			<span class="postbtn" id="newtopic" onmouseover="$('newtopic').id = 'newtopictmp';this.id = 'newtopic';showMenu(this.id);">
				<a href="${JForumContext.encodeURL("/jforum${extension}?module=posts&amp;action=insert&amp;forum_id=${forum.id}", "")}" rel="nofollow" class="icon_new_topic">
					<img src="${contextPath}/templates/${templateName}/images/newtopic.gif" alt="" />
				</a>
			</span>
		</#if>
		<#if (totalRecords > recordsPerPage)>
			<#if logged>
				<span class="postbtn"><a href="${JForumContext.encodeURL("/forums/readAll/${forum.id}")}">${I18n.getMessage("ForumIndex.setAllTopicsAsRead")}</a></span>
			</#if>
			<#if moderator>
				<span class="postbtn" valign="center">
					<#if openModeration?default(false)>
						<#assign link = ""/>
						<#if (start > 0)>
							<#assign link = JForumContext.encodeURL("/forums/show/" + start + "/" + forum.id)/>
						<#else>
							<#assign link = JForumContext.encodeURL("/forums/show/" + forum.id)/>
						</#if>
						<a href="${link}">${I18n.getMessage("Moderation.CloseModeration")}</a>
					<#else>
						<a href="${contextPath}/forums/moderation/<#if (start > 0)>${start}/</#if>${forum.id}${extension}">${I18n.getMessage("Moderation.OpenModeration")}</a>
					</#if>
				</span>
			</#if>
			<span class="postbtn" valign="center">
			<form accept-charset="${encoding}" action="${JForumContext.encodeURL("/jforum")}" method="get" id="formSearch" name="formSearch">
				<input type="hidden" name="module" value="search"/>
				<input type="hidden" name="action" value="search"/>
				<input type="hidden" name="forum" value="${forum.id}">
				<input type="hidden" name="match_type" value="all">
				<label>${I18n.getMessage("ForumIndex.searchThisForum")}:</label>
				<input type="text" onblur="if (this.value == '') this.value = '${I18n.getMessage("ForumIndex.searchThisForum")}...';" onclick="if (this.value == '${I18n.getMessage("ForumIndex.searchThisForum")}...') this.value = '';" value="${I18n.getMessage("ForumIndex.searchThisForum")}..." size="20" name="search_keywords" class="inputSearchForum"/>
				<input type="submit" value="${I18n.getMessage("ForumBase.search")}" class="liteoption">
			</form>
			</span>
			<!--span class="postbtn" id="newtopic" onmouseover="$('newtopic').id = 'newtopictmp';this.id = 'newtopic';showMenu(this.id);">
				<a href="${JForumContext.encodeURL("/jforum${extension}?module=posts&amp;action=insert&amp;forum_id=${forum.id}", "")}" rel="nofollow" class="icon_new_topic">
					<img src="${contextPath}/templates/${templateName}/images/newtopic.gif" alt="" />
				</a>
			</span-->
		</#if>
		<ul class="popmenu_popup newtopicmenu" id="newtopic_menu" style="position: absolute; z-index: 50; clip: rect(auto, auto, auto, auto); left: 1020px; top: 198px; display: none;">
			<li><a href="./post.jsp?fid=5">new</a></li>		
			<li class="vote"><a href="./post.jsp?fid=5&amp;act=vote">vote</a></li>
			<li class="reward"><a href="./post.jsp?fid=5&amp;act=reward">xuanshang</a></li>
		</ul>
</#macro>

<#macro pageLink page id>
	<#assign start = recordsPerPage * (page - 1)/>
	<#if page != thisPage>
		<#assign link><a href="${contextPath}/${moduleName}/${action}<#if (start > 0)>/${start}</#if><#if (id > -1)>/${id}</#if>${extension}">${page}</a></#assign>
	<#else>
		<#assign link><strong>${page}</strong></#assign>
	</#if>

	${link}
</#macro>