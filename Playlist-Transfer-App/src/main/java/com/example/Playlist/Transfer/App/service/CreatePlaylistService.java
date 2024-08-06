package com.example.Playlist.Transfer.App.service;

import com.example.Playlist.Transfer.App.model.RecommendedTrackList;
import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.special.SnapshotResult;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.User;
import se.michaelthelin.spotify.requests.data.playlists.AddItemsToPlaylistRequest;
import se.michaelthelin.spotify.requests.data.playlists.CreatePlaylistRequest;
import se.michaelthelin.spotify.requests.data.users_profile.GetCurrentUsersProfileRequest;

import java.io.IOException;

@Service
public class CreatePlaylistService {

    private final RecommendedTrackList recommendedTrackList;
    private final SpotifyApi spotifyApi;
    private String userName;
    private String userId;

    @Autowired
    public CreatePlaylistService(RecommendedTrackList recommendedTrackList, AuthService authService) {
        this.recommendedTrackList = recommendedTrackList;
        this.spotifyApi = authService.getSpotifyApi();
    }

    private void retrieveUserInfo(){
        final GetCurrentUsersProfileRequest getCurrentUsersProfileRequest = spotifyApi.getCurrentUsersProfile()
                .build();
        try{
            final User user = getCurrentUsersProfileRequest.execute();
            userId = user.getId();
            userName = user.getDisplayName();
        }
        catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public boolean createPlaylist(String playlistName){
        retrieveUserInfo();

        final CreatePlaylistRequest createPlaylistRequest = spotifyApi
                .createPlaylist(userId, userName)
                .name(playlistName)
                //.public_(false)
//          .description("Amazing music.")
                .build();

        try {
            final Playlist playlist = createPlaylistRequest.execute();
            System.out.println("Name: " + playlist.getName());
            setTracksForPlaylist(playlist.getId());
            return true;

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
    private void setTracksForPlaylist(String playlistId){
        final AddItemsToPlaylistRequest addItemsToPlaylistRequest = spotifyApi
                .addItemsToPlaylist(playlistId, recommendedTrackList.getTracksUri())
//          .position(0)
                .build();

        try {
            final SnapshotResult snapshotResult = addItemsToPlaylistRequest.execute();

            //System.out.println("Snapshot ID: " + snapshotResult.getSnapshotId());

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
