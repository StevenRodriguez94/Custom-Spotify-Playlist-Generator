package com.example.Playlist.Transfer.App.controller;

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
    private final SpotifyApi spotifyApi;

    @Autowired
    public PlaylistController(AuthService authService, PlaylistRetrievalService playlistRetrievalService) {
        this.spotifyApi = authService.getSpotifyApi();
        this.playlistRetrievalService = playlistRetrievalService;
    }

    @GetMapping()
    public String showPlaylists(Model model){
        List<PlaylistSimplified> playlists = playlistRetrievalService.retrievePlaylistList(spotifyApi);
        model.addAttribute("playlists", playlists);
        model.addAttribute("title", "List of Playlists");
        return "retrievePlaylists";
    }

    @PostMapping()
    @ResponseBody
    public String post(@RequestParam String playlistId){
        System.out.println("lll");
        System.out.println(playlistId);
        return playlistId;
    }

}
