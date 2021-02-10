package com.galvanize.playlist;

import com.galvanize.playlist.DBUitil.PlayListExistException;
import com.galvanize.playlist.DBUitil.PlayListNameException;
import com.galvanize.playlist.model.PlayList;
import com.galvanize.playlist.model.Song;
import com.galvanize.playlist.repositories.PlayListRepository;
import com.galvanize.playlist.sevices.PlayListService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

    @Test
    public void addNewPlayAlreadyExist() {

        PlayList playlist = new PlayList("myPlayList");

        when(playListRepository.findByName(playlist.getName()))
                .thenThrow(new PlayListExistException("Playlist with this name already exist"));

        PlayListExistException exception =
                assertThrows(PlayListExistException.class, ()->{
                    playListService.add(playlist);
                });

        assertEquals("Playlist with this name already exist", exception.getMessage());
        verify(playListRepository,times(1)).findByName(playlist.getName());

    }



    @Test
    public void createPlaylistWithNoName() {

        PlayList playlist = new PlayList();

//        when(playListRepository.findByName(playlist.getName()))
//                .thenReturn(playlist);

        PlayListNameException exception =
                assertThrows(PlayListNameException.class, ()->{
                    playListService.add(playlist);
                });

        assertEquals("Playlist Name Required!", exception.getMessage());

    }

    @Test
    public void whenAddSongToPlayList()
    {
        PlayList playList = new PlayList("myPlaylist");

        when(playListRepository.findByName("myPlaylist")).thenReturn(playList);
        when(playListRepository.save(playList)).thenReturn(playList);

        Song song = new Song("mySong");


        PlayList actual = playListService.addSongToPlaylist(playList.getName(), song.getName());

        assertEquals("mySong", actual.getSongs().get(0).getName());
    }



}
