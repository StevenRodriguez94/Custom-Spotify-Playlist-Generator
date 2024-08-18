package com.example.Playlist.Generator.App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy()
public class PlaylistTransferAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlaylistTransferAppApplication.class, args);
	}

}
