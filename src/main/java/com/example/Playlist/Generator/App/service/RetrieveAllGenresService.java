package com.example.Playlist.Generator.App.service;

import com.example.Playlist.Generator.App.customTags.CatchSpotifyApiExceptions;
import com.example.Playlist.Generator.App.customTags.Log;
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

    @Log
    @CatchSpotifyApiExceptions
    public List<String> getAllGenres(SpotifyApi spotifyApi){
        try{
            final GetAvailableGenreSeedsRequest getAvailableGenreSeedsRequest = spotifyApi
                    .getAvailableGenreSeeds()
                    .build();

            return Arrays.asList(getAvailableGenreSeedsRequest.execute());
        }
        catch(IOException | SpotifyWebApiException | ParseException e){
            return List.of();
        }
    }

}
