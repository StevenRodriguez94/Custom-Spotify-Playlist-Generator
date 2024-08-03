package com.example.Playlist.Transfer.App.controller;

import com.example.Playlist.Transfer.App.model.GenreList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/playlist/genre")
public class GenreController {

    private final GenreList genreList;
    private List<String> genres;

    @Autowired
    public GenreController(GenreList genreList) {
        this.genreList = genreList;
    }

    @GetMapping()
    public String getGenreSelectionView(Model model){
        genres = genreList.getGenres();
        model.addAttribute("title", "Bruh");
        model.addAttribute("genres", genres);
        //System.out.println("bruh " + genres);
        return "genreRelatedActions/selectGenre";
    }

}
