package com.tw.twjpa;

import com.tw.twjpa.repository.AlbumOneToOneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OneToOneTest {
    @Autowired
    private AlbumOneToOneRepository albumOneToOneRepository;



}
