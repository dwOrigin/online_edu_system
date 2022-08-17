package com.houduan.ArticleTest;

import com.houduan.mapper.ArticleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author dw
 * @date 2022/8/17 10:13
 */
@SpringBootTest
public class ArticleTest {
    @Autowired
    private ArticleMapper mapper;
    @Test
    public void TestAddArticle(){

    }

}
