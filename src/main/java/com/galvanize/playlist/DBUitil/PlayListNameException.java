package com.galvanize.playlist.DBUitil;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PlayListNameException extends RuntimeException{

    public PlayListNameException(String message){
        super(message);
    }
}
