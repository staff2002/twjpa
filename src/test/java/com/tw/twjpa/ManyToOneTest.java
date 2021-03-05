package com.tw.twjpa;

import com.tw.twjpa.model.Artist;
import com.tw.twjpa.model.Company;
import com.tw.twjpa.model.onetoone.AlbumOneToOne;
import com.tw.twjpa.model.onetoone.AlbumPublishInfoOneToOne;
import com.tw.twjpa.repository.AlbumOneToOneRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@SpringBootTest
public class ManyToOneTest {
    @Autowired
    private AlbumOneToOneRepository albumOneToOneRepository;

    //1、不设置 artist id，设置artist name
    //1.1 不设置级连，album 保存失败
    //1.2 设置级连，album 保存成功，并且artist也会被保存
    @Test
    public void when_not_pass_artist_id(){
        AlbumPublishInfoOneToOne albumPublishInfo = AlbumPublishInfoOneToOne.builder().publisher("发行商1").build();

        AlbumOneToOne album = AlbumOneToOne.builder()
                .albumPublishInfo(albumPublishInfo)
                .artist(Artist.builder().name("周杰伦").build())
                .company(Company.builder().companyAddress("a").companyName("b").build())
                .name("范特西1")
                .publishData(new Date())
                .build();
        albumPublishInfo.setAlbum(album);
        albumOneToOneRepository.save(album);
    }

    //2、设置artist id
    //结果：
    //2.1 不设置级连,album保存成功
    //2.2 设置了级连，保存失败. 错误信息：detached entity passed to persist
    //分析：由于设置了id，jpa认为 这个对象不是new而是detach
    @Test
    public void when_pass_artist_id(){
        AlbumPublishInfoOneToOne albumPublishInfo = AlbumPublishInfoOneToOne.builder().publisher("发行商1").build();

        AlbumOneToOne album = AlbumOneToOne.builder()
                .albumPublishInfo(albumPublishInfo)
                .artist(Artist.builder().id(1).build())
                .company(Company.builder().companyAddress("a").companyName("b").build())
                .name("范特西2")
                .publishData(new Date())
                .build();
        albumPublishInfo.setAlbum(album);
        albumOneToOneRepository.save(album);
    }

    //3、设置artist id,name
    //3.1 不设置级连,  保存album成功，不会更新id为1的artist，也不会报错。
    //3.2 设置级连, 保存失败. 原因是 Artist 对象并没有被 JPA 托管，错误信息：detached entity passed to persist
    @Test
    public void when_pass_artist_id_and_name(){
        AlbumPublishInfoOneToOne albumPublishInfo = AlbumPublishInfoOneToOne.builder().publisher("发行商1").build();

        AlbumOneToOne album = AlbumOneToOne.builder()
                .albumPublishInfo(albumPublishInfo)
                .artist(Artist.builder().id(1).name("oasis").build())
                .company(Company.builder().companyAddress("a").companyName("b").build())
                .name("范特西3")
                .publishData(new Date())
                .build();
        albumPublishInfo.setAlbum(album);
        albumOneToOneRepository.save(album);
    }

    //4、先查询，再修改artist name再保存
    //结果：修改级连保存成功
    @Test
    public void get_then_modify_artist_save(){
        AlbumOneToOne album = albumOneToOneRepository.findById(25L).get();
        album.setName("范特西12");
        Artist artist = album.getArtist();
        artist.setName("David Bowie");
        albumOneToOneRepository.save(album);
    }

    //5、new 一个对象，设置id，设置属性。然后保存
    //结果：先查album，然后会进行更新
    //分析：save的实现中，判断有id时不为new状态，会进行merge。merge前会先查询再更新，类似git的pull，
    @Test
    public void new_album_set_id_save(){
        AlbumOneToOne album = AlbumOneToOne.builder()
                .id(29L)
                .artist(Artist.builder().name("周杰伦").build())
                .company(Company.builder().companyAddress("a").companyName("b").build())
                .name("范特西28")
                .publishData(new Date())
                .build();
        albumOneToOneRepository.save(album);
    }

    //6、级连删除
    //结果：album 被删除，artist 也被删除
    //分析：artist 被级连删除。但可能有其他 album 还关联在此 artist 上。数据完整性被破坏
    @Test
    public void delete(){
        AlbumOneToOne album = albumOneToOneRepository.findById(27L).get();
        albumOneToOneRepository.delete(album);
    }


    /*结论：
    1、设置级连persist后，如果想保存级连对象，不要设置id。一旦设置id会被认为是detached entity。
    2、顶级对象，设置 id 后 save 会进行 merge 操作。如果 id 有对应数据，那么 save 时会 update 此数据，否则 insert
    3、级连删除时，ManyToOne不建议删除，原因是可能有其他多方关联了一方，会破坏数据完整性。
    */
}
