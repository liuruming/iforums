package net.iforums.service;

import java.util.List;

import net.iforums.beans.Post;

public interface PostService {

	public Post getPostById(long id);
	
	public List<Post> selectPostByTopicId(int topicId,int page,int size);
}
