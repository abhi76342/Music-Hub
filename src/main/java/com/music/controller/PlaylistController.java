package com.music.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.music.entity.Playlist;
import com.music.entity.Song;
import com.music.service.PlaylistService;
import com.music.service.SongService;
@Controller
public class PlaylistController {
	@Autowired
SongService songservice;	
	@Autowired
	PlaylistService playlistService;
	
	@GetMapping("/createPlaylist")
	public String createPlaylist(Model model) {
	    List<Song> songList = songservice.fetchAllSongs();
	    if (songList != null) {
	        model.addAttribute("songs", songList);
	    } else {
	        // Handle the case where songList is null (e.g., show an error message)
	        // You can also log an error message or perform other error handling actions
	        model.addAttribute("songs", Collections.emptyList()); // Provide an empty list to avoid NullPointerException in the view
	    }
	    return "createPlaylist";
	}

	@PostMapping("/addPlaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist) {
		System.out.println("out");
		//playlist.setName("Songs");
		//updating playlist table
		playlistService.addPlaylist(playlist);
		System.out.println(playlist);
		//updating song table
		List<Song> songList =playlist.getSongs();
		for(Song s : songList) {
		   s.getPlaylist().add(playlist);
		   //update song object in db
		   songservice.updateSong(s);
		}
		return "adminHome";
	}
	
    @GetMapping("/displayPlaylists")
	public String viewPlaylists(Model model) {
		List<Playlist> allPlaylists=playlistService.fetchAllPlaylists();
		model.addAttribute("allPlaylists", allPlaylists);
		return "displayPlaylists";
	}
	 
}