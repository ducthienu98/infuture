package com.example.model;

import java.time.LocalDateTime;
import java.util.List;


public class PostDTO {
	public int post_id;
	public UserDTO user;
	public String image_url;
	public String caption;
	public LocalDateTime created_at;
	private List<CommentDTO> comments;
	private List<LikeDTO> likes;

	public PostDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<LikeDTO> getLikes() {
		return likes;
	}

	public void setLikes(List<LikeDTO> likes) {
		this.likes = likes;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public List<CommentDTO> getComments() {
		return comments;
	}

	public void setComments(List<CommentDTO> comments) {
		this.comments = comments;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public PostDTO(int post_id, UserDTO user, String image_url, String caption, LocalDateTime created_at,
			List<CommentDTO> comments, List<LikeDTO> likes) {
		super();
		this.post_id = post_id;
		this.user = user;
		this.image_url = image_url;
		this.caption = caption;
		this.created_at = created_at;
		this.comments = comments;
		this.likes = likes;
	}


}
