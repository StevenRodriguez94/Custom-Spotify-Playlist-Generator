package com.example.Playlist.Transfer.App.controller;

import com.example.Playlist.Transfer.App.model.GenreList;
import com.example.Playlist.Transfer.App.service.AuthService;
import com.example.Playlist.Transfer.App.service.CreatePlaylistService;
import com.example.Playlist.Transfer.App.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.model_objects.specification.Track;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/playlist/genre")
public class GenreController {

    private final RecommendationService recommendationService;
    private final SpotifyApi spotifyApi;
    private final GenreList genreList;
    private final CreatePlaylistService createPlaylistService;
    private List<String> genres;

    @Autowired
    public GenreController(GenreList genreList, RecommendationService recommendationService, AuthService authService, CreatePlaylistService createPlaylistService) {
        this.spotifyApi = authService.getSpotifyApi();
        this.genreList = genreList;
        this.recommendationService = recommendationService;
        this.createPlaylistService = createPlaylistService;
    }

    @GetMapping()
    public String getGenreSelectionView(Model model){
        genres = genreList.getGenres();
        model.addAttribute("title", "Generate Playlist");
        model.addAttribute("genres", genres);
        return "genreRelatedActions/selectGenre";
    }
    @PostMapping()
    public String generatePlaylist(RedirectAttributes redirectAttributes,
                                   @RequestParam String genreDropdown,
                                   @RequestParam String btnradio,
                                   @RequestParam String playlistName){

        System.out.println(genreDropdown + ", " + btnradio + ", " + playlistName);
        List<Track> tracks = recommendationService.getRecommendedTracksGenre(spotifyApi, Integer.parseInt(btnradio), genreDropdown);

        if(createPlaylistService.createPlaylist(playlistName)){
            System.out.println("Check spotify app");
            redirectAttributes.addFlashAttribute("message", "You've got a new playlist waiting for you on Spotify! Open the app to explore it now.");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            return "redirect:/playlist/genre";
        }

        redirectAttributes.addFlashAttribute("message", "An Error occurred while attempting to create your new playlist; Please try again");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");

        return "/playlist/genre";

        //should take to a page with a progress bar while the playlist gets created and adds the list of songs. After its done it should set a message
        //stating that the user should go into their spotify app and check their new playlist.
        //return genre;
    }

}
