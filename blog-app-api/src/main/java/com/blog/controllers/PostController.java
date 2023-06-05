package com.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.blog.payloads.ApiResponse;
import com.blog.payloads.PostDto;
import com.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	// create post
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId){
		
		// Call the createPost() of postService and save the returned object
		PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);
		
		// Return the createdPost as HTTP response with status
		return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
	}
	
	// Get By User
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
		
		// Call the getPostsByUser() in postService and return list of posts
		List<PostDto> posts = this.postService.getPostsByUser(userId);
		
		// Return the list of post as http response with status
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}
	
	// Get By Category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
		
		// Call the getPostsByCategory() in postService and return list of posts
		List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
		
		// Return the list of post as http response with status
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}
	
	// Get all posts
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize){
		
		// Call the get all post method of service
		List<PostDto> allPost = this.postService.getAllPost(pageNumber, pageSize);
		
		// Return the list of all posts with http status ok
		return new ResponseEntity<List<PostDto>>(allPost, HttpStatus.OK);
	}
	
	
	// get post details by id
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		
		// Call the get all post by id method of service
		PostDto post = this.postService.getPostById(postId);
		
		// Return the post with particular id with http status ok
		return new ResponseEntity<PostDto>(post, HttpStatus.OK);
	}
	
	// delete post
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId) {
		
		// Call the deletePost method of service
		this.postService.deletePost(postId);
		
		// Return the message of deletion
		return new ApiResponse("Post is successfully deleted!", true);
		
	}
	
	// update post
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId) {
		
		// Call the updatePost method of service
		PostDto updatedPost = this.postService.updatePost(postDto, postId);
		
		// Return the updated post
		return new ResponseEntity<PostDto> (updatedPost, HttpStatus.OK);
		
	}
}
