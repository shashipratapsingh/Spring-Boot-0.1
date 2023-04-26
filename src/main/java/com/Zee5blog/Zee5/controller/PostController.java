package com.Zee5blog.Zee5.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Zee5blog.Zee5.config.AppContant;
import com.Zee5blog.Zee5.payloads.ApiResponse;
import com.Zee5blog.Zee5.payloads.PostDto;
import com.Zee5blog.Zee5.payloads.PostResponse;
import com.Zee5blog.Zee5.services.FileService;
import com.Zee5blog.Zee5.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;
	@Value("${project.image}")
	private String path;

	// create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<>(createPost, HttpStatus.CREATED);
	}

	// get post by by id
	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> findById(@PathVariable Integer postId) {
		PostDto createPost = this.postService.getPostById(postId);
		return new ResponseEntity<>(createPost, HttpStatus.CREATED);
	}

	// get posts by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId) {
		List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}

	// get posts by User

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {

		List<PostDto> posts = this.postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}

	// find all list of posts

	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = AppContant.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppContant.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppContant.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppContant.SORT_DIR, required = false) String sortDir) {

		// List<PostDto> allPosts = this.postService.getAllPost(pageNumber,pageSize);
		PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);

	}

	// find post by id
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {

		PostDto post = this.postService.getPostById(postId);

		return new ResponseEntity<PostDto>(post, HttpStatus.OK);

	}

	// post delete

	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ApiResponse("post id success full deleted", true);

	}

	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);

	}

	/*
	 * //for the searching
	 * 
	 * @GetMapping("/posts/serching/{keyword}") public ResponseEntity<List<PostDto>>
	 * searchPosts(
	 * 
	 * @PathVariable("keyword") String keyword){ List<PostDto>
	 * results=this.postService.searchPosts(keyword); return new
	 * ResponseEntity<>(results,HttpStatus.OK);
	 * 
	 * }
	 */

	// Duplicate searching function

	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPosts1(@PathVariable("keywords") String keywords) {
		List<PostDto> results = this.postService.searchByTitleContaining(keywords);
		return new ResponseEntity<>(results, HttpStatus.OK);

	}

	/// file uploading

	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,@PathVariable Integer postId) throws IOException {
		PostDto postDto = this.postService.getPostById(postId);
		String fileName = this.fileService.uploadImage(path,image);
		postDto.setImageName(fileName);
		PostDto updatedPost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<>(updatedPost, HttpStatus.OK);

	}
	// Method of the post Images
	@GetMapping(value="/profile/{imageName}",produces=MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable String imageName,HttpServletResponse response) throws IOException{
		InputStream resource  =this.fileService.getResouce(path, imageName);
		((ServletResponse) resource).setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
	
	
	
	
	
}
