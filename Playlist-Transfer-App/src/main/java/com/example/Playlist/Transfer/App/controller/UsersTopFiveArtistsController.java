package com.example.Playlist.Transfer.App.controller;

import com.example.Playlist.Transfer.App.service.AuthService;
import com.example.Playlist.Transfer.App.service.CreatePlaylistService;
import com.example.Playlist.Transfer.App.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.specification.Track;

import java.util.List;

@Controller
@RequestMapping("playlist/usersTopArtists")
public class UsersTopFiveArtistsController {

    private final RecommendationService recommendationService;
    private final CreatePlaylistService createPlaylistService;
    private final SpotifyApi spotifyApi;

    @Autowired
    public UsersTopFiveArtistsController(AuthService authService,
                                         RecommendationService recommendationService,
                                         CreatePlaylistService createPlaylistService) {

        this.spotifyApi = authService.getSpotifyApi();
        this.recommendationService = recommendationService;
        this.createPlaylistService = createPlaylistService;
    }

    @GetMapping()
    public String getUsersTopArtistsSelectionView(Model model){
        model.addAttribute("title", "Playlist with recommended songs based on user's top five artists");
        return "usersTopArtistsRelatedActions/selectUsersTopArtists";
    }

    @PostMapping()
    public String generatePlaylist(RedirectAttributes redirectAttributes,
                                   @RequestParam String btnradio,
                                   @RequestParam String playlistName){

        System.out.println(btnradio + ", " + playlistName);
        List<Track> tracks = recommendationService.getRecommendedTracksUsersTopArtists(Integer.parseInt(btnradio));

        redirectAttributes.addFlashAttribute("message", "An Error occurred while attempting to create your new playlist; Please try again");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");

        if(createPlaylistService.createPlaylist(playlistName)){
            System.out.println("Check spotify app");
            redirectAttributes.addFlashAttribute("message", "You've got a new playlist waiting for you on Spotify! Open the app to explore it now.");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            return "redirect:/playlist";
        }

        return "/playlist/usersTopArtists";
    }

}
