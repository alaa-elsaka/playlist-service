package com.galvanize.playlist.sevices;

import com.galvanize.playlist.model.PlayList;
import com.galvanize.playlist.repositories.PlayListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayListService {

    @Autowired
    private PlayListRepository repository;

    public PlayList add(PlayList playList) {

        return repository.save(playList);
    }
}
