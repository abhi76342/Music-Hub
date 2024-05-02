package com.music.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController {
	@GetMapping("/login")
  public String login() {
	  return "login";
  }
	@GetMapping("/registration")
	public String registration() {
		return "registration";
	}

	@GetMapping("/newSong")
	public String newSong() {
		return "newSong";
	}
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	@GetMapping("/index")
	public String index() {
		return "index";
	}
}
