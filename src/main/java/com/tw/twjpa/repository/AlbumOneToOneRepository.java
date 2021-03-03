package com.tw.twjpa.repository;

import com.tw.twjpa.model.onetoone.AlbumOneToOne;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AlbumOneToOneRepository extends JpaRepository<AlbumOneToOne, Long> {

}
