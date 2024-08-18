package com.example.Playlist.Generator.App.controller;

import com.example.Playlist.Generator.App.model.GenreList;
import com.example.Playlist.Generator.App.service.AuthService;
import com.example.Playlist.Generator.App.service.RetrieveAllGenresService;
import com.example.Playlist.Generator.App.service.RedirectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.michaelthelin.spotify.SpotifyApi;

@Controller
@RequestMapping("/playlist")
public class PlaylistController {

    private final RetrieveAllGenresService retrieveAllGenresService;
    private final RedirectService redirectService;
    private final SpotifyApi spotifyApi;
    private final GenreList genreList;

    @Autowired
    public PlaylistController(AuthService authService,
                              RetrieveAllGenresService retrieveAllGenresService,
                              RedirectService redirectService,
                              GenreList genreList) {

        this.spotifyApi = authService.getSpotifyApi();
        this.retrieveAllGenresService = retrieveAllGenresService;
        this.redirectService = redirectService;
        this.genreList = genreList;
    }
    @GetMapping()
    public String setUpAttrSelectionPage(Model model){
        genreList.setGenres(retrieveAllGenresService.getAllGenres(spotifyApi));
        model.addAttribute("title", "Please select what shared attributes all songs of the playlist should have: ");

        return "sharedAttributeSelection/playlistTypeSelection";
    }
    @PostMapping()
    public String postSharedAttributes(@RequestParam String selected){
        return redirectService.redirectAfterSharedAttributeSelection(selected);
    }

}
