package com.lzw.item.service;

import com.lzw.core.base.BaseService;
import com.lzw.item.pojo.TbCategory;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系  服务实现类
 * </p>
 * @author LZW
 * @since 2019-04-06
 */
public interface ITbCategoryService extends BaseService<TbCategory> {

    List<TbCategory> selectByBrandId(Long bid);

    List<TbCategory> getList(List<Long> bids);

    List<String> queryNameByIds(List<Long> ids);
}
