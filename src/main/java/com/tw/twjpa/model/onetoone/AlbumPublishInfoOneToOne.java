package com.tw.twjpa.model.onetoone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "album_publish_info")
public class AlbumPublishInfoOneToOne {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //双向
    @OneToOne
    @JoinColumn(name= "album_id")
    private AlbumOneToOne album;

    private String publisher;
}
