package com.galvanize.playlist.DBUitil;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PlayListExistException extends RuntimeException{

    public PlayListExistException(String message){
        super((message));
    }
}
