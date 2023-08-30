package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Like;
import com.example.entity.Post;
import com.example.entity.User;

@Repository
public interface LikeRepository extends JpaRepository<Like, Integer>{
	List<Like> getListLikeByPost(Post post);
	@Query("SELECT l.like_id FROM Like l WHERE l.user = :user AND l.post = :post")
    Integer findLikeIdByUserIdAndPostId(@Param("user") User user, @Param("post") Post post);
}
