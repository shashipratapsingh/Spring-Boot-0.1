package com.Zee5blog.Zee5.services;

import com.Zee5blog.Zee5.payloads.CommentDto;

public interface CommentService {

	CommentDto createComments(CommentDto commentDto, Integer postId);

	void deleteComments(Integer commentId);

}
