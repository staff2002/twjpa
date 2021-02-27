package com.tw.twjpa.controller;

import com.tw.twjpa.mapper.AlbumMapper;
import com.tw.twjpa.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/albums")
public class AlbumController {
    private final AlbumRepository albumRepository;

    @RequestMapping
    public Object findAll(){
        return albumRepository.findAll().stream().map(AlbumMapper.Album_MAPPER::toDTO).collect(Collectors.toList());
    }
}
