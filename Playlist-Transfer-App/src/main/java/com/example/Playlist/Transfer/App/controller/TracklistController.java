//package com.example.Playlist.Transfer.App.controller;
//
//import com.example.Playlist.Transfer.App.model.TrackListofPlaylist;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/playlist/track")
//public class TracklistController {
//
//    private final TrackListofPlaylist trackListofPlaylist;
//
//    @Autowired
//    public TracklistController(TrackListofPlaylist trackListofPlaylist) {
//        this.trackListofPlaylist = trackListofPlaylist;
//    }
//
//    @GetMapping()
//    public String getTracklistofPlaylist(Model model){
//
//        model.addAttribute("tracks", trackListofPlaylist.getTracks());
//        return "retrieveTracklist";
//    }
//}
