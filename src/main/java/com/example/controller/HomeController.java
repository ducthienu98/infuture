package com.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.CommentDTO;
import com.example.model.LikeDTO;
import com.example.model.PostDTO;
import com.example.model.UserDTO;
import com.example.service.CommentService;
import com.example.service.LikeService;
import com.example.service.PostService;
import com.example.service.UserService;


@RestController
@RequestMapping("/api")
public class HomeController {
	@Autowired
	private PostService postService;
	@Autowired
	private UserService userService;
	@Autowired
	private LikeService likeService;
	@Autowired
	private CommentService commentService;

	@GetMapping("/post-home/all-post")
	public ResponseEntity<?> getAllPost() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		UserDetails userDetails = (UserDetails) principal;
		String username = userDetails.getUsername();
		UserDTO user = userService.findByUsername(username);

		List<PostDTO> posts = postService.getAllPosts();
		List<Object[]> listPost = new ArrayList<>() ;
		for(int i = 0; i<posts.size(); i++) {
        	List<LikeDTO> likes = likeService.getListLikeByPost(posts.get(i));
        	List<CommentDTO> comments = commentService.getListCommentByPost(posts.get(i));
        	PostDTO post = posts.get(i);
        	Integer like = likeService.getLikeByPostAndUser(post,user);
        	int likeOrDislike = 0;
        	if(like != null) {
        		likeOrDislike = 1;
        	}
        	
        	
        	Object[] myArray = new Object[4];
        	myArray[0] = post;
        	myArray[1] = likes;
        	myArray[2] = comments;
        	myArray[3] = likeOrDislike;
        	listPost.add(myArray);
        }
		Map<String, Object> response = new HashMap<>();
		response.put("listPost", listPost);
		response.put("user", user);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(value = "fullname") String fullname) {
		Map<String, Object> response = new HashMap<>();
		List<UserDTO> listUsers = userService.searchByFullname(fullname);
		response.put("listUsers", listUsers);
		return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
