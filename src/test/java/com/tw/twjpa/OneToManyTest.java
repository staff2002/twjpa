package com.tw.twjpa;

import com.tw.twjpa.enums.MediumType;
import com.tw.twjpa.model.Artist;
import com.tw.twjpa.model.Company;
import com.tw.twjpa.model.onetomany.AlbumOneToMany;
import com.tw.twjpa.model.onetomany.AlbumVersionManyToOne;
import com.tw.twjpa.repository.AlbumOneToManyRepository;
import com.tw.twjpa.repository.AlbumVersionManyToOneRepository;
import com.tw.twjpa.service.AlbumOneToManyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class OneToManyTest {
    @Autowired
    private AlbumOneToManyRepository albumOneToManyRepository;

    @Autowired
    private AlbumVersionManyToOneRepository albumVersionManyToOneRepository;

    @Autowired
    private AlbumOneToManyService albumOneToManyService;

    //一、级连保存

    /*
     * 1、oneToMany 设置 mappedby，一方不维护关系. 保存时不为多方设置一方
     * 结果：
     * 分析：
     * */
    @Test
    public void mappedBy_bidirectional_without_set_album_to_version_save(){
        AlbumVersionManyToOne albumVersion = AlbumVersionManyToOne.builder().mediumType(MediumType.CD).versionName("首版").build();

        AlbumOneToMany album = AlbumOneToMany.builder()
                .albumVersions(Arrays.asList(albumVersion))
                .artist(Artist.builder().id(1).build())
                .company(Company.builder().companyAddress("a").companyName("b").build())
                .name("海阔天空")
                .publishData(new Date()).build();

        albumOneToManyRepository.save(album);
    }

    /*
    * 2、oneToMany 设置 mappedby，一方不维护关系。为多方设置一方
    * 结果：保存成功
    * 分析：这是正确级连保存的方式。需要为多方设置一方，因为由多方维护关系，否则无法保存。
    * */
    @Test
    public void mappedBy_bidirectional_save(){
        AlbumVersionManyToOne albumVersion = AlbumVersionManyToOne.builder().mediumType(MediumType.CD).versionName("首版").build();

        AlbumOneToMany album = AlbumOneToMany.builder()
                .albumVersions(Arrays.asList(albumVersion))
                .artist(Artist.builder().id(1).build())
                .company(Company.builder().companyAddress("a").companyName("b").build())
                .name("海阔天空")
                .publishData(new Date()).build();
        albumVersion.setAlbum(album);

        albumOneToManyRepository.save(album);
    }

    /*
     * 3、oneToMany使用JoinColumn，维护关系
     * 结果：保存成功，会多执行一条update
     * 分析：jpa对sql的优化并不是最优，一方没有放弃维护，导致去更新外键关系。
     * 参考官方文档
     * https://docs.jboss.org/hibernate/annotations/3.5/reference/en/html_single/
     * 2.2.5.3.1.1. Bidirectional
     * 按照官方说法需要在ManyToOne的JoinColumn加上 insertable=false, updatable=false。意思是多方insert、update 时不维护外键，放弃维护关系。
     * 但是由于album_id不能为空会导致insert失败。
     * 不推荐使用这种方式
     * */
    @Test
    public void join_column_bidirectional_save(){
        AlbumVersionManyToOne albumVersion = AlbumVersionManyToOne.builder().mediumType(MediumType.CD).versionName("首版").build();

        AlbumOneToMany album = AlbumOneToMany.builder()
                .albumVersions(Arrays.asList(albumVersion))
                .artist(Artist.builder().id(1).build())
                .company(Company.builder().companyAddress("a").companyName("b").build())
                .name("海阔天空2")
                .publishData(new Date()).build();
        albumVersion.setAlbum(album);

        albumOneToManyRepository.save(album);
    }

    /*
    * 4、单向
    * 结果：保存成功
    * 分析：单向时，不需要为 albumVersion 设置 albumId。但是会多一条无用的update语句。
    * */
    @Test
    public void unidirectional_save(){
        AlbumVersionManyToOne albumVersion = AlbumVersionManyToOne.builder().mediumType(MediumType.CD).versionName("首版").build();

        AlbumOneToMany album = AlbumOneToMany.builder()
                .albumVersions(Arrays.asList(albumVersion))
                .artist(Artist.builder().id(1).build())
                .company(Company.builder().companyAddress("a").companyName("b").build())
                .name("海阔天空")
                .publishData(new Date()).build();

        albumOneToManyRepository.save(album);
    }

    /*
     * 4、一方的集合中添加多方,cascade需要设置persist和merge
     * 结果： 保存成功
     * 分析： 必须在事物中，否则执行完查询，session关闭，无法完成懒加载。cascade需要persist和merge。
     * 原因是save的时候判断对象存在做的 merge 操作。不设置cascade=merge，无法merge新添加的多方
     * */
    @Test
    @Transactional
    public void add_many_to_one_save(){
        AlbumOneToMany albumOneToMany = albumOneToManyRepository.findById(1L).get();

        AlbumVersionManyToOne albumVersion = AlbumVersionManyToOne.builder()
                .mediumType(MediumType.ON_LINE)
                .album(albumOneToMany)
                .versionName("线上版").build();
        List<AlbumVersionManyToOne> albumVersions = albumOneToMany.getAlbumVersions();
        albumVersions.add(albumVersion);
        albumOneToManyRepository.save(albumOneToMany);
    }


    /*
    * 保存小结
    * 正确姿势，oneToMany使用mappedBy放弃关系维护，ManyToOne使用JoinColumn。保存时，多方需要设置一方。
    * oneTomMany 不要使用 JoinColumn。否则会多一条更新外键的 update 语句
    * */

    // ------------------二、级连删除-----------------------------------
    /*
    * 1、双向 mappedBy配置
    * 结果：删除成功
    * 分析：三条查询sql，第 1 条是 find 方法触发。第2，3条是delete前确实对象是否存在触发
    * 四条delete语句，一条删除album，三条分别删除三个关联的albumVersion
    * 如果一方关联多方的数量很大，并不适合采用级连删除的方式，因为会产生大量的sql
    * 参考文章：https://thorben-janssen.com/avoid-cascadetype-delete-many-assocations/
    * */
    @Test
    public void cascade_delete_one(){
        AlbumOneToMany albumOneToMany = albumOneToManyRepository.findById(100L).get();
        albumOneToManyRepository.delete(albumOneToMany);
    }

    /*
    * 2、双向 mappedBy配置，不级连删除，多方自己删除
    * 结果：删除成功
    * 分析：两条查询。两条delete。
    * 一条删除album，另外一条一次删除全部关联的albumVersion
    * */
    @Test
    @Transactional
    public void separated_delete(){
        AlbumOneToMany albumOneToMany = albumOneToManyRepository.findById(100L).get();
        albumOneToManyRepository.delete(albumOneToMany);
        albumVersionManyToOneRepository.deleteByAlbumId(albumOneToMany.getId());
    }

    /*
     * 级连删除小结
     * 级连删除会触发多方多条sql删除。如果关联的多方数量不会很大可以使用级连删除。
     * 如果关联的数量很大，建议多方按外键单独删除而不采用级连删除，以提升性能。
     * */


    // ------------------三、删除一方持有的多方 List 中的数据------------------
    /*
     * 1、双向 mappedBy 配置，不级连删除，从一方 list 中 remove 掉
     * 结果：list 中 remove 掉的 AlbumVersion 并没有被删除
     * 分析：原因不是没有设置级连删除。可以尝试设置级连为all，结果还是无法删除。这个场景不属于级连删除。想这样删除掉多方是不行的。
     * */
    @Test
    public void remove_from_List(){
        albumOneToManyService.remove_from_List();
    }

    /*
     * 2、双向 mappedBy配置，不级连删除。从list remove，直接手动删除多方
     * 结果：可以成功删除
     * 分析：一方需要断开关系，然后显式删除多方。 是否可以通过一方级连删除呢？
     * */
    @Test
    public void delete_many(){
        albumOneToManyService.delete_many();
    }

    /*
     * 3、双向 mappedBy配置，oneToMany 设置orphanRemoval = true, 设置级连 ALL。从一方持有的list remove
     * 结果：可以删除掉
     * 分析：orphanRemoval = true，会级连删除关系被移除的实体。
     * 进一步分析：既然源码说级连删除，那么把级连改成 仅 Delete 测试，发现并不会删除。其实是级连 PERSIST 的效果。
     * */
    @Test
    public void orphanRemoval_remove_many_save_one(){
        albumOneToManyService.orphanRemoval_remove_many_save_one();
    }

    /*
    * 小结：想要删除一方持有的多方，比较好的做法是在 @oneToMany 设置 orphanRemoval = true，cascade 设置 PERSIST或者ALL
    * 然后在一方持有的 list 中 remove 掉多方。最后 save 一方
    *
    * 还有一种做法是显式用多方 repository 删除。
    */

    /*
    * OneToMany总结：
    *
    * 1、一方的 @oneToMany 设置 mappedBy放弃维护关系。不要使用JoinColumn，否则会有多余sql执行。
    * 2、一方的 @oneToMany 设置 orphanRemoval = true。便于通过一方维护持有的多方删除。
    * 3、级连删除 会触发 n+1 sql。如果一方关联的多方数量很大，建议不采用级连删除。由多方写sql根据外键一个sql删除
    * */
}
