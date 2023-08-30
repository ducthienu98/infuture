package com.example.model;


public class PostCount {
	private PostDTO post;
	private int likeCounts;
	private int commentCounts;
	public PostDTO getPost() {
		return post;
	}
	public void setPost(PostDTO post) {
		this.post = post;
	}
	public PostCount(PostDTO post, int likeCounts, int commentCounts) {
		super();
		this.post = post;
		this.likeCounts = likeCounts;
		this.commentCounts = commentCounts;
	}
	public int getLikeCounts() {
		return likeCounts;
	}
	public void setLikeCounts(int likeCounts) {
		this.likeCounts = likeCounts;
	}
	public int getCommentCounts() {
		return commentCounts;
	}
	public void setCommentCounts(int commentCounts) {
		this.commentCounts = commentCounts;
	}
	public PostCount() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
