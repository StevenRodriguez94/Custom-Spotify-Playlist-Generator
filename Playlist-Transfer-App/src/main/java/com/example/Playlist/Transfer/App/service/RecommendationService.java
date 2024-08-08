package com.example.Playlist.Transfer.App.service;

import com.example.Playlist.Transfer.App.model.RecommendedTrackList;
import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.browse.GetRecommendationsRequest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.neovisionaries.i18n.CountryCode.US;

@Service
public class RecommendationService {

    private GetRecommendationsRequest getRecommendationsRequest;
    private final RecommendedTrackList recommendedTrackList;
    private final RetrieveUsersTopArtistsService retrieveUsersTopArtistsService;
    private final SpotifyApi spotifyApi;

    @Autowired
    public RecommendationService(RecommendedTrackList recommendedTrackList,
                                 RetrieveUsersTopArtistsService retrieveUsersTopArtistsService,
                                 AuthService authService) {
        this.spotifyApi = authService.getSpotifyApi();
        this.recommendedTrackList = recommendedTrackList;
        this.retrieveUsersTopArtistsService = retrieveUsersTopArtistsService;
    }

    public List<Track> getRecommendedTracksGenre(int limit, String genre){
        try {
            getRecommendationsRequest = spotifyApi.getRecommendations()
                    .limit(limit)
                    .seed_genres(genre)
                    .max_popularity(100)
                    .min_popularity(20)
                    .market(US)
                    .build();

            recommendedTrackList.setRecommendedTrackList(Arrays.asList(getRecommendationsRequest.execute().getTracks()));
            System.out.println("Amount of tracks: " + recommendedTrackList.getRecommendedTrackList().size());
            return recommendedTrackList.getRecommendedTrackList();

        }catch(IOException | SpotifyWebApiException | ParseException e){
            System.out.println("Error: " + e.getMessage());
        }

        return List.of();
    }

    public List<Track> getRecommendedTracksBpm(int limit, Float bpm, String genreConcat){
        try {
            getRecommendationsRequest = spotifyApi.getRecommendations()
                    .limit(limit)
                    .seed_genres(genreConcat)
                    .target_tempo(bpm)
                    .max_popularity(100)
                    .min_popularity(20)
                    .market(US)
                    .build();

            recommendedTrackList.setRecommendedTrackList(Arrays.asList(getRecommendationsRequest.execute().getTracks()));
            System.out.println("Amount of tracks: " + recommendedTrackList.getRecommendedTrackList().size());
           // System.out.println("Bpm of first: " + recommendedTrackList.getRecommendedTrackList().get(0));
            return recommendedTrackList.getRecommendedTrackList();

        }catch(IOException | SpotifyWebApiException | ParseException e){
            System.out.println("Error: " + e.getMessage());
        }

        return List.of();
    }

    public List<Track> getRecommendedTracksUsersTopArtists(int limit){
        try {

            getRecommendationsRequest = spotifyApi.getRecommendations()
                    .limit(limit)
                    .seed_artists(retrieveUsersTopArtistsService.getAllUsersTopArtists())
                    .market(US)
                    .build();

            recommendedTrackList.setRecommendedTrackList(Arrays.asList(getRecommendationsRequest.execute().getTracks()));
            System.out.println("Amount of tracks: " + recommendedTrackList.getRecommendedTrackList().size());
            return recommendedTrackList.getRecommendedTrackList();

        }catch(IOException | SpotifyWebApiException | ParseException e){
            System.out.println("Errorll: " + e.getMessage());
        }

        return List.of();


    }
}
