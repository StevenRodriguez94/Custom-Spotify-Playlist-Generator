package com.example.Playlist.Generator.App.controller;

import com.example.Playlist.Generator.App.model.GenreList;
import com.example.Playlist.Generator.App.service.CreatePlaylistService;
import com.example.Playlist.Generator.App.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import se.michaelthelin.spotify.model_objects.specification.Track;

import java.util.List;

@Controller
@RequestMapping("/playlist/genre")
public class GenreController {

    private final RecommendationService recommendationService;
    private final GenreList genreList;
    private final CreatePlaylistService createPlaylistService;
    private List<String> genres;

    @Autowired
    public GenreController(GenreList genreList,
                           RecommendationService recommendationService,
                           CreatePlaylistService createPlaylistService) {

        this.genreList = genreList;
        this.recommendationService = recommendationService;
        this.createPlaylistService = createPlaylistService;
    }

    @GetMapping()
    public String getGenreSelectionView(Model model){
        genres = genreList.getGenres();
        model.addAttribute("title", "Generate Playlist by Genre");
        model.addAttribute("genres", genres);
        return "genreRelatedActions/selectGenre";
    }
    @PostMapping()
    public String generatePlaylist(RedirectAttributes redirectAttributes,
                                   @RequestParam String genreDropdown,
                                   @RequestParam String btnradio,
                                   @RequestParam String playlistName){

        System.out.println(genreDropdown + ", " + btnradio + ", " + playlistName);
        List<Track> tracks = recommendationService.getRecommendedTracksGenre(Integer.parseInt(btnradio), genreDropdown);

        redirectAttributes.addFlashAttribute("message", "An Error occurred while attempting to create your new playlist; Please try again");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");

        if(createPlaylistService.createPlaylist(playlistName)){
            System.out.println("Check spotify app");
            redirectAttributes.addFlashAttribute("message", "You've got a new playlist waiting for you on Spotify! Open the app to explore it now.");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            return "redirect:/playlist";
        }

        return "/playlist/genre";
    }

}
