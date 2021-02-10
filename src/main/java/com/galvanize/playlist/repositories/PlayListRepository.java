package com.galvanize.playlist.repositories;

import com.galvanize.playlist.model.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayListRepository extends JpaRepository<PlayList, Long> {
}
