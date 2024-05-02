package com.music.service;

import java.util.List;

import com.music.entity.Playlist;

public interface PlaylistService {

public void addPlaylist(Playlist playlist);

public List<Playlist> fetchAllPlaylists();

}
