package com.lzw.search.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzw.item.dto.SpecParam;
import com.lzw.item.pojo.TbSku;
import com.lzw.item.pojo.TbSpu;
import com.lzw.item.pojo.TbSpuDetail;
import com.lzw.search.client.CategoryClient;
import com.lzw.search.client.GoodsClient;
import com.lzw.search.pojo.Goods;
import com.lzw.search.pojo.SearchRequest;
import com.lzw.search.repository.GoodsRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.TypeReference;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * Created by LZW on 2019/5/12.
 */
@Service
public class SearchService {
    @Autowired
    private CategoryClient categoryClient;

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private GoodsRepository goodsRepository;

    public Goods buildGoods(TbSpu spu) throws IOException {
        Goods goods = new Goods();

        // 查询商品分类名称
        List<String> names = this.categoryClient.queryNameByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
        // 查询sku
        List<TbSku> skus = this.goodsClient.querySkuBySpuId(spu.getId());
        // 查询详情
        TbSpuDetail spuDetail = this.goodsClient.querySpuDetailById(spu.getId());

        // 处理sku，仅封装id、价格、标题、图片，并获得价格集合
        List<Long> prices = new ArrayList<>();
        List<Map<String, Object>> skuList = new ArrayList<>();
        skus.forEach(sku -> {
            prices.add(sku.getPrice());
            Map<String, Object> skuMap = new HashMap<>();
            skuMap.put("id", sku.getId());
            skuMap.put("title", sku.getTitle());
            skuMap.put("price", sku.getPrice());
            skuMap.put("image", StringUtils.isBlank(sku.getImages()) ? "" : StringUtils.split(sku.getImages(), ",")[0]);
            skuList.add(skuMap);
        });

        // 处理规格参数
        JSONArray genericSpecs = JSONArray.parseArray(spuDetail.getSpecifications());
        //JSONArray specialSpecs = JSONArray.parseArray(spuDetail.getSpecTemplate());

        // 获取可搜索的规格参数
        Map<String, Object> searchSpec = new HashMap<>();

        // 过滤规格模板，把所有可搜索的信息保存到Map中
        Map<String, Object> specMap = new HashMap<>();
        for(int i = 0;i<genericSpecs.size();i++){
            JSONObject job1 = genericSpecs.getJSONObject(i);
            JSONArray params = job1.getJSONArray("params");
            for(int j = 0;j<params.size();j++){
                JSONObject job2 = params.getJSONObject(j);
                if ((boolean)job2.get("searchable")) {
                    if ((boolean)job2.get("global")) {
                        String value = job2.get("v") == null ? "" :job2.get("v").toString();
//                        if((boolean)job2.get("numerical")){
//                            value = chooseSegment(value, p);
//                        }
                        specMap.put(job2.get("k").toString(), StringUtils.isBlank(value) ? "其它" : value);
                    } else {
                        specMap.put(job2.get("k").toString(), job2.get("options"));
                    }
                }
            }
        }

        goods.setId(spu.getId());
        goods.setSubTitle(spu.getSubTitle());
        goods.setBrandId(spu.getBrandId());
        goods.setCid1(spu.getCid1());
        goods.setCid2(spu.getCid2());
        goods.setCid3(spu.getCid3());
        goods.setCreateTime(spu.getCreateTime());
        goods.setAll(spu.getTitle() + " " + StringUtils.join(names, " "));
        goods.setPrice(prices);
        goods.setSkus(JSON.toJSONString(skuList));
        goods.setSpecs(specMap);
        return goods;
    }

    public org.springframework.data.domain.Page<Goods> search(SearchRequest request) {
        String key = request.getKey();
        // 判断是否有搜索条件，如果没有，直接返回null。不允许搜索全部商品
        if (StringUtils.isBlank(key)) {
            return null;
        }

        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        // 1、对key进行全文检索查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("all", key).operator(Operator.AND));

        // 2、通过sourceFilter设置返回的结果字段,我们只需要id、skus、subTitle
        queryBuilder.withSourceFilter(new FetchSourceFilter(
                new String[]{"id","skus","subTitle"}, null));

        // 3、分页
        // 准备分页参数
        int page = request.getPage();
        int size = request.getSize();
        queryBuilder.withPageable(new PageRequest(page, size));

        // 4、查询，获取结果
        org.springframework.data.domain.Page<Goods> pageInfo = this.goodsRepository.search(queryBuilder.build());
        return pageInfo;
    }
}
