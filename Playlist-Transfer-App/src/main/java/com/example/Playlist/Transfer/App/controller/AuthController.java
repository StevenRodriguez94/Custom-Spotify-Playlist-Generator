package com.example.Playlist.Transfer.App.controller;

import com.example.Playlist.Transfer.App.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("login")
    @ResponseBody
    public RedirectView getSpotifyAuthorizationCode(){
        return authService.getAuthCode();
    }

    @GetMapping("getcode")
    public String getSpotifyAccessToken(@RequestParam("code") String userCode, HttpServletResponse response) throws IOException{
        return authService.getAccessCode(userCode, response);
    }

}
