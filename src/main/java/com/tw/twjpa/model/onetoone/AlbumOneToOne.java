package com.tw.twjpa.model.onetoone;

import com.tw.twjpa.model.AlbumVersion;
import com.tw.twjpa.model.Artist;
import com.tw.twjpa.model.Company;
import com.tw.twjpa.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "album")
public class AlbumOneToOne {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @Embedded
    private Company company;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    //双向
//    @OneToOne(cascade= CascadeType.PERSIST, mappedBy = "album")
//    private AlbumPublishInfoOneToOne albumPublishInfo;

    @OneToOne(cascade= CascadeType.PERSIST)
    @JoinColumn(referencedColumnName = "album_id")
    private AlbumPublishInfoOneToOne albumPublishInfo;

    @Temporal(TemporalType.DATE)
    private Date publishData;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;
}

