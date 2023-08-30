package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Comment;
import com.example.entity.Post;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{
	List<Comment> getListCommentByPost(Post post);
}
