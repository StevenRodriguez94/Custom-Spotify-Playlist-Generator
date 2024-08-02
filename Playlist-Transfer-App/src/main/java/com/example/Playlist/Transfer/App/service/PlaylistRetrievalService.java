package com.example.Playlist.Transfer.App.service;

import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;
import se.michaelthelin.spotify.model_objects.specification.PlaylistTrack;
import se.michaelthelin.spotify.requests.data.playlists.GetListOfCurrentUsersPlaylistsRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistsItemsRequest;

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
    public List<PlaylistTrack> retrieveTrackList(SpotifyApi spotifyApi, String playlistId){
        try{
            final GetPlaylistsItemsRequest getPlaylistsItemsRequest = spotifyApi
                    .getPlaylistsItems(playlistId)
                    .build();

            final Paging<PlaylistTrack> playlistTrackPaging = getPlaylistsItemsRequest.execute();
            System.out.println("Total " + playlistTrackPaging.getTotal());
            return Arrays.asList(playlistTrackPaging.getItems());
        }
        catch(IOException | SpotifyWebApiException | ParseException e){
            System.out.println("Error: " + e.getMessage());
        }
        return List.of();
    }
}
