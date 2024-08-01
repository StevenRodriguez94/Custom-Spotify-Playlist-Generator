package com.example.Playlist.Transfer.App.controller;

import com.example.Playlist.Transfer.App.service.AuthService;
import com.example.Playlist.Transfer.App.service.PlaylistRetrievalService;
import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Image;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;
import se.michaelthelin.spotify.requests.data.playlists.GetListOfCurrentUsersPlaylistsRequest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

//import static com.example.Playlist.Transfer.App.Demo.service.AuthService.spotifyApi;

@Controller
@RequestMapping("/playlist")
public class PlaylistController {
    //private final AuthService authService;
    private final PlaylistRetrievalService playlistRetrievalService;
    private final SpotifyApi spotifyApi;

//    @GetMapping
//    public String get(Model model){
//        model.addAttribute("imageUrl", "https://image-cdn-ak.spotifycdn.com/image/ab67706c0000da847cdb4f19682c1f74db24df29");
//        return "retrievePlaylists";
//    }

    @Autowired
    public PlaylistController(AuthService authService, PlaylistRetrievalService playlistRetrievalService) {
        //this.authService = authService;
        this.spotifyApi = authService.getSpotifyApi();
        this.playlistRetrievalService = playlistRetrievalService;
    }

    @GetMapping()
    public String showPlaylists(Model model){

//        try{
//
//            final GetListOfCurrentUsersPlaylistsRequest getListOfCurrentUsersPlaylistsRequest = spotifyApi
//                    .getListOfCurrentUsersPlaylists()
//                    //.limit(10)
//                    .build();
//
//            final Paging<PlaylistSimplified> playlistSimplifiedPaging = getListOfCurrentUsersPlaylistsRequest.execute();
//
//            System.out.println("Total: " + playlistSimplifiedPaging.getTotal());
//
//            List<PlaylistSimplified> playlists = Arrays.asList(playlistSimplifiedPaging.getItems());
////            for(PlaylistSimplified pl : playlists){
////                String om = pl.getImages()[0].getUrl();
////                System.out.println(pl.getName() + ", by " + pl.getOwner().getDisplayName() + ", " + Arrays.toString(pl.getImages()));
////                System.out.println("________________________________________");
////            }
//            model.addAttribute("playlists", playlists);
//            model.addAttribute("title", "List of Playlists");
//
//            return "retrievePlaylists";
//
//        }
//        catch(IOException | SpotifyWebApiException | ParseException e){
//            System.out.println("Error: " + e.getMessage());
//        }
//
//        return null;

        List<PlaylistSimplified> playlists = playlistRetrievalService.retrievePlaylistList(spotifyApi);
        model.addAttribute("playlists", playlists);
        model.addAttribute("title", "List of Playlists");
        return "retrievePlaylists";
    }

}
