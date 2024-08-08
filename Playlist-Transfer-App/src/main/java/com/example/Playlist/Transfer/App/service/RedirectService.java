package com.example.Playlist.Transfer.App.service;

import org.springframework.stereotype.Service;

@Service
public class RedirectService {

    public String redirectAfterSharedAttributeSelection(String selected){
        return switch (selected) {
            case "genre" -> "redirect:/playlist/genre";
            case "word" -> "redirect:/playlist/bpm";
            case "topartists" -> "redirect:/playlist/usersTopArtists";
            default -> "Not found";
        };

    }
}
