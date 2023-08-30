package com.example.model;

public class LikeDTO {
	public int like_id;
	public PostDTO post;
	public UserDTO user;

	public int getLike_id() {
		return like_id;
	}

	public void setLike_id(int like_id) {
		this.like_id = like_id;
	}

	public LikeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PostDTO getPost() {
		return post;
	}

	public void setPost(PostDTO post) {
		this.post = post;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public LikeDTO(int like_id, PostDTO post, UserDTO user) {
		super();
		this.like_id = like_id;
		this.post = post;
		this.user = user;
	}

}
