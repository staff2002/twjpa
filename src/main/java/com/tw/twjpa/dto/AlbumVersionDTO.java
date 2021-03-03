package com.tw.twjpa.dto;

import com.tw.twjpa.enums.MediumType;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AlbumVersionDTO {
    private Long id;
    private MediumType mediumType;
    private String versionName;
}
