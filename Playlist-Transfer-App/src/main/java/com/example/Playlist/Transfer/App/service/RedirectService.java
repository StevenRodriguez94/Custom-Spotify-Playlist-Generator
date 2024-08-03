package com.example.Playlist.Transfer.App.service;

import org.springframework.stereotype.Service;

@Service
public class RedirectService {

    public String redirectAfterSharedAttributeSelection(String selected){
        if(selected.equals("genre"))
            return "redirect:/playlist/genre";
        else if(selected.equals("word"))
            return "word";

        return "length";
    }
}
