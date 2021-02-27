package com.tw.twjpa.model;

import com.tw.twjpa.enums.MediumType;
import lombok.AllArgsConstructor;
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
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "album_version")
public class AlbumVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="album_id")
    private long albumId;

    private String versionName;

    @Enumerated(EnumType.STRING)
    private MediumType mediumType;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    @OneToMany
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "record_version_id")
    List<VersionImage> versionImages;
}
