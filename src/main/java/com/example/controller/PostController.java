package com.example.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.model.CommentDTO;
import com.example.model.LikeDTO;
import com.example.model.PostDTO;
import com.example.model.UserDTO;
import com.example.service.CommentService;
import com.example.service.LikeService;
import com.example.service.PostService;
import com.example.service.UserService;


@RestController
@RequestMapping("/api")
public class PostController {
	@Autowired
	private PostService postService;
	@Autowired
	private UserService userService;
	@Autowired
	private LikeService likeService;
	@Autowired
	private CommentService commentService;

    @GetMapping("/posts")
	public ResponseEntity<List<PostDTO>> listAllPosts(){
		List<PostDTO> listPost= postService.getAllPosts();
		if(listPost.isEmpty()) {
			return new ResponseEntity<List<PostDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<PostDTO>>(listPost, HttpStatus.OK);
	}

    @GetMapping("/post-detail")
    public ResponseEntity<?> handlePost(@RequestParam(value = "postId") int postId) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		UserDetails userDetails = (UserDetails) principal;
		String username = userDetails.getUsername();
		UserDTO user = userService.findByUsername(username);
    	PostDTO post = postService.getPostById(postId);
    	List<LikeDTO> likes = likeService.getListLikeByPost(post);
    	List<CommentDTO> comments = commentService.getListCommentByPost(post);
    	Integer like = likeService.getLikeByPostAndUser(post,user);
    	int likeOrDislike = 0;
    	if(like != null) {
    		likeOrDislike = 1;
    	}
    	Map<String, Object> response = new HashMap<>();
    	response.put("comments", comments);
    	response.put("likes", likes);
		response.put("post", post);
		response.put("likeOrDislike", likeOrDislike);
		return new ResponseEntity<>(response, HttpStatus.OK);
    }
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
    @PostMapping("/create-post")
    public ResponseEntity<?> createPost(@RequestParam("avatar") MultipartFile avatar, @RequestParam("caption") String caption) throws IOException {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		UserDetails userDetails = (UserDetails) principal;
		String username = userDetails.getUsername();
		UserDTO user = userService.findByUsername(username);
    	PostDTO post = new PostDTO();
    	post.setUser(user);
        post.setCaption(caption);
        Path staticPath = Paths.get("static");
		Path imagePath = Paths.get("images");
		if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
			Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
		}
		Path file = CURRENT_FOLDER.resolve(staticPath).resolve(imagePath).resolve(avatar.getOriginalFilename());
		try (OutputStream os = Files.newOutputStream(file)) {
			os.write(avatar.getBytes());
		}
		post.setImage_url(avatar.getOriginalFilename());
        postService.createPost(post);
        Map<String, Object> response = new HashMap<>();
        response.put("post_id", post.getPost_id());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
