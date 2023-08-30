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

import com.example.model.CommentDTO;
import com.example.model.PostDTO;
import com.example.model.UserDTO;
import com.example.service.CommentService;
import com.example.service.PostService;
import com.example.service.UserService;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private UserService userService;

	@Autowired
	private CommentService commentService;
	@Autowired
	private PostService postService;

	@PostMapping("/post-home/comment-post")
	public ResponseEntity<?> commentPost(@RequestBody Map<String, Object> payload) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		UserDetails userDetails = (UserDetails) principal;
		String username = userDetails.getUsername();
		UserDTO user = userService.findByUsername(username);

		Map<String, Object> response = new HashMap<>();
		try {
			int post_id = Integer.parseInt((String) payload.get("id_post"));
			String text= (String) payload.get("text");
			PostDTO post = postService.getPostById(post_id);
			
			CommentDTO comment = new CommentDTO();
			comment.setPost(post);
			comment.setUser(user);
			comment.setText(text);
			commentService.createComment(comment);
			List<CommentDTO> comments = commentService.getListCommentByPost(post);
			response.put("comment", comment);
			response.put("commentCount", comments.size());
			System.out.print(comments.size());
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
