package com.lzw.item.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.lzw.item.dto.SpuBo;
import com.lzw.item.pojo.TbSku;
import com.lzw.item.pojo.TbSpu;
import com.lzw.item.pojo.TbSpuDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by LZW on 2019/5/11.
 */
public interface GoodsApi {
    /**
     * 分页查询商品
     * @param pageNum
     * @param pageSize
     * @param saleable
     * @param key
     * @return
     */
    @GetMapping("/spu/page")
    List<SpuBo> querySpuByPage(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "saleable", defaultValue = "true") Boolean saleable,
            @RequestParam(value = "key", required = false) String key);

    /**
     * 根据spu商品id查询详情
     * @param id
     * @return
     */
    @GetMapping("/spu/detail2/{id}")
    TbSpuDetail querySpuDetailById(@PathVariable("id") Long id);

    @GetMapping("/spu/querySpuById/{spuId}")
    TbSpu querySpuById(@PathVariable("spuId") Long spuId);

    /**
     * 根据spu的id查询sku
     * @param id
     * @return
     */
    @GetMapping("/sku/list2")
    List<TbSku> querySkuBySpuId(@RequestParam("id") Long id);

}
