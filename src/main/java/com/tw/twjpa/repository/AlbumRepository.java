package com.tw.twjpa.repository;

import com.tw.twjpa.model.Album;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


public interface AlbumRepository extends JpaRepository<Album, Long> {
    @EntityGraph("album-entity-graph")
    @Override
    List<Album> findAll();

    List<Album> findByPublishDataBetween(Date startDate, Date endDate);

    List<Album> findByIdGreaterThan(Long id);

    List<Album> findByNameLessThanEqual(String name);

    List<Album> findByPublishDataBefore(Date date);

    Album findByNameAndCompany_CompanyName(String name, String companyName);

    Album findTop1ByNameStartingWith(String name);

    @Query("select a from Album a where a.name like ?1%")
    Album getAlbumWithNameLike(String name);

    @Query(value = "select * from album where name like ?1%", nativeQuery = true)
    Album getAlbumWithNameLikeUseSql(String name);

}
