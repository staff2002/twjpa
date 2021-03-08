package com.tw.twjpa.select_demo;

import com.tw.twjpa.model.Album;
import com.tw.twjpa.repository.AlbumRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class QueryName {

    @Autowired
    private AlbumRepository albumRepository;

    private static final ZoneId DEFAULT_TIME_ZONE = ZoneId.of("Asia/Shanghai");

    @Test
    void should_get_album_with_name_is_Ride_the_Lightning_and_company_name_is_Vertigo() {
        // Todo
        Album album = albumRepository.findByNameAndCompany_CompanyName("Ride the Lightning", "Vertigo");

        assertEquals("Ride the Lightning", album.getName());
        assertEquals("Vertigo", album.getCompany().getCompanyName());
    }

    @Test
    void should_get_album_with_publish_data_between_1996_and_2021() {
        Date startDate = toDate(1996);
        Date endDate = toDate(2021);

        // Todo
        List<Album> albums = albumRepository.findByPublishDataBetween(startDate, endDate);

        assertEquals(1, albums.size());
    }

    @Test
    void should_get_album_with_id_greater_than_2() {

        // Todo
        List<Album> albums = albumRepository.findByIdGreaterThan(2L);

        assertEquals(1, albums.size());
    }

    @Test
    void should_get_album_with_name_less_then_equals_Hotel_California() {

        // Todo
        List<Album> albums = albumRepository.findByNameLessThanEqual("Hotel California");

        assertEquals(2, albums.size());
    }

    @Test
    void should_get_album_with_publish_date_before_1996() {
        Date date = toDate(1996);

        // Todo
        List<Album> albums = albumRepository.findByPublishDataBefore(date);

        assertEquals(2, albums.size());
    }

    @Test
    void should_get_first_album_with_name_start_with_ride() {
        // Todo
        Album album = albumRepository.findTop1ByNameStartingWith("ride");

        assertEquals("Ride the Lightning", album.getName());
    }

    @Test
    void should_get_first_album_with_name_like_ride() {
        // Todo
        Album album = albumRepository.getAlbumWithNameLike("Ride");

        assertEquals("Ride the Lightning", album.getName());
    }

    @Test
    void should_get_first_album_with_name_like_ride_native_query_is_true() {
        // Todo
        Album album = albumRepository.getAlbumWithNameLikeUseSql("Ride");

        assertEquals("Ride the Lightning", album.getName());
    }


    private static Date toDate(Integer year) {
        LocalDate localDate = LocalDate.of(year, 1, 1);
        return Date.from(localDate.atStartOfDay(DEFAULT_TIME_ZONE).toInstant());
    }
}
