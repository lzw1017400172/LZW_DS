package com.lzw.goods.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lzw.goods.client.CategoryClient;
import com.lzw.goods.client.GoodsClient;
import com.lzw.item.pojo.TbCategory;
import com.lzw.item.pojo.TbSku;
import com.lzw.item.pojo.TbSpu;
import com.lzw.item.pojo.TbSpuDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LZW on 2019/5/12.
 */
@Service
public class GoodsService {
    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private CategoryClient categoryClient;

    private static final Logger logger = LoggerFactory.getLogger(GoodsService.class);

    public Map<String, Object> loadModel(Long spuId){

        try {
            // 查询spu
            TbSpu spu = this.goodsClient.querySpuById(spuId);

            // 查询spu详情
            TbSpuDetail spuDetail = this.goodsClient.querySpuDetailById(spuId);

            // 查询sku
            List<TbSku> skus = this.goodsClient.querySkuBySpuId(spuId);

//            // 查询品牌
//            List<Brand> brands = this.brandClient.queryBrandByIds(Arrays.asList(spu.getBrandId()));

            // 查询分类
            List<TbCategory> categories = getCategories(spu);

            // 查询组内参数
            JSONArray specGroups = JSON.parseArray(spuDetail.getSpecifications());

            // 查询所有特有规格参数
//            List<SpecParam> specParams = this.specificationClient.querySpecParam(null, spu.getCid3(), null, false);
//            // 处理规格参数
//            Map<Long, String> paramMap = new HashMap<>();
//            specParams.forEach(param->{
//                paramMap.put(param.getId(), param.getName());
//            });

            Map<String, Object> map = new HashMap<>();
            map.put("spu", spu);
            map.put("spuDetail", spuDetail);
            map.put("skus", skus);
            map.put("brand", "UUUUUUUUUUUUUUUUUUU");
            map.put("categories", categories);
            map.put("groups", specGroups);
            map.put("params", new HashMap<>());
            return map;
        } catch (Exception e) {
            logger.error("加载商品数据出错,spuId:{}", spuId, e);
        }
        return null;
    }

    private List<TbCategory> getCategories(TbSpu spu) {
        try {
            List<String> names = this.categoryClient.queryNameByIds(
                    Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
            TbCategory c1 = new TbCategory();
            c1.setName(names.get(0));
            c1.setId(spu.getCid1());

            TbCategory c2 = new TbCategory();
            c2.setName(names.get(1));
            c2.setId(spu.getCid2());

            TbCategory c3 = new TbCategory();
            c3.setName(names.get(2));
            c3.setId(spu.getCid3());

            return Arrays.asList(c1, c2, c3);
        } catch (Exception e) {
            logger.error("查询商品分类出错，spuId：{}", spu.getId(), e);
        }
        return null;
    }
}
