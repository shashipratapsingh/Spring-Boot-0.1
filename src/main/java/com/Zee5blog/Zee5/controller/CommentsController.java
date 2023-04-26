package com.Zee5blog.Zee5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Zee5blog.Zee5.payloads.ApiResponse;
import com.Zee5blog.Zee5.payloads.CommentDto;
import com.Zee5blog.Zee5.services.CommentService;

@RestController
@RequestMapping("/api")
public class CommentsController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/post/{postId}/commnets")
	public ResponseEntity<CommentDto> createComments(@RequestBody CommentDto CommentDto,@PathVariable Integer postId )
	{
		CommentDto createComments = this.commentService.createComments(CommentDto, postId);
		
		return new ResponseEntity<CommentDto>(createComments,HttpStatus.CREATED);
			
	}
	
	//delete
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> createComments(@PathVariable Integer commentId )
	{
		this.commentService.deleteComments(commentId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("deleted succesfully Id",true),HttpStatus.OK);
			
	}
}
