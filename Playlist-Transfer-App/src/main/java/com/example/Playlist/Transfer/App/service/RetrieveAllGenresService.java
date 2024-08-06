package com.example.Playlist.Transfer.App.service;

import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.browse.miscellaneous.GetAvailableGenreSeedsRequest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class RetrieveAllGenresService {

    public List<String> getAllGenres(SpotifyApi spotifyApi){
        try{
            final GetAvailableGenreSeedsRequest getAvailableGenreSeedsRequest = spotifyApi
                    .getAvailableGenreSeeds()
                    .build();

            final List<String> genres = Arrays.asList(getAvailableGenreSeedsRequest.execute());
            System.out.println("Length: " + genres.size());
            System.out.println("that " + genres);
            return genres;
        }
        catch(IOException | SpotifyWebApiException | ParseException e){
            System.out.println("Error: " + e.getMessage());
        }
        return List.of();
    }

}
