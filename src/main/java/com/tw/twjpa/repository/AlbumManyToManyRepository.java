package com.tw.twjpa.repository;

import com.tw.twjpa.model.manytomany.AlbumManyToMany;
import com.tw.twjpa.model.onetomany.AlbumOneToMany;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AlbumManyToManyRepository extends JpaRepository<AlbumManyToMany, Long> {

}
