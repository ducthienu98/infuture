package com.example.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ResourceNotFoundException;
import com.example.entity.Like;
import com.example.entity.Post;
import com.example.entity.User;
import com.example.model.LikeDTO;
import com.example.model.PostDTO;
import com.example.model.UserDTO;
import com.example.repository.LikeRepository;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    
    public Integer getLikeByPostAndUser(PostDTO postDTO, UserDTO userDTO) {
        Post post = postService.convertToEntity(postDTO);
        User user = userService.toEntity(userDTO);
        Integer like_id = likeRepository.findLikeIdByUserIdAndPostId(user,post);
        return like_id;
    }
    
    public List<LikeDTO> getListLikeByPost(PostDTO post) {
		Post p =postService.convertToEntity(post);
		List<Like> likes = likeRepository.getListLikeByPost(p);
        return likes.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    
    public LikeDTO createLike(LikeDTO likeDTO) {
        Like like = convertToEntity(likeDTO);
        Like savedLike = likeRepository.save(like);
        return convertToDTO(savedLike);
    }

    public LikeDTO getLikeById(int id) {
        Optional<Like> like = likeRepository.findById(id);
        if (like.isPresent()) {
            return convertToDTO(like.get());
        } else {
            throw new ResourceNotFoundException("Like not found with id: " + id);
        }
    }

    public List<LikeDTO> getAllLikes() {
        List<Like> likes = likeRepository.findAll();
        return likes.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public LikeDTO updateLike(int id, LikeDTO likeDTO) {
        Optional<Like> existingLike = likeRepository.findById(id);
        if (existingLike.isPresent()) {
            Like like = existingLike.get();
            like.setPost(postService.convertToEntity(likeDTO.getPost()));
            like.setUser(userService.toEntity(likeDTO.getUser()));
            Like updatedLike = likeRepository.save(like);
            return convertToDTO(updatedLike);
        } else {
            throw new ResourceNotFoundException("Like not found with id: " + id);
        }
    }

    public void deleteLike(int id) {
        Optional<Like> existingLike = likeRepository.findById(id);
        if (existingLike.isPresent()) {
            likeRepository.delete(existingLike.get());
        } else {
            throw new ResourceNotFoundException("Like not found with id: " + id);
        }
    }

    public Like convertToEntity(LikeDTO likeDTO) {
        Like like = new Like();
        like.setLike_id(likeDTO.like_id);
        like.setPost(postService.convertToEntity(likeDTO.getPost()));
        like.setUser(userService.toEntity(likeDTO.getUser()));
        return like;
    }

    public LikeDTO convertToDTO(Like like) {
        LikeDTO likeDTO = new LikeDTO();
        likeDTO.like_id = like.getLike_id();
        likeDTO.post = postService.convertToDTO(like.getPost());
        likeDTO.user = userService.convertToDTO(like.getUser());
        return likeDTO;
    }
}
