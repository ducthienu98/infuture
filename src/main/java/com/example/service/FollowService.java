package com.example.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ResourceNotFoundException;
import com.example.entity.Follow;
import com.example.entity.User;
import com.example.model.FollowDTO;
import com.example.model.UserDTO;
import com.example.repository.FollowRepository;

@Service
public class FollowService {

	@Autowired
	private FollowRepository followRepository;
	@Autowired
	private UserService userService;

	public List<UserDTO> getFollowedUsers(User user) {
		List<User> listFollowedUsers = followRepository.getListFollowedByFollower(user);
		List<UserDTO> followedUsersDTO = new ArrayList<>();
		for (User u : listFollowedUsers) {
			followedUsersDTO.add(userService.convertToDTO(u));
		}

		return followedUsersDTO;
	}
	
	public List<UserDTO> getUnfollowedUsers(User user) {
		// Lấy danh sách người dùng mà user có id = userId đã follow
		List<User> listFollowedUsers = followRepository.getListFollowedByFollower(user);
		List<UserDTO> followedUsersDTO = new ArrayList<>();
		for (User u : listFollowedUsers) {
			followedUsersDTO.add(userService.convertToDTO(u));
		}

		List<UserDTO> allUsers = userService.getAllUsers();
		Iterator<UserDTO> iterator = allUsers.iterator();
		while (iterator.hasNext()) {
		    UserDTO u = iterator.next();
		    for (UserDTO followedUser : followedUsersDTO) {
		        if (u.getUser_id() == followedUser.getUser_id() ) {
		            iterator.remove();
		        } 
		    }
		    if( u.getUser_id() == user.getUser_id()) {
	        	iterator.remove();
	        }
		}
		allUsers.removeAll(followedUsersDTO);
		allUsers.remove(user);
		return allUsers;
	}

	public FollowDTO createFollow(FollowDTO followDTO) {
		Follow follow = convertToEntity(followDTO);
		Follow savedFollow = followRepository.save(follow);
		return convertToDTO(savedFollow);
	}

	public FollowDTO getFollowById(int id) {
		Optional<Follow> follow = followRepository.findById(id);
		if (follow.isPresent()) {
			return convertToDTO(follow.get());
		} else {
			throw new ResourceNotFoundException("Follow not found with id: " + id);
		}
	}

	public List<FollowDTO> getAllFollows() {
		List<Follow> follows = followRepository.findAll();
		return follows.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public FollowDTO updateFollow(int id, FollowDTO followDTO) {
		Optional<Follow> existingFollow = followRepository.findById(id);
		if (existingFollow.isPresent()) {
			Follow follow = existingFollow.get();
			follow.setFollower(userService.toEntity(followDTO.getFollower()));
			follow.setFollowed(userService.toEntity(followDTO.getFollowed()));
			Follow updatedFollow = followRepository.save(follow);
			return convertToDTO(updatedFollow);
		} else {
			throw new ResourceNotFoundException("Follow not found with id: " + id);
		}
	}

	public void deleteFollow(int id) {
		Optional<Follow> existingFollow = followRepository.findById(id);
		if (existingFollow.isPresent()) {
			followRepository.delete(existingFollow.get());
		} else {
			throw new ResourceNotFoundException("Follow not found with id: " + id);
		}
	}

	public Follow convertToEntity(FollowDTO followDTO) {
		Follow follow = new Follow();
		follow.setFollower(userService.toEntity(followDTO.getFollower()));
		follow.setFollowed(userService.toEntity(followDTO.getFollowed()));
		return follow;
	}

	public FollowDTO convertToDTO(Follow follow) {
		FollowDTO followDTO = new FollowDTO();
		followDTO.follow_id = follow.getFollow_id();
		followDTO.follower = userService.convertToDTO(follow.getFollower());
		followDTO.followed = userService.convertToDTO(follow.getFollowed());
		return followDTO;
	}
}
