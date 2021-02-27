package com.tw.twjpa.repository;

import com.tw.twjpa.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {

}
