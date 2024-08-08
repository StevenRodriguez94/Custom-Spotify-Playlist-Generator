package com.example.Playlist.Transfer.App.controller;

import com.example.Playlist.Transfer.App.model.GenreList;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/playlist/bpm")
public class BpmController {

    private final RecommendationService recommendationService;
    private final GenreList genreList;
    private final CreatePlaylistService createPlaylistService;
    private List<String> genres;
    private String genreConcat;
    private final SpotifyApi spotifyApi;

    @Autowired
    public BpmController(AuthService authService, RecommendationService recommendationService, CreatePlaylistService createPlaylistService, GenreList genreList) {
        this.spotifyApi = authService.getSpotifyApi();
        this.recommendationService = recommendationService;
        this.createPlaylistService = createPlaylistService;
        this.genreList = genreList;
        this.genres = new ArrayList<>();
    }

    @GetMapping()
    public String getBpmSelectionView(Model model){
        //genres = genreList.getGenres();
        setFiveGenres();
        model.addAttribute("title", "Generate Playlist");
        return "bpmRelatedActions/selectBpm";
    }

    @PostMapping()
    public String generatePlaylist(RedirectAttributes redirectAttributes,
                                   @RequestParam String bpmNumber,
                                   @RequestParam String btnradio,
                                   @RequestParam String playlistName){

        System.out.println(bpmNumber + ", " + btnradio + ", " + playlistName);
//        if (Float.parseFloat(bpmNumber) > 250 || Float.parseFloat(bpmNumber) < 60){
//            redirectAttributes.addFlashAttribute("message", "An Error occurred while attempting to create your new playlist; Please enter a bpm value between 60 and 250 please.");
//            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
//            return "bpmRelatedActions/selectBpm";
//        }
        List<Track> tracks = recommendationService.getRecommendedTracksBpm(Integer.parseInt(btnradio), Float.parseFloat(bpmNumber), genreConcat);

        if(createPlaylistService.createPlaylist(playlistName)){
            System.out.println("Check spotify app");
            redirectAttributes.addFlashAttribute("message", "You've got a new playlist waiting for you on Spotify! Open the app to explore it now.");
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            return "redirect:/playlist";
        }

        redirectAttributes.addFlashAttribute("message", "An Error occurred while attempting to create your new playlist; Please try again");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");

        return "/playlist/bpm";

    }

    private void setFiveGenres(){
        genreConcat = "";
        for (int i = 0; i < 5; i ++){
            int random = new Random().nextInt(genreList.getGenres().size());
            if (!genres.contains(genreList.getGenres().get(random))) {
                genres.add(genreList.getGenres().get(random));
                if (i != 4)
                    genreConcat += genres.get(i) + ",";
                else
                    genreConcat += genres.get(i);
            }
            else
                i --;
        }
    }


}
