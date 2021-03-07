package com.tw.twjpa.service;

import com.tw.twjpa.model.manytomany.AlbumManyToMany;
import com.tw.twjpa.model.manytomany.TagManyToMany;
import com.tw.twjpa.model.onetomany.AlbumOneToMany;
import com.tw.twjpa.model.onetomany.AlbumVersionManyToOne;
import com.tw.twjpa.repository.AlbumManyToManyRepository;
import com.tw.twjpa.repository.AlbumOneToManyRepository;
import com.tw.twjpa.repository.AlbumVersionManyToOneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AlbumManyToManyService {
    private final AlbumManyToManyRepository albumManyToManyRepository;

    @Transactional
    public void save_new_tag_and_relation(){
        AlbumManyToMany album = albumManyToManyRepository.getOne(1L);
        List<TagManyToMany> tags = album.getTags();
        TagManyToMany singleTag = TagManyToMany.builder().name("单曲").build();
        tags.add(singleTag);
        albumManyToManyRepository.save(album);
    }
}
