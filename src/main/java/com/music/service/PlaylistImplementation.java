package com.music.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.music.entity.Playlist;
import com.music.repository.PlaylistRepository;
@Service
public class PlaylistImplementation implements PlaylistService{
@Autowired
PlaylistRepository repo;

@Override
public void addPlaylist(Playlist playlist) {
	 repo.save(playlist);
}

@Override
public List<Playlist> fetchAllPlaylists() {
	return repo.findAll();
}
}
