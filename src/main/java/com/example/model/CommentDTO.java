package com.example.model;

import java.time.LocalDateTime;

public class CommentDTO {
	public int comment_id;
	public PostDTO post;
	public UserDTO user;
	public String text;
	public LocalDateTime created_at;

	public CommentDTO() {
		super();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getComment_id() {
		return comment_id;
	}

	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
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

	public CommentDTO(int comment_id, PostDTO post, UserDTO user, String text, LocalDateTime created_at) {
		super();
		this.comment_id = comment_id;
		this.post = post;
		this.user = user;
		this.text = text;
		this.created_at = created_at;
	}

}
