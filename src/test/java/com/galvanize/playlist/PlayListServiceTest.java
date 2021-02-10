package com.galvanize.playlist;

import com.galvanize.playlist.model.PlayList;
import com.galvanize.playlist.repositories.PlayListRepository;
import com.galvanize.playlist.sevices.PlayListService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlayListServiceTest {

    @Mock
    private PlayListRepository playListRepository;


    @InjectMocks
    private PlayListService playListService;

    @Test
    public void addPlayList(){
        PlayList playlist = new PlayList("myPlayList");

        when(playListRepository.save(any(PlayList.class))).thenReturn(playlist);

        PlayList actual = playListService.add(playlist);

        assertTrue(actual.getName().equals(playlist.getName()));

    }
}
