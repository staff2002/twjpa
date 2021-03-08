package com.tw.twjpa.service;

import com.tw.twjpa.model.onetomany.AlbumOneToMany;
import com.tw.twjpa.model.onetomany.AlbumVersionManyToOne;
import com.tw.twjpa.repository.AlbumOneToManyRepository;
import com.tw.twjpa.repository.AlbumVersionManyToOneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AlbumOneToManyService {
    private final AlbumOneToManyRepository albumOneToManyRepository;
    private final AlbumVersionManyToOneRepository albumVersionManyToOneRepository;

    @Transactional
    public void remove_from_List(){
        AlbumOneToMany albumOneToMany = albumOneToManyRepository.findById(100L).get();
        AlbumVersionManyToOne deletedAlbumVersion = albumOneToMany.getAlbumVersions().get(0);
        albumOneToMany.getAlbumVersions().remove(deletedAlbumVersion);
        albumOneToManyRepository.save(albumOneToMany);
    }

    @Transactional
    public void delete_many(){
        AlbumOneToMany albumOneToMany = albumOneToManyRepository.findById(100L).get();
        AlbumVersionManyToOne deletedAlbumVersion = albumOneToMany.getAlbumVersions().get(0);
        albumOneToMany.getAlbumVersions().remove(deletedAlbumVersion);
        albumVersionManyToOneRepository.delete(deletedAlbumVersion);
    }



    @Transactional
    public void orphanRemoval_remove_many_save_one(){
        AlbumOneToMany albumOneToMany = albumOneToManyRepository.findById(100L).get();
        AlbumVersionManyToOne deletedAlbumVersion = albumOneToMany.getAlbumVersions().get(0);
        albumOneToMany.getAlbumVersions().remove(deletedAlbumVersion);
        albumOneToManyRepository.save(albumOneToMany);
    }
}
