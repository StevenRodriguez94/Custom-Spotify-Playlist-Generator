package com.example.Playlist.Transfer.App.controller;


import com.example.Playlist.Transfer.App.model.ClientInfo;
import com.example.Playlist.Transfer.App.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetListOfCurrentUsersPlaylistsRequest;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/api")
public class AuthController {

    //    private static final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:8080/api/getcode");
    private final AuthService authService;
//    private String code = "";
//
//    static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
//            .setClientId(ClientInfo.CLIENT_ID.getClientInfo())
//            .setClientSecret(ClientInfo.CLIENT_SECRET.getClientInfo())
//            .setRedirectUri(redirectUri)
//            .build();

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("login")
    @ResponseBody
    public RedirectView getSpotifyAuthorizationCode(){

//        AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
//                .scope("playlist-read-private, playlist-read-collaborative")
//                .show_dialog(true)
//                .build();
//
//        final URI uri = authorizationCodeUriRequest.execute();
//        String url = uri.toString();
//
//        RedirectView redirectView = new RedirectView();
//        redirectView.setUrl(url);
//        return redirectView;

        return authService.getAuthCode();
    }

    @GetMapping("getcode")
    public String getSpotifyAccessToken(@RequestParam("code") String userCode, HttpServletResponse response) throws IOException{
//        code = userCode;
//
//        AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(code).build();
//
//        try{
//            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();
//
//            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
//            spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());
//
//            System.out.println("Expires in: " + authorizationCodeCredentials.getExpiresIn());
//        }
//        catch (IOException | SpotifyWebApiException | ParseException e){
//            System.out.println("Error: " + e.getMessage());
//        }
//
//        response.sendRedirect("http://localhost:8080/playlist");
//        System.out.println(spotifyApi.getAccessToken());
//
//        return spotifyApi.getAccessToken();
        return authService.getAccessCode(userCode, response);
    }

}
