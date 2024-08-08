package com.example.Playlist.Transfer.App.service;

import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.browse.miscellaneous.GetAvailableGenreSeedsRequest;
import se.michaelthelin.spotify.requests.data.personalization.simplified.GetUsersTopArtistsRequest;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class RetrieveUsersTopArtistsService {

    private String usersTopArtistsConcat = "";
    private final SpotifyApi spotifyApi;

    @Autowired
    public RetrieveUsersTopArtistsService(AuthService authService) {
        this.spotifyApi = authService.getSpotifyApi();
    }

    public String getAllUsersTopArtists(){
        try{
            final GetUsersTopArtistsRequest getUsersTopArtistsRequest = spotifyApi.getUsersTopArtists()
                    .limit(5)
//          .offset(0)
                    .time_range("long_term")
                    .build();

            final Paging<Artist> usersTopArtists = getUsersTopArtistsRequest.execute();

            System.out.println("Length: " + usersTopArtists.getTotal());
            int i = 0;
            for(Artist artist : usersTopArtists.getItems()){
                if (i >= 4)
                    usersTopArtistsConcat += artist.getId();
                else
                    usersTopArtistsConcat += artist.getId() + ",";
                i ++;
            }
            System.out.println(usersTopArtistsConcat);

            return usersTopArtistsConcat;
        }
        catch(IOException | SpotifyWebApiException | ParseException e){
            System.out.println("Error''': " + e.getMessage());
        }
        return "";
    }
}
