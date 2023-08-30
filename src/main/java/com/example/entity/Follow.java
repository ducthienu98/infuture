package com.example.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "follows", uniqueConstraints = @UniqueConstraint(columnNames = {"follower_id", "followed_id"}))
public class Follow {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int follow_id;
  
  @ManyToOne
  @JoinColumn(name = "follower_id")
  private User follower;

	@ManyToOne
  @JoinColumn(name = "followed_id")
  private User followed;





	public Follow(int follow_id, User follower, User followed) {
	super();
	this.follow_id = follow_id;
	this.follower = follower;
	this.followed = followed;
}


	public int getFollow_id() {
	return follow_id;
}


public void setFollow_id(int follow_id) {
	this.follow_id = follow_id;
}


	public User getFollower() {
		return follower;
	}


	public void setFollower(User follower) {
		this.follower = follower;
	}


	public User getFollowed() {
		return followed;
	}


	public void setFollowed(User followed) {
		this.followed = followed;
	}

	public Follow() {
		super();
		// TODO Auto-generated constructor stub
	}

}
