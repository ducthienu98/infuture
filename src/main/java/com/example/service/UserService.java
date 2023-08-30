package com.example.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ResourceNotFoundException;
import com.example.entity.User;
import com.example.model.UserDTO;
import com.example.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public int getFollowersCount(int userId) {
		return userRepository.getFollowersCount(userId);
	}

	public int getFollowedsCount(int userId) {
		return userRepository.getFollowedsCount(userId);
	}

	public boolean existsByUsername(String username) {
		// Kiểm tra xem có tồn tại người dùng với tên người dùng đã cho hay không
		// Trả về true nếu có, false nếu không
		return userRepository.existsByUsername(username);
	}

	public List<UserDTO> searchByFullname(String fullname) {
		List<User> listUsers = userRepository.findByFullnameContainingIgnoreCase(fullname);
		return listUsers.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
	
	public UserDTO createUser(UserDTO userDTO) {
		User user = new User();
		user.setUsername(userDTO.username);
		user.setFullname(userDTO.fullname);
		user.setEmail(userDTO.email);
		user.setPassword(userDTO.password);
		user.setBio(userDTO.bio);
		user.setProfile_picture(userDTO.profile_picture);
		User savedUser = userRepository.save(user);
		return convertToDTO(savedUser);
	}

	public UserDTO getUserById(int id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return convertToDTO(user.get());
		} else {
			throw new ResourceNotFoundException("User not found with id: " + id);
		}
	}

	public List<UserDTO> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public UserDTO updateUser(int id, UserDTO userDTO) {
		Optional<User> existingUser = userRepository.findById(id);
		if (existingUser.isPresent()) {
			User user = existingUser.get();
			user.setUsername(userDTO.username);
			user.setFullname(userDTO.fullname);
			user.setEmail(userDTO.email);
			user.setPassword(userDTO.password);
			user.setBio(userDTO.bio);
			user.setProfile_picture(userDTO.profile_picture);
			User updatedUser = userRepository.save(user);
			return convertToDTO(updatedUser);
		} else {
			throw new ResourceNotFoundException("User not found with id: " + id);
		}
	}

	public void deleteUser(int id) {
		Optional<User> existingUser = userRepository.findById(id);
		if (existingUser.isPresent()) {
			userRepository.delete(existingUser.get());
		} else {
			throw new ResourceNotFoundException("User not found with id: " + id);
		}
	}

	public UserDTO convertToDTO(User user) {
		UserDTO userDTO = new UserDTO();
		if (user != null) {
			userDTO.user_id = user.getUser_id();
			userDTO.username = user.getUsername();
			userDTO.fullname = user.getFullname();
			userDTO.email = user.getEmail();
			userDTO.password = user.getPassword();
			userDTO.bio = user.getBio();
			userDTO.profile_picture = user.getProfile_picture();
		}
		return userDTO;
	}

	public UserDTO findByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if (user != null) {
			return convertToDTO(user);
		} else {
			throw new ResourceNotFoundException("User not found with username: " + username);
		}
	}

	public User toEntity(UserDTO userDTO) {
		User user = new User();
		user.setUser_id(userDTO.user_id);
		user.setUsername(userDTO.username);
		user.setFullname(userDTO.fullname);
		user.setEmail(userDTO.email);
		user.setPassword(userDTO.password);
		user.setBio(userDTO.bio);
		user.setProfile_picture(userDTO.profile_picture);
		return user;
	}
}
