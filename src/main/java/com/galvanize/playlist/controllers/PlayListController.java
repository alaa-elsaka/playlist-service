package com.galvanize.playlist.controllers;


import com.galvanize.playlist.model.PlayList;
import com.galvanize.playlist.sevices.PlayListService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

}
