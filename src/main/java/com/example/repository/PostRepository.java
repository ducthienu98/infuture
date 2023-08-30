package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Post;
import com.example.entity.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{
	List<Post> getListPostByUser(User user);
	@Query("SELECT p FROM Post p ORDER BY function('RAND')")
	List<Post> findAll();
}
