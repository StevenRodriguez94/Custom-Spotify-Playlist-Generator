package com.example.Playlist.Transfer.App.service;

import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;
import se.michaelthelin.spotify.requests.data.playlists.GetListOfCurrentUsersPlaylistsRequest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class PlaylistRetrievalService {

    public List<PlaylistSimplified> retrievePlaylistList(SpotifyApi spotifyApi){
        try{

            final GetListOfCurrentUsersPlaylistsRequest getListOfCurrentUsersPlaylistsRequest = spotifyApi
                    .getListOfCurrentUsersPlaylists()
                    //.limit(10)
                    .build();

            final Paging<PlaylistSimplified> playlistSimplifiedPaging = getListOfCurrentUsersPlaylistsRequest.execute();
            System.out.println("Total: " + playlistSimplifiedPaging.getTotal());

            return Arrays.asList(playlistSimplifiedPaging.getItems());
        }
        catch(IOException | SpotifyWebApiException | ParseException e){
            System.out.println("Error: " + e.getMessage());
        }
        return List.of();
    }
}
