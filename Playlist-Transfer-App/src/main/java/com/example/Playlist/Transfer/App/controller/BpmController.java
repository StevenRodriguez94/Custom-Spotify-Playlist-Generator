package com.example.Playlist.Transfer.App.controller;

import com.example.Playlist.Transfer.App.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import se.michaelthelin.spotify.SpotifyApi;

@Controller
@RequestMapping("/playlist/bpm")
public class BpmController {

    private final SpotifyApi spotifyApi;
    public BpmController(AuthService authService) {
        this.spotifyApi = authService.getSpotifyApi();

    }

    @GetMapping()
    public String getBpmSelectionView(Model model){
        model.addAttribute("title", "Generate Playlist");
        return "bpmRelatedActions/selectBpm";
    }

    @PostMapping()
    @ResponseBody
    public String generatePlaylist(RedirectAttributes redirectAttributes,
                                   @RequestParam String bpmNumber,
                                   @RequestParam String btnradio,
                                   @RequestParam String playlistName){

        System.out.println(bpmNumber + ", " + btnradio + ", " + playlistName);
        return bpmNumber;
    }


}
