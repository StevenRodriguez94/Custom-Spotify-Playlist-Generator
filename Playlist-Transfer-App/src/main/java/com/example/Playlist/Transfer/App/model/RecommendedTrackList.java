package com.example.Playlist.Transfer.App.model;

import lombok.Data;
import org.springframework.stereotype.Component;
import se.michaelthelin.spotify.model_objects.specification.Track;

import java.util.List;

@Component
@Data
public class RecommendedTrackList {

    private List<Track> recommendedTrackList;

    public String[] getTracksUri(){
        String [] uris = new String[recommendedTrackList.size()];
        int i = 0;
        for(Track track: recommendedTrackList){
            uris[i] = track.getUri();
            i ++;
        }
        return uris;
    }

}
