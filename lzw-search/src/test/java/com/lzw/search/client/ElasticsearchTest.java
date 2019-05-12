package com.lzw.search.client;

import com.baomidou.mybatisplus.plugins.Page;
import com.lzw.item.dto.SpuBo;
import com.lzw.search.LzwSearchApplication;
import com.lzw.search.pojo.Goods;
import com.lzw.search.repository.GoodsRepository;
import com.lzw.search.service.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZW on 2019/5/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LzwSearchApplication.class)
public class ElasticsearchTest {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private SearchService searchService;

    @Autowired
    private GoodsRepository goodsRepository;

    @Test
    public void createIndex(){
        // 创建索引
        this.elasticsearchTemplate.createIndex(Goods.class);
        // 配置映射
        this.elasticsearchTemplate.putMapping(Goods.class);
    }

    @Test
    public void loadRR(){
        List<SpuBo> spus = this.goodsClient.querySpuByPage(1, 1, true, null);
        // 创建Goods集合
        List<Goods> goodsList = new ArrayList<>();
        // 遍历spu
        for (SpuBo spu : spus) {
            try {
                Goods goods = this.searchService.buildGoods(spu);
                goodsList.add(goods);
            } catch (Exception e) {
                break;
            }
        }
    }

    @Test
    public void loadData(){
        // 创建索引
        this.elasticsearchTemplate.createIndex(Goods.class);
        // 配置映射
        this.elasticsearchTemplate.putMapping(Goods.class);
        int page = 1;
        int rows = 100;
        int size = 0;
        do {
            // 查询分页数据
            List<SpuBo> spus = this.goodsClient.querySpuByPage(page, rows, true, null);
            size = spus.size();
            // 创建Goods集合
            List<Goods> goodsList = new ArrayList<>();
            // 遍历spu
            for (SpuBo spu : spus) {
                try {
                    Goods goods = this.searchService.buildGoods(spu);
                    goodsList.add(goods);
                } catch (Exception e) {
                    break;
                }
            }

            this.goodsRepository.saveAll(goodsList);
            page++;
        } while (size == 100);
    }
}
