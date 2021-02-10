package com.galvanize.playlist.controllers;


import com.galvanize.playlist.model.PlayList;
import com.galvanize.playlist.model.Song;
import com.galvanize.playlist.sevices.PlayListService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PlayListController {

    private final PlayListService playListService;

    public PlayListController(PlayListService playListService){
        this.playListService = playListService;
    }


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public PlayList addPlayList(@RequestBody PlayList playList){

        return playListService.add(playList);


    }

    @PostMapping("playlist/add/{playlistName}/{songName}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PlayList addSongToPlaylist(@PathVariable String playlistName, @PathVariable String songName){

        return playListService.addSongToPlaylist(playlistName, songName);
    }

    @PostMapping("playlist/remove/{playlistName}/{songName}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PlayList removeSongFromPlaylist(@PathVariable String playlistName, @PathVariable String songName){

        return playListService.removeSongFromPlaylist(playlistName, songName);
    }

    @GetMapping("playlist/{playlistName}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Song> getSongs(@PathVariable String playlistName){

        return playListService.getAllSongs(playlistName);
    }
}
