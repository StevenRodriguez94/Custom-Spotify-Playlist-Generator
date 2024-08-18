package com.example.Playlist.Generator.App.service;

import com.example.Playlist.Generator.App.customTags.CatchSpotifyApiExceptions;
import com.example.Playlist.Generator.App.customTags.Log;
import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.personalization.simplified.GetUsersTopArtistsRequest;

import java.io.IOException;

@Service
public class RetrieveUsersTopArtistsService {

    private String usersTopArtistsConcat = "";
    private final SpotifyApi spotifyApi;

    @Autowired
    public RetrieveUsersTopArtistsService(AuthService authService) {
        this.spotifyApi = authService.getSpotifyApi();
    }

    @Log
    @CatchSpotifyApiExceptions
    public String getAllUsersTopArtists(){
        try{
            final GetUsersTopArtistsRequest getUsersTopArtistsRequest = spotifyApi.getUsersTopArtists()
                    .limit(5)
//          .offset(0)
                    .time_range("long_term")
                    .build();

            final Paging<Artist> usersTopArtists = getUsersTopArtistsRequest.execute();

            int i = 0;
            for(Artist artist : usersTopArtists.getItems()){
                if (i >= 4)
                    usersTopArtistsConcat += artist.getId();
                else
                    usersTopArtistsConcat += artist.getId() + ",";
                i ++;
            }
            return usersTopArtistsConcat;
        }
        catch(IOException | SpotifyWebApiException | ParseException e){
            return "";
        }
    }
}
