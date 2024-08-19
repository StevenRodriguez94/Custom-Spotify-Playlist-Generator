package com.example.Playlist.Generator.App.controller;

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
@RequestMapping("/playlist/songLength")
public class SongLengthController {

    private final RecommendationService recommendationService;
    private final CreatePlaylistService createPlaylistService;

    @Autowired
    public SongLengthController(RecommendationService recommendationService, CreatePlaylistService createPlaylistService) {
        this.recommendationService = recommendationService;
        this.createPlaylistService = createPlaylistService;
    }

    @GetMapping()
    public String getSongLengthSelectionView(Model model){
        model.addAttribute("title", "Generate Playlist by Song Length");
        return "songLengthRelatedActions/selectSongLength";
    }

    @PostMapping()
    public String generatePlaylist(RedirectAttributes redirectAttributes,
                                   @RequestParam String songMin,
                                   @RequestParam String btnradio,
                                   @RequestParam String playlistName){

        System.out.println(songMin + ", " + btnradio + ", " + playlistName);
        List<Track> tracks = recommendationService.getRecommendedTracksSongLength(Integer.parseInt(btnradio), Integer.parseInt(songMin));

        if(createPlaylistService.createPlaylist(playlistName)){
            System.out.println("Check spotify app");
            redirectAttributes.addFlashAttribute("message", "You've got a new playlist waiting for you on Spotify! Open the app to explore it now.");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            return "redirect:/playlist";
        }

        redirectAttributes.addFlashAttribute("message", "An Error occurred while attempting to create your new playlist; Please try again");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");

        return "/playlist/songLength";

    }
}
