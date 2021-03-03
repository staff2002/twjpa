package com.tw.twjpa;

import com.tw.twjpa.enums.MediumType;
import com.tw.twjpa.model.*;
import com.tw.twjpa.model.onetoone.AlbumOneToOne;
import com.tw.twjpa.model.onetoone.AlbumPublishInfoOneToOne;
import com.tw.twjpa.repository.AlbumOneToOneRepository;
import com.tw.twjpa.repository.AlbumRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Date;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class JpaTest {
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private AlbumOneToOneRepository albumOneToOneRepository;

    @Test
    public void add(){
        AlbumVersion albumVersion = AlbumVersion.builder().mediumType(MediumType.CD).versionName("首版").build();
        Tag tag = Tag.builder().name("nice").build();
        Album album = Album.builder()
                .albumVersions(Arrays.asList(albumVersion))
                .artist(Artist.builder().id(1).build())
                .company(Company.builder().companyAddress("a").companyName("b").build())
                .name("范特西2")
                .publishData(new Date())
                .tags(Arrays.asList(tag)).build();

//        albumVersion.setAlbumId(album.getId());
        albumVersion.setAlbum(album);

        albumRepository.save(album);
    }

    @Test
    public void addOneToOne(){
        AlbumPublishInfoOneToOne albumPublishInfo = AlbumPublishInfoOneToOne.builder().publisher("发行商1").build();

        AlbumOneToOne album = AlbumOneToOne.builder()
                .albumPublishInfo(albumPublishInfo)
                .artist(Artist.builder().id(1).build())
                .company(Company.builder().companyAddress("a").companyName("b").build())
                .name("范特西oneToOne")
                .publishData(new Date())
                .build();
//        albumPublishInfo.setAlbum(album);

        albumOneToOneRepository.save(album);
    }
}
