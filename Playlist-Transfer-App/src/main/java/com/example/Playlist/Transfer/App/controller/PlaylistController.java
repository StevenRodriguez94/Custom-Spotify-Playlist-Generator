package com.example.Playlist.Transfer.App.controller;

import com.example.Playlist.Transfer.App.model.GenreList;
//import com.example.Playlist.Transfer.App.model.TrackListofPlaylist;
import com.example.Playlist.Transfer.App.service.AuthService;
import com.example.Playlist.Transfer.App.service.RetrieveAllGenresService;
import com.example.Playlist.Transfer.App.service.RedirectService;
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
    //private final TrackListofPlaylist trackListofPlaylist;
    private final RedirectService redirectService;
    private final SpotifyApi spotifyApi;
    private final GenreList genreList;
    //private List<PlaylistSimplified> playlists;

    @Autowired
    public PlaylistController(AuthService authService,
                              RetrieveAllGenresService retrieveAllGenresService,
                             // TrackListofPlaylist trackListofPlaylist,
                              RedirectService redirectService,
                              GenreList genreList) {

        this.spotifyApi = authService.getSpotifyApi();
        this.retrieveAllGenresService = retrieveAllGenresService;
        //this.trackListofPlaylist = trackListofPlaylist;
        this.redirectService = redirectService;
        this.genreList = genreList;
    }
    @GetMapping()
    public String getAllGenres(Model model){
        genreList.setGenres(retrieveAllGenresService.getAllGenres(spotifyApi));
        model.addAttribute("title", "Please select what shared attributes all songs of the playlist should have: ");
        return "sharedAttributeSelection/playlistTypeSelection";
    }
    @PostMapping()
    public String postSharedAttributes(@RequestParam String selected){
        //System.out.println(selected);
        return redirectService.redirectAfterSharedAttributeSelection(selected);
    }

//    @GetMapping()
//    public String showPlaylists(Model model){
//        playlists = retrieveAllGenresService.retrievePlaylistList(spotifyApi);
//        model.addAttribute("playlists", playlists);
//        model.addAttribute("title", "List of Playlists");
//        return "retrievePlaylists";
//    }

//    @PostMapping()
//    public String post(@RequestParam String playlistIndex){
//
//        System.out.println(playlistIndex);
//        //Implement this in a service class
//        String plName = playlists.get(Integer.parseInt(playlistIndex)).getName();
//        String plId = playlists.get(Integer.parseInt(playlistIndex)).getId();
//
//        trackListofPlaylist.setPlaylistName(plName);
//        trackListofPlaylist.setTracks(retrieveAllGenresService.retrieveTrackList(spotifyApi, plId));
//        return "redirect:/playlist/track";
//    }

}
