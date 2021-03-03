package com.tw.twjpa.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AlbumDTO {
    private long id;
    private String name;
    private String companyName;
    private String companyAddress;
    private String artistName;
    private Date publishData;
    private List<AlbumVersionDTO> AlbumVersions;
}
