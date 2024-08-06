package com.example.Playlist.Transfer.App.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class loginController {

    @GetMapping()
    public String redirectToAuthControl(){
        return "redirect:/api/login";
    }
}
