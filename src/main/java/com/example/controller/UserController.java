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

import com.example.model.PostCount;
import com.example.model.PostDTO;
import com.example.model.UserDTO;
import com.example.service.CommentService;
import com.example.service.LikeService;
import com.example.service.PostService;
import com.example.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private PostService postService;
	@Autowired
	private LikeService likeService;
	@Autowired
	private CommentService commentService;

	@GetMapping("/users")
	public ResponseEntity<List<UserDTO>> listAllUsers() {
		List<UserDTO> users = userService.getAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity<List<UserDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
	}

	@GetMapping("/present-users")
	public ResponseEntity<?> getUserById() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) principal;
			String username = userDetails.getUsername();
			UserDTO user = userService.findByUsername(username);
			if (user == null) {
				System.out.println("null rồi nhé");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
			List<PostDTO> posts = postService.getListPostByUser(user);
			int likeCounts = 0;
			int commentCounts = 0;
			List<PostCount> postCount = new ArrayList<>();
			for (int i = 0; i < posts.size(); i++) {
				likeCounts = likeService.getListLikeByPost(posts.get(i)).size();
				commentCounts = commentService.getListCommentByPost(posts.get(i)).size();
				postCount.add(new PostCount(posts.get(i), likeCounts, commentCounts));
			}

			Map<String, Object> response = new HashMap<>();
			response.put("user", user);
			response.put("posts", postCount);
			response.put("followersCount", userService.getFollowersCount(user.getUser_id()));
			response.put("followedsCount", userService.getFollowedsCount(user.getUser_id()));
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Người dùng chưa đăng nhập");
		}

	}

	@GetMapping("/profile")
	public ResponseEntity<?> profile(@RequestParam(value = "userId") int userId) {
		UserDTO user = userService.getUserById(userId);
		List<PostDTO> posts = postService.getListPostByUser(user);
		int likeCounts = 0;
		int commentCounts = 0;
		List<PostCount> postCount = new ArrayList<>();
		for (int i = 0; i < posts.size(); i++) {
			likeCounts = likeService.getListLikeByPost(posts.get(i)).size();
			commentCounts = commentService.getListCommentByPost(posts.get(i)).size();
			postCount.add(new PostCount(posts.get(i), likeCounts, commentCounts));
		}
		
		Map<String, Object> response = new HashMap<>();
		response.put("user", user);
		response.put("posts", postCount);
		response.put("followersCount", userService.getFollowersCount(user.getUser_id()));
		response.put("followedsCount", userService.getFollowedsCount(user.getUser_id()));
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
