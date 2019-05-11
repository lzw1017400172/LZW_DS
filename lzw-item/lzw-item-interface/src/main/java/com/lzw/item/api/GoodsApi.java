package com.lzw.item.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.lzw.item.dto.SpuBo;
import com.lzw.item.pojo.TbSku;
import com.lzw.item.pojo.TbSpuDetail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by LZW on 2019/5/11.
 */
@RequestMapping("/goods")
public interface GoodsApi {
    /**
     * 分页查询商品
     * @param page
     * @param rows
     * @param saleable
     * @param key
     * @return
     */
    @GetMapping("/spu/page")
    Page<SpuBo> querySpuByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "saleable", defaultValue = "true") Boolean saleable,
            @RequestParam(value = "key", required = false) String key);

    /**
     * 根据spu商品id查询详情
     * @param id
     * @return
     */
    @GetMapping("/spu/detail/{id}")
    TbSpuDetail querySpuDetailById(@PathVariable("id") Long id);

    /**
     * 根据spu的id查询sku
     * @param id
     * @return
     */
    @GetMapping("/sku/list")
    List<TbSku> querySkuBySpuId(@RequestParam("id") Long id);
}
