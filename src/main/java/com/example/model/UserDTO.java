package com.example.model;

import java.util.List;

public class UserDTO {
	public int user_id;
	public String username;
	public String fullname;
	public String email;
	public String password;
	public String bio;
	public String profile_picture;

	private List<PostDTO> posts;

	private List<FollowDTO> followers;

	private List<FollowDTO> followeds;

	private List<CommentDTO> comments;

	private List<LikeDTO> likes;

	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public List<PostDTO> getPosts() {
		return posts;
	}

	public void setPosts(List<PostDTO> posts) {
		this.posts = posts;
	}

	public List<FollowDTO> getFollowers() {
		return followers;
	}

	public void setFollowers(List<FollowDTO> followers) {
		this.followers = followers;
	}

	public List<FollowDTO> getFolloweds() {
		return followeds;
	}

	public void setFolloweds(List<FollowDTO> followeds) {
		this.followeds = followeds;
	}

	public List<CommentDTO> getComments() {
		return comments;
	}

	public void setComments(List<CommentDTO> comments) {
		this.comments = comments;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getProfile_picture() {
		return profile_picture;
	}

	public void setProfile_picture(String profile_picture) {
		this.profile_picture = profile_picture;
	}

	public List<LikeDTO> getLikes() {
		return likes;
	}

	public void setLikes(List<LikeDTO> likes) {
		this.likes = likes;
	}

	public UserDTO(int user_id, String username, String fullname, String email, String password, String bio,
			String profile_picture, List<PostDTO> posts, List<FollowDTO> followers, List<FollowDTO> followeds,
			List<CommentDTO> comments, List<LikeDTO> likes) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.fullname = fullname;
		this.email = email;
		this.password = password;
		this.bio = bio;
		this.profile_picture = profile_picture;
		this.posts = posts;
		this.followers = followers;
		this.followeds = followeds;
		this.comments = comments;
		this.likes = likes;
	}

}
