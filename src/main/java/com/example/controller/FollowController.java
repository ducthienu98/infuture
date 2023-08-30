package com.example.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.FollowDTO;
import com.example.model.UserDTO;
import com.example.service.FollowService;
import com.example.service.UserService;

@RestController
@RequestMapping("/api")
public class FollowController {

	@Autowired
	private FollowService followService;
	@Autowired
	private UserService userService;

	@GetMapping("/unfollowed_users")
	public List<UserDTO> getUnfollowedUsers() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		UserDetails userDetails = (UserDetails) principal;
		String username = userDetails.getUsername();
		UserDTO user = userService.findByUsername(username);
		List<UserDTO> unfollowedUsers = followService.getUnfollowedUsers(userService.toEntity(user));
		return unfollowedUsers;
	}

	@PostMapping("/follow-user")
	public ResponseEntity<Void> follow(@RequestBody Map<String, Object> payload) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		UserDetails userDetails = (UserDetails) principal;
		String username = userDetails.getUsername();
		UserDTO userFollower = userService.findByUsername(username);
		int followedId = Integer.parseInt((String) payload.get("followed_id"));
		UserDTO userFollowed = userService.getUserById(followedId);
		FollowDTO follow = new FollowDTO();
		follow.setFollower(userFollower);
		follow.setFollowed(userFollowed);
		followService.createFollow(follow);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@DeleteMapping("/unfollow-user")
	public ResponseEntity<?> deleteFollow(@RequestBody Map<String, Object> payload) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		UserDetails userDetails = (UserDetails) principal;
		String username = userDetails.getUsername();
		UserDTO userFollower = userService.findByUsername(username);
		int followedId = Integer.parseInt((String) payload.get("followed_id"));
		UserDTO userFollowed = userService.getUserById(followedId);
		FollowDTO follow = new FollowDTO();
		follow.setFollower(userFollower);
		follow.setFollowed(userFollowed);
		followService.deleteFollow(follow.getFollow_id());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
