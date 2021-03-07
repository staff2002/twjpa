package com.tw.twjpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "album")
@NamedEntityGraph(
        name = "album-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("albumVersions"),
                @NamedAttributeNode("artist"),
        }
)
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @Embedded
    private Company company;

    @Fetch(FetchMode.JOIN)
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @Temporal(TemporalType.DATE)
    private Date publishData;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    //双向 mapped 配置方式
    @Fetch(FetchMode.JOIN)
    @OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER,mappedBy = "album")
    List<AlbumVersion> albumVersions;

    //双向 mapped 配置方式
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY,mappedBy = "album")
    List<Track> tracks;

    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinTable(
            name = "album_tag_relation",
            joinColumns = @JoinColumn(name = "album_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id")
    )
    private List<Tag> tags;
}

