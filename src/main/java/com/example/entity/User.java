package com.example.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    private String username;
    private String fullname;
    @Email
    private String email;
    private String password;
    private String bio;
    private String profile_picture;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private List<Post> posts;

    @OneToMany(mappedBy = "follower",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Follow> followers;

    @OneToMany(mappedBy = "followed",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Follow> followeds;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Like> likes;


	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public List<Like> getLikes() {
		return likes;
	}



	public void setLikes(List<Like> likes) {
		this.likes = likes;
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


	public User(int user_id, String username, String fullname, String email, String password, String bio,
			String profile_picture, List<Post> posts, List<Follow> followers, List<Follow> followeds,
			List<Comment> comments, List<Like> likes) {
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


	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<Follow> getFollowers() {
		return followers;
	}

	public void setFollowers(List<Follow> followers) {
		this.followers = followers;
	}

	public List<Follow> getFolloweds() {
		return followeds;
	}

	public void setFolloweds(List<Follow> followeds) {
		this.followeds = followeds;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}


	public int getFollowersCount() {
	    return this.followers.size();
	}

	public int getFollowedsCount() {
	    return this.followeds.size();
	}

	
}
