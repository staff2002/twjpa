package com.tw.twjpa.model.onetomany;

import com.tw.twjpa.enums.MediumType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "album_version")
public class AlbumVersionOneToMany {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    //双向，如果album中没有用mapped by，并且save的时候没有设置album，会报album_id不能为空。设置alum后，insert后，还会update。并不是好的做法
    //正确做法应该在一方设置mapped by
    @ManyToOne
    @JoinColumn(name = "album_id")
    private AlbumOneToMany album;

    // 单向
//    @Column(name="album_id")
//    private long albumId;

    private String versionName;

    @Enumerated(EnumType.STRING)
    private MediumType mediumType;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

}
