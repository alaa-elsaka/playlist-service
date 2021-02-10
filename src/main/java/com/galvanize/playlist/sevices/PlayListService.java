package com.galvanize.playlist.sevices;

import com.galvanize.playlist.DBUitil.PlayListExistException;
import com.galvanize.playlist.DBUitil.PlayListNameException;
import com.galvanize.playlist.model.PlayList;
import com.galvanize.playlist.model.Song;
import com.galvanize.playlist.repositories.PlayListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayListService {

    @Autowired
    private PlayListRepository repository;

    public PlayList add(PlayList playList) {

        if(playList.getName() == null){
            throw new PlayListNameException("Playlist Name Required!");
        }
        PlayList play = repository.findByName(playList.getName());
        if(play != null)
            throw new PlayListExistException("Playlist with this name already exist");



        return repository.save(playList);
    }

    public PlayList addSongToPlaylist(String playlistName, String songName) {

        PlayList playList = repository.findByName(playlistName);
        Song song = new Song(songName);

        playList.getSongs().add(song);

        return repository.save(playList);

    }
}
