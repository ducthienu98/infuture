package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.ResourceNotFoundException;
import com.example.entity.Post;
import com.example.model.PostDTO;
import com.example.model.UserDTO;
import com.example.repository.PostRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	@Autowired
	private UserService userService;

	public List<PostDTO> getListPostByUser(UserDTO user) {
		List<Post> posts = postRepository.getListPostByUser(userService.toEntity(user));
		return posts.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public PostDTO createPost(PostDTO postDTO) {
		Post post = new Post();
		post.setUser(userService.toEntity(postDTO.user));
		post.setImage_url(postDTO.image_url);
		post.setCaption(postDTO.caption);
		post.setCreated_at(postDTO.created_at);
		Post savedPost = postRepository.save(post);
		return convertToDTO(savedPost);
	}

	public PostDTO getPostById(int id) {
		Optional<Post> post = postRepository.findById(id);
		if (post.isPresent()) {
			return convertToDTO(post.get());
		} else {
			throw new ResourceNotFoundException("Post not found with id: " + id);
		}
	}

	public List<PostDTO> getAllPosts() {
		List<Post> posts = postRepository.findAll();
		return posts.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public PostDTO updatePost(int id, PostDTO postDTO) {
		Optional<Post> existingPost = postRepository.findById(id);
		if (existingPost.isPresent()) {
			Post post = existingPost.get();
			post.setUser(userService.toEntity(postDTO.user));
			post.setImage_url(postDTO.image_url);
			post.setCaption(postDTO.caption);
			post.setCreated_at(postDTO.created_at);
			Post updatedPost = postRepository.save(post);
			return convertToDTO(updatedPost);
		} else {
			throw new ResourceNotFoundException("Post not found with id: " + id);
		}
	}

	public void deletePost(int id) {
		Optional<Post> existingPost = postRepository.findById(id);
		if (existingPost.isPresent()) {
			postRepository.delete(existingPost.get());
		} else {
			throw new ResourceNotFoundException("Post not found with id: " + id);
		}
	}

	public PostDTO convertToDTO(Post post) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		UserDetails userDetails = (UserDetails) principal;
		String username = userDetails.getUsername();
		UserDTO user = userService.findByUsername(username);
		PostDTO postDTO = new PostDTO();
		postDTO.post_id = post.getPost_id();
		postDTO.user = user;
		postDTO.user = userService.convertToDTO(post.getUser());
		postDTO.image_url = post.getImage_url();
		postDTO.caption = post.getCaption();
		postDTO.created_at = post.getCreated_at();
		return postDTO;
	}

	public Post convertToEntity(PostDTO postDTO) {
		Post post = new Post();
		post.setPost_id(postDTO.post_id);
		post.setImage_url(postDTO.image_url);
		post.setCaption(postDTO.caption);
		post.setCreated_at(postDTO.created_at);
		return post;
	}

}