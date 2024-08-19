package com.example.Playlist.Generator.App.service;

import com.example.Playlist.Generator.App.customTags.CatchSpotifyApiExceptions;
import com.example.Playlist.Generator.App.customTags.Log;
import com.example.Playlist.Generator.App.model.RecommendedTrackList;
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

    @Log
    @CatchSpotifyApiExceptions
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
            //System.out.println("Amount of tracks: " + recommendedTrackList.getRecommendedTrackList().size());
            return recommendedTrackList.getRecommendedTrackList();

        }catch(IOException | SpotifyWebApiException | ParseException e){
            return List.of();
        }
    }

    @Log
    @CatchSpotifyApiExceptions
    public List<Track> getRecommendedTracksBpm(int limit, Float bpm, String genreConcat){
        try {
            getRecommendationsRequest = spotifyApi.getRecommendations()
                    .limit(limit)
                    .seed_genres("pop, edm, hip-hop, rock, latino")
                    .max_tempo(bpm+3)
                    .target_tempo(bpm)
                    .min_tempo(bpm-3)
                    .max_popularity(100)
                    .min_popularity(10)
                    .market(US)
                    .build();

            recommendedTrackList.setRecommendedTrackList(Arrays.asList(getRecommendationsRequest.execute().getTracks()));
            //System.out.println("Amount of tracks: " + recommendedTrackList.getRecommendedTrackList().size());
            return recommendedTrackList.getRecommendedTrackList();

        }catch(IOException | SpotifyWebApiException | ParseException e){
            return List.of();
        }
    }

    @Log
    @CatchSpotifyApiExceptions
    public List<Track> getRecommendedTracksUsersTopArtists(int limit){
        try {

            getRecommendationsRequest = spotifyApi.getRecommendations()
                    .limit(limit)
                    .seed_artists(retrieveUsersTopArtistsService.getAllUsersTopArtists())
                    .market(US)
                    .build();

            recommendedTrackList.setRecommendedTrackList(Arrays.asList(getRecommendationsRequest.execute().getTracks()));
            //System.out.println("Amount of tracks: " + recommendedTrackList.getRecommendedTrackList().size());
            return recommendedTrackList.getRecommendedTrackList();

        }catch(IOException | SpotifyWebApiException | ParseException e){
            return List.of();
        }

    }

    @Log
    @CatchSpotifyApiExceptions
    public List<Track> getRecommendedTracksSongLength(int limit, int minutes){
        try {
            int songDuration = (minutes * 1000 * 60); //+ (seconds * 1000);
            int songDurationMax = ((minutes + 1) * 1000 * 60) - 1000;

            getRecommendationsRequest = spotifyApi.getRecommendations()
                    .limit(limit)
                    .seed_genres("pop, edm, hip-hop, rock, latino")
                    .max_popularity(100)
                    //.min_popularity(10)
                    //.target_duration_ms(songDuration)
                    .max_duration_ms(songDurationMax)
                    .min_duration_ms(songDuration)
                    .market(US)
                    .build();

            recommendedTrackList.setRecommendedTrackList(Arrays.asList(getRecommendationsRequest.execute().getTracks()));
            System.out.println("Amount of tracks: " + recommendedTrackList.getRecommendedTrackList().size());
            return recommendedTrackList.getRecommendedTrackList();

        }catch(IOException | SpotifyWebApiException | ParseException e){
            return List.of();
        }
    }
}
