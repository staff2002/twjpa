package com.tw.twjpa.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="track")
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="album_id")
    private Album album;

    private String title;
}
