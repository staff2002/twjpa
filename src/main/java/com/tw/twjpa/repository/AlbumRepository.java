package com.tw.twjpa.repository;

import com.tw.twjpa.model.Album;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AlbumRepository extends JpaRepository<Album, Long> {
//    @EntityGraph("album-entity-graph")
    @Override
    List<Album> findAll();
}
