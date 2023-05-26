package com.blog.services;

import java.util.List;

import com.blog.entities.Post;
import com.blog.payloads.PostDto;

public interface PostService {
	
	// create 
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	// update
	Post updatePost(PostDto postDto, Integer postId);
	
	// delete
	void deletePost(Integer postId);
	
	// get all posts
	List<Post> getAllPost();
	
	// get single post
	Post getPostById(Integer postId);
	
	// get all post by category
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	// get all posts by user
	List<PostDto> getPostsByUser(Integer userId);
	
	// Search post
	List<Post> searchPost(String keyword);
	
	
}
