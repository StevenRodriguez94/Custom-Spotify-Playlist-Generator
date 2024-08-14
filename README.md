# **Custom Spotify Playlist Generator**
Custom Spotify Playlist Generator that generates a playlist in the user's Spotify Account based on a specific selected attribute such as by genre or tempo. 

## **Description**
  This Project let's the user generate different playlists with many options, but in order to do that the application has to primarily request permission from the user. This is done by using OAuth2.0 to access the Spotify API. The application implements the Authorization Code Flow from the OAuth2.0 standard in order to request an access token, which the application then use for the different requests to the API. After retrieving the access token, the application provides the user with multiple options, each being one attribute that all the playlist's songs will share. For now, there are three options provided to the user which are by Genre, by Tempo, and by the Users Top 5 Artists. 

  ### **By Genre**
  - After the user selects this option, they will be taken into another page which has a cardview in the middle of the screen. This view gives the user different options that have to be selected to continue. In these options, there is a dropdown for choosing the specific genre that the user wants for the playlist, taken from the list of all available genres that Spotify provides. Next to the dropdown is a collection of radio buttons, which makes the user choose the amount of songs that they want for the playlist, from a minimum of 5 to a maximum of 100 songs. The default option is 5. Finally, we have a textfield, for the user to enter the Playlist Name. For now, there are no restrictions to the length of the playlist name or any other specific restrictions that Spotify enforces for the playlist name. However, the user cannot leave this field empty. After all options are selected, the user can press the Generate button below to generate the playlist with the selected parameters. The application then prompts the user to go to their Spotify Account and verify that the playlist has been successfully created.
    
  ### **By Tempo**
  - After the user selects this option, they will be taken into another page which has a cardview in the middle of the screen. This view gives the user different options that have to be selected to continue. In these options, there is a number field, where the number value ranges from a minimum of 60 to a maximum of 250 (I will increase the tempo maximum in the future, need more testing to avoid some specific problems). This number represents the tempo that the songs of the playlist should have. Next to the dropdown is a collection of radio buttons, which makes the user choose the amount of songs that they want for the playlist, from a minimum of 5 to a maximum of 100 songs. The default option is 5. Finally, we have a textfield, for the user to enter the Playlist Name. For now, there are no restrictions to the length of the playlist name or any other specific restrictions that Spotify enforces for the playlist name. However, the user cannot leave this field empty. After all options are selected, the user can press the Generate button below to generate the playlist with the selected parameters. The application then prompts the user to go to their Spotify Account and verify that the playlist has been successfully created.
    
  ### **By Users Top Artists**
  - After the user selects this option, they will be taken into another page which has a cardview in the middle of the screen. This view gives the user different options that have to be selected to continue. This option is different than the others in that the user doesnt have to select a specific artist, the top artists data is already pulled from the Spotify API. Although, this option still shares the other two options, the radio buttons for the amount of songs, and the playlist name textfield. After these two fields are selected, the user can press the Generate button below to generate the playlist with the selected parameters. The application then prompts the user to go to their Spotify Account and verify that the playlist has been successfully created.

## **Additional Information**
  In order for this project to work, the developer needs to generate a client id and client secret by following the instructions from the [Spotify for Developers Website](https://developer.spotify.com). This project has an enum class not in the repository which contains these details. They should NOT be visible for anyone to see. This information is used in the authorization process of the application, so it is important to set it up first before delving into any similar application that uses the Spotify API specifically. 

## **Tools Used**
  Java/Spring Boot, Thymeleaf, Bootstrap, HTML/CSS.

## Authors
[@Steven Rodriguez](https://github.com/StevenRodriguez94)
