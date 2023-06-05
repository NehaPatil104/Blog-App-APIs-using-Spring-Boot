package com.blog.services;

import java.util.List;

import com.blog.payloads.PostDto;

public interface PostService {
	
	// create 
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	// update
	PostDto updatePost(PostDto postDto, Integer postId);
	
	// delete
	void deletePost(Integer postId);
	
	// get all posts
	List<PostDto> getAllPost(Integer pageNumber, Integer pageSize);
	
	// get single post
	PostDto getPostById(Integer postId);
	
	// get all post by category
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	// get all posts by user
	List<PostDto> getPostsByUser(Integer userId);
	
	// Search post
	List<PostDto> searchPost(String keyword);
	
}
