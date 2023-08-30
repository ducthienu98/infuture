package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.LikeDTO;
import com.example.model.PostDTO;
import com.example.model.UserDTO;
import com.example.service.LikeService;
import com.example.service.PostService;
import com.example.service.UserService;


@RestController
@RequestMapping("/api")
public class LikeController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LikeService likeService;
	@Autowired
	private PostService postService;
	
	@PostMapping("/post-home/like-post")
	public ResponseEntity<?> follow(@RequestBody Map<String, Object> payload) {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    Object principal = authentication.getPrincipal();
	    UserDetails userDetails = (UserDetails) principal;
	    String username = userDetails.getUsername();
	    UserDTO user = userService.findByUsername(username);

	    Map<String, Object> response = new HashMap<>();
	    try {
	        int post_id = Integer.parseInt((String) payload.get("post_id"));
	        PostDTO post = postService.getPostById(post_id);
	        Integer like_id = likeService.getLikeByPostAndUser(post, user);
	        if (like_id == null) {
	            // Nếu người dùng chưa like bài viết, thêm một like mới
	            LikeDTO like = new LikeDTO();
	            like.setPost(post);
	            like.setUser(user);
	            likeService.createLike(like);
	            response.put("action", "like");
	        } else {
	            // Nếu người dùng đã like bài viết, xóa like
	            likeService.deleteLike(like_id);
	            response.put("action", "dislike");
	        }

	        // Trả về số lượng likes mới nhất
	        List<LikeDTO> likes = likeService.getListLikeByPost(post);
	        response.put("likes", likes.size());
	    } catch (NullPointerException e) {
	        e.printStackTrace();
	    }

	    return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
