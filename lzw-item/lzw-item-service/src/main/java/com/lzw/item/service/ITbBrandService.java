package com.lzw.item.service;

import com.lzw.core.base.BaseService;
import com.lzw.item.pojo.TbBrand;

import java.util.List;

/**
 * <p>
 * 品牌表，一个品牌下有多个商品（spu），一对多关系 服务类
 * </p>
 *
 * @author LZW
 * @since 2019-04-06
 */
public interface ITbBrandService extends BaseService<TbBrand> {

    /**
     * 新增品牌and新增品牌-分类关系
     * @param tbBrand
     */
    void insertBrandAndCategory(TbBrand tbBrand);

    /**
     * 修改品牌and新增品牌-分类关系
     * @param tbBrand
     */
    void updateBrandAndCategory(TbBrand tbBrand);

    /**
     * 根据分类id查询品牌
     * @param cid
     * @return
     */
    List<TbBrand> selectByCid(Long cid);
}
