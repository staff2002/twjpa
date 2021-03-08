package com.tw.twjpa.select_demo;

import com.tw.twjpa.model.Album;
import com.tw.twjpa.repository.AlbumRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PageableAndSort {

    @Autowired
    private AlbumRepository albumRepository;

    @Test
    void should_get_all_album_with_pageable() {
        Pageable pageable = PageRequest.of(1, 2);

        Page<Album> albums = albumRepository.findAll(pageable);

        assertEquals(1, albums.getContent().size());
    }

    @Test
    void should_get_all_album_with_sort_by_name() {
        Sort sort = Sort.by(Sort.Direction.DESC, "name");

        List<Album> albums = albumRepository.findAll(sort);

        assertEquals("Ride the Lightning", albums.get(0).getName());
    }

    @Test
    void should_get_all_album_with_pageable_and_sort_by_name() {
        Pageable pageable = PageRequest.of(1, 2, Sort.by("name"));

        Page<Album> albums = albumRepository.findAll(pageable);

        List<Album> albumList = albums.getContent();
        assertEquals(1, albumList.size());
        assertEquals("Ride the Lightning", albumList.get(0).getName());
    }
}
