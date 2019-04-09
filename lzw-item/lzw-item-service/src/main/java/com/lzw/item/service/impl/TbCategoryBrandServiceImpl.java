package com.lzw.item.service.impl;

import com.lzw.core.base.BaseServiceImpl;
import com.lzw.item.mapper.TbCategoryBrandMapper;
import com.lzw.item.pojo.TbCategoryBrand;
import com.lzw.item.service.ITbCategoryBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品分类和品牌的中间表，两者是多对多关系 服务实现类
 * </p>
 *
 * @author liuzw
 * @since 2019-04-07
 */
@Service
public class TbCategoryBrandServiceImpl extends BaseServiceImpl<TbCategoryBrand> implements ITbCategoryBrandService {

    @Autowired
    private TbCategoryBrandMapper tbCategoryBrandMapper;

    @Override
    public List<Long> selectCids(Map<String, Object> param) {
        return tbCategoryBrandMapper.selectCids(param);
    }
}
