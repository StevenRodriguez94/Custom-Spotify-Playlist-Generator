//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.Playlist.Transfer.App.service;

import com.example.Playlist.Transfer.App.model.ClientInfo;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

@Service
public class AuthService {
    private final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:8080/api/getcode");
    private final SpotifyApi spotifyApi;

    public AuthService() {
        this.spotifyApi = (new SpotifyApi.Builder())
                .setClientId(ClientInfo.CLIENT_ID.getClientInfo())
                .setClientSecret(ClientInfo.CLIENT_SECRET.getClientInfo())
                .setRedirectUri(this.redirectUri)
                .build();
    }

    public RedirectView getAuthCode() {
        AuthorizationCodeUriRequest authorizationCodeUriRequest = this.spotifyApi
                .authorizationCodeUri()
                .scope("playlist-read-private, playlist-read-collaborative")
                .show_dialog(true)
                .build();

        URI uri = authorizationCodeUriRequest.execute();
        String url = uri.toString();
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(url);
        return redirectView;
    }

    public String getAccessCode(String code, HttpServletResponse response) throws IOException {
        AuthorizationCodeRequest authorizationCodeRequest = this.spotifyApi.authorizationCode(code).build();

        try {
            AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();
            this.spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
            this.spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());
            System.out.println("Expires in: " + authorizationCodeCredentials.getExpiresIn());

        } catch (SpotifyWebApiException | ParseException | IOException var5) {
            System.out.println("Error: " + var5.getMessage());
        }

        response.sendRedirect("http://localhost:8080/playlist");
        System.out.println(this.spotifyApi.getAccessToken());
        return this.spotifyApi.getAccessToken();
    }

    public SpotifyApi getSpotifyApi() {
        return this.spotifyApi;
    }
}
