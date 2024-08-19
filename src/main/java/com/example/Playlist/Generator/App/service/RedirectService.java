package com.example.Playlist.Generator.App.service;

import com.example.Playlist.Generator.App.customTags.Log;
import org.springframework.stereotype.Service;

@Service
public class RedirectService {

    @Log
    public String redirectAfterSharedAttributeSelection(String selected){
        return switch (selected) {
            case "genre" -> "redirect:/playlist/genre";
            case "bpm" -> "redirect:/playlist/bpm";
            case "topartists" -> "redirect:/playlist/usersTopArtists";
            case "songlength" -> "redirect:/playlist/songLength";
            default -> "Not found";
        };

    }
}
