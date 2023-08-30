package com.example.model;

public class FollowDTO {

	public int follow_id;
	public UserDTO follower;
	public UserDTO followed;

	public FollowDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getFollow_id() {
		return follow_id;
	}

	public void setFollow_id(int follow_id) {
		this.follow_id = follow_id;
	}

	public UserDTO getFollower() {
		return follower;
	}

	public void setFollower(UserDTO follower) {
		this.follower = follower;
	}

	public UserDTO getFollowed() {
		return followed;
	}

	public void setFollowed(UserDTO followed) {
		this.followed = followed;
	}

	public FollowDTO(int follow_id, UserDTO follower, UserDTO followed) {
		super();
		this.follow_id = follow_id;
		this.follower = follower;
		this.followed = followed;
	}


}
