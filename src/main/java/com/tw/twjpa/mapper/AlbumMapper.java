package com.tw.twjpa.mapper;

import com.tw.twjpa.dto.AlbumDTO;
import com.tw.twjpa.dto.AlbumVersionDTO;
import com.tw.twjpa.model.Album;
import com.tw.twjpa.model.AlbumVersion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface AlbumMapper {
    AlbumMapper Album_MAPPER = Mappers.getMapper(AlbumMapper.class);

    @Mapping(source = "album.company.companyName", target = "companyName")
    @Mapping(source = "album.company.companyAddress", target = "companyAddress")
    @Mapping(source = "album.artist.name", target = "artistName")
    AlbumDTO toDTO(Album album);

    AlbumVersionDTO toAlbumVersionDTO(AlbumVersion albumVersion);
}
