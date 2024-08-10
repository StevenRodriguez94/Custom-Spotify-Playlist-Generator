package com.example.Playlist.Transfer.App.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class loginController {

    @GetMapping()
    public String getHomePage(Model model){
        model.addAttribute("title", "Spotify Playlist Generator");
        return "login/redirectToAuthSpotify.html";
    }

}
