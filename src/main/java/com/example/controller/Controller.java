package com.example.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.model.UserDTO;
import com.example.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@org.springframework.stereotype.Controller
public class Controller {

	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	public String login() {
		return "Login";
	}

	@GetMapping("/search")
    public String search() {
        return "search";
    }
	
	@GetMapping("/post-detail")
    public String postDetail() {
        return "Post";
    }
	
	@RequestMapping("/messenger")
	public String messenger() {
		return "Messenger";
	}

	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("user", new UserDTO());
		return "register";
	}

	@RequestMapping("/profile")
	public String profile() {
		return "Profile";
	}
	@RequestMapping("/present-user")
	public String presentUser() {
		return "PresentUser";
	}

	@RequestMapping("/home")
	public String home() {
		return "Home";
	}

	
	@PostMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		// Thực hiện các hành động bổ sung tại đây
		return "redirect:/login";
	}

	private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));

	@PostMapping("/register")
	public String register(@ModelAttribute("user") @Valid UserDTO user, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes, @RequestParam("avatar") MultipartFile avatar) throws IOException {

		if (bindingResult.hasErrors()) {
			String errorMessage = bindingResult.getFieldError().getDefaultMessage();
			model.addAttribute("error", errorMessage);
			return "register";
		} else if (user.getUsername() == null || user.getUsername().isEmpty()) {
			model.addAttribute("error", "Tên đăng nhập không được để trống");
			return "register";
		}else if (user.getUsername().matches("[^a-zA-Z0-9]")) {
			model.addAttribute("error", "Tên người dùng không được chứa ký tự đặc biệt");
			return "register";
		}else if (user.getPassword() == null || user.getPassword().isEmpty()) {
			model.addAttribute("error", "Mật khẩu không được để trống");
			return "register";
		} else if (user.getEmail() == null || user.getEmail().isEmpty()) {
			model.addAttribute("error", "Email không được để trống");
			return "register";
		} else if (userService.existsByUsername(user.getUsername())) {
			model.addAttribute("error", "Tên đăng nhập đã được sử dụng");
			return "register";
		} else if (user.getBio() == null || user.getBio().isEmpty()) {
			model.addAttribute("error", "Miêu tả không được để trống");
			return "register";
		} else if (avatar.isEmpty()) {
			model.addAttribute("error", "Ảnh đại diện không được để trống");
			return "register";
		} else {
			Path staticPath = Paths.get("static");
			Path imagePath = Paths.get("images");
			if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
				Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
			}
			Path file = CURRENT_FOLDER.resolve(staticPath).resolve(imagePath).resolve(avatar.getOriginalFilename());
			try (OutputStream os = Files.newOutputStream(file)) {
				os.write(avatar.getBytes());
			}
			user.setProfile_picture(avatar.getOriginalFilename());
			userService.createUser(user);
			redirectAttributes.addFlashAttribute("message", "Đăng ký thành công! Hãy đăng nhập!");
			return "redirect:/login";
		}
	}


	@RequestMapping("/create-post")
	public String createPost() {
		return "CreatePost";
	}
}
