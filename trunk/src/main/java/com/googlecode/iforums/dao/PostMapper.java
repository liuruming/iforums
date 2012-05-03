package com.googlecode.iforums.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.googlecode.iforums.bean.Post;

public interface PostMapper extends Mapper<Post>{

    List<Post> selectByTopicId(@Param("topicId") int topicId, @Param("offset") int offset, @Param("size") int size);

}
