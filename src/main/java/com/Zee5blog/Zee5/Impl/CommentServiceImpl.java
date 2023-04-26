package com.Zee5blog.Zee5.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Zee5blog.Zee5.exception.ResourceNotFountException;
import com.Zee5blog.Zee5.models.Comment;
import com.Zee5blog.Zee5.models.Post;
import com.Zee5blog.Zee5.payloads.CommentDto;
import com.Zee5blog.Zee5.repostory.CommentsRepository;
import com.Zee5blog.Zee5.repostory.PostRepository;
import com.Zee5blog.Zee5.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentsRepository commentsRepository;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComments(CommentDto commentDto, Integer postId) {
		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFountException("Post", "PostId", postId));
		Comment comments = this.modelMapper.map(commentDto, Comment.class);

		comments.setPost(post);
		Comment savedComment1 = this.commentsRepository.save(comments);

		return this.modelMapper.map(savedComment1, CommentDto.class);
	}

	@Override
	public void deleteComments(Integer commentId) {
		Comment com=this.commentsRepository.findById(commentId).orElseThrow(()->new ResourceNotFountException("comments", "comments ID",commentId));
		this.commentsRepository.delete(com);
	}

}
