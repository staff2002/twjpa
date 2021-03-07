package com.tw.twjpa;

import com.tw.twjpa.model.Tag;
import com.tw.twjpa.model.manytomany.AlbumManyToMany;
import com.tw.twjpa.model.manytomany.TagManyToMany;
import com.tw.twjpa.repository.AlbumManyToManyRepository;
import com.tw.twjpa.service.AlbumManyToManyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class ManyToManyTest {
    @Autowired
    private AlbumManyToManyRepository albumManyToManyRepository;

    @Autowired
    private AlbumManyToManyService albumManyToManyService;

    //----------一、查询-----------------
    /*
    *
    * */
    @Test
    @Transactional
    public void cascade_get(){
        AlbumManyToMany album = albumManyToManyRepository.getOne(1L);
        List<TagManyToMany> tags = album.getTags();
        System.out.println(tags);
    }

    //----------二、保存-----------------
    /*1、级连需要设置persist和merge
    * 结果：成功保存tag，以及album和tag关系
    * 分析：不设置级连 merge 不会成功保存。
    * 这种方式会生成一条insert tag，一条delete album的relation。n条现有relation
    */
    @Test
    public void save(){
        albumManyToManyService.save_new_tag_and_relation();
    }

    /*
    * 总结：
    * ManyToMany 不适合级连操作，会产生大量sql。而且级连删除有可能删除意料之外的数据
    * 建议 ManyToMany 不设置级连，手动维护关系s
    * */
}
