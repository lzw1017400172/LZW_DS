package com.lzw.item.service;

import com.lzw.core.base.BaseService;
import com.lzw.item.pojo.TbCategoryBrand;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品分类和品牌的中间表，两者是多对多关系  服务实现类
 * </p>
 *
 * @author LZW
 * @since 2019-04-06
 */
public interface ITbCategoryBrandService extends BaseService<TbCategoryBrand> {

    /**
     * 查询分类ids
     * @param param
     * @return
     */
    List<Long> selectCids(Map<String,Object> param);

}
