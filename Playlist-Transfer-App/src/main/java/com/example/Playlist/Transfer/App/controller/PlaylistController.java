package com.example.Playlist.Transfer.App.controller;

import com.example.Playlist.Transfer.App.model.TrackListofPlaylist;
import com.example.Playlist.Transfer.App.service.AuthService;
import com.example.Playlist.Transfer.App.service.PlaylistRetrievalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;

import java.util.List;

@Controller
@RequestMapping("/playlist")
public class PlaylistController {

    private final PlaylistRetrievalService playlistRetrievalService;
    private final TrackListofPlaylist trackListofPlaylist;
    private final SpotifyApi spotifyApi;
    private List<PlaylistSimplified> playlists;

    @Autowired
    public PlaylistController(AuthService authService, PlaylistRetrievalService playlistRetrievalService, TrackListofPlaylist trackListofPlaylist) {
        this.spotifyApi = authService.getSpotifyApi();
        this.playlistRetrievalService = playlistRetrievalService;
        this.trackListofPlaylist = trackListofPlaylist;
    }

    @GetMapping()
    public String showPlaylists(Model model){
        playlists = playlistRetrievalService.retrievePlaylistList(spotifyApi);
        model.addAttribute("playlists", playlists);
        model.addAttribute("title", "List of Playlists");
        return "retrievePlaylists";
    }

    @PostMapping()
    public String post(@RequestParam String playlistIndex){

        System.out.println(playlistIndex);
        //Implement this in a service class
        String plName = playlists.get(Integer.parseInt(playlistIndex)).getName();
        String plId = playlists.get(Integer.parseInt(playlistIndex)).getId();

        trackListofPlaylist.setPlaylistName(plName);
        trackListofPlaylist.setTracks(playlistRetrievalService.retrieveTrackList(spotifyApi, plId));
        return "redirect:/playlist/track";
    }

}
