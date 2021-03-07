package com.tw.twjpa.controller;

import com.tw.twjpa.enums.MediumType;
import com.tw.twjpa.mapper.AlbumMapper;
import com.tw.twjpa.model.*;
import com.tw.twjpa.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
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

    @RequestMapping("/{id}")
    public Object findById(@PathVariable long id){
        return AlbumMapper.Album_MAPPER.toDTO(albumRepository.findById(id).get());
    }
}
