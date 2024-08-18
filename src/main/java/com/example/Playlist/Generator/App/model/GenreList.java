package com.example.Playlist.Generator.App.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class GenreList {

    private List<String> genres;

}
