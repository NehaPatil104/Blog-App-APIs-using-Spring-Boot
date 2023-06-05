package com.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.PostDto;
import com.blog.repositories.CategoryRepo;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepo;
import com.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		// Get the user from db
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User id", userId));
		
		// Get the category from db
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category id", categoryId));
		
		// Convert postDto to Post
		Post post = this.modelMapper.map(postDto, Post.class);
		
		// Add the remaining fields using setter method in post object
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		// Save the post object to db
		Post newPost = this.postRepo.save(post);
		
		// Return the PostDto object
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		
		// Get the post from db
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));
		
		// Update the post object with the data in postDto
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		// Save the post to db
		Post updatedPost = this.postRepo.save(post);
		
		// Convert the post to postDto
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		
		// Get the post to delete from db i.e confirm if it is present in db
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));
		
		// Delete post from db
		this.postRepo.delete(post);
	}

	@Override
	public List<PostDto> getAllPost(Integer pageNumber, Integer pageSize) {
		
		// Pagination
		
		Pageable p = PageRequest.of(pageNumber, pageSize);
		
		// Get all posts from db
		Page<Post> pagePost = this.postRepo.findAll(p);
		
		List<Post> allPosts = pagePost.getContent();
		
		// Convert Post to PostDto
		List<PostDto> postDto = allPosts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		// Return PostDto
		return postDto;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		
		// Get the particular post from db
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));
		
		// Convert the post to postDto
		PostDto postDto = this.modelMapper.map(post, PostDto.class);
		
		// Return postDto
		return postDto;
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		// First find the category id from db and check if it is present
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Categroy", "Category Id", categoryId));
		
		// Get all the posts of the given category from db by findByCategory inbuilt Repo method
		List<Post> posts = this.postRepo.findByCategory(category);
		
		// Use map to convert all the post to postDtos 
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		// Return list of posts
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {

		// Get the user from the db
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));
		
		// Get all posts by id
		List<Post> posts = this.postRepo.findByUser(user);
		
		// Convert to PostDto
		List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
