package com.example.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Follow;
import com.example.entity.User;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer> {
	
	
	@Query("SELECT f.followed FROM Follow f WHERE f.follower = :user")
    List<User> getListFollowedByFollower(User user);
	
}
