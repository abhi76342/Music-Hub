package com.music.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.music.entity.Song;
import com.music.entity.Users;
import com.music.service.SongService;
import com.music.service.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController {
	@Autowired
	UsersService service;
	@Autowired
	SongService songService;
	@PostMapping("/registration")
    public String addUsers(@ModelAttribute Users user) {
		boolean userStatus=service.emailExists(user.getEmail());
		if(userStatus==false) {
		service.addUser(user);
		System.out.println("user added");
		}
		else {
			System.out.println("user alredy exist");
		}
		return "home";
	}
	@PostMapping("/validate")
	public String validate(@RequestParam String email,@RequestParam String password,HttpSession session,Model model)
	{
		System.out.println("controles comes here...");
		if(service.validateUser(email,password)==true) {
			String role=service.getRole(email);
			
			session.setAttribute("email", email);
			
			if(role.equals("admin")) {
				return "adminHome";
			}
			else {
				Users user=service.getUser(email);
				boolean userStatus = user.isPremium();
				List<Song> songsList=songService.fetchAllSongs();
				model.addAttribute("songs",songsList);
				model.addAttribute("isPremium", userStatus);
				return "customerHome";
			}
		}
		else {
			return "login";
		}
	}
	  @GetMapping("/logout")
	  public String logout(HttpSession session) {
	  session.invalidate();
	  return "login";
	  }


}
