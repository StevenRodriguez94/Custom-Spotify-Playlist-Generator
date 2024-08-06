package com.example.Playlist.Transfer.App.service;

import com.example.Playlist.Transfer.App.model.RecommendedTrackList;
import com.neovisionaries.i18n.CountryCode;
import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Recommendations;
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

    @Autowired
    public RecommendationService(RecommendedTrackList recommendedTrackList) {
        this.recommendedTrackList = recommendedTrackList;
    }

    public List<Track> getRecommendedTracksGenre(SpotifyApi spotifyApi, int limit, String genre){
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
}
