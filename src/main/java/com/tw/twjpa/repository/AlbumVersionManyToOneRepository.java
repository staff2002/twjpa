package com.tw.twjpa.repository;

import com.tw.twjpa.model.onetomany.AlbumVersionManyToOne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface AlbumVersionManyToOneRepository extends JpaRepository<AlbumVersionManyToOne,Long> {

    @Modifying
    @Query(value = "delete from album_version where album_id = :albumId",nativeQuery = true)
    int deleteByAlbumId(@Param("albumId") long albumId);
}
