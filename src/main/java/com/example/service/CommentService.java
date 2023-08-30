package com.example.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ResourceNotFoundException;
import com.example.entity.Comment;
import com.example.entity.Post;
import com.example.model.CommentDTO;
import com.example.model.PostDTO;
import com.example.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private PostService poService;
	@Autowired
	private UserService userService;
	@Autowired
	private PostService postService;
	@Autowired
	private CommentRepository commentRepository;
	
	public List<CommentDTO> getListCommentByPost(PostDTO post) {
		Post p =postService.convertToEntity(post);
		List<Comment> likes = commentRepository.getListCommentByPost(p);
        return likes.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

	public CommentDTO createComment(CommentDTO commentDTO) {
		Comment comment = new Comment();
		comment.setPost(poService.convertToEntity(commentDTO.getPost()));
		comment.setUser(userService.toEntity(commentDTO.getUser()));
		comment.setText(commentDTO.text);
		comment.setCreated_at(commentDTO.created_at);
		Comment savedComment = commentRepository.save(comment);
		return convertToDTO(savedComment);
	}

	public CommentDTO getCommentById(int id) {
		Optional<Comment> comment = commentRepository.findById(id);
		if (comment.isPresent()) {
			return convertToDTO(comment.get());
		} else {
			throw new ResourceNotFoundException("Comment not found with id: " + id);
		}
	}

	public List<CommentDTO> getAllComments() {
		List<Comment> comments = commentRepository.findAll();
		return comments.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public CommentDTO updateComment(int id, CommentDTO commentDTO) {
		Optional<Comment> existingComment = commentRepository.findById(id);
		if (existingComment.isPresent()) {
			Comment comment = existingComment.get();
			comment.setPost(poService.convertToEntity(commentDTO.getPost()));
			comment.setUser(userService.toEntity(commentDTO.getUser()));
			comment.setText(commentDTO.text);
			comment.setCreated_at(commentDTO.created_at);
			Comment updatedComment = commentRepository.save(comment);
			return convertToDTO(updatedComment);
		} else {
			throw new ResourceNotFoundException("Comment not found with id: " + id);
		}
	}

	public void deleteComment(int id) {
		Optional<Comment> existingComment = commentRepository.findById(id);
		if (existingComment.isPresent()) {
			commentRepository.delete(existingComment.get());
		} else {
			throw new ResourceNotFoundException("Comment not found with id: " + id);
		}
	}

	public CommentDTO convertToDTO(Comment comment) {
		CommentDTO commentDTO = new CommentDTO();
		commentDTO.comment_id = comment.getComment_id();
		commentDTO.post = postService.convertToDTO(comment.getPost());
		commentDTO.user = userService.convertToDTO(comment.getUser());
		commentDTO.text = comment.getText();
		commentDTO.created_at = comment.getCreated_at();
		return commentDTO;
	}
	public Comment toEntity(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setComment_id(commentDTO.comment_id);
        comment.setPost(postService.convertToEntity(commentDTO.getPost()));
        comment.setUser(userService.toEntity(commentDTO.getUser()));
        comment.setText(commentDTO.text);
        comment.setCreated_at(commentDTO.created_at);
        return comment;
    }
}
