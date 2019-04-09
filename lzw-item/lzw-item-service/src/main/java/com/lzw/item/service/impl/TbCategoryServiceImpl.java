package com.lzw.item.service.impl;

import com.lzw.core.base.BaseServiceImpl;
import com.lzw.item.mapper.TbCategoryMapper;
import com.lzw.item.pojo.TbCategory;
import com.lzw.item.service.ITbCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系 服务实现类
 * </p>
 *
 * @author liuzw
 * @since 2019-04-07
 */
@Service
public class TbCategoryServiceImpl extends BaseServiceImpl<TbCategory> implements ITbCategoryService {

    @Autowired
    private TbCategoryMapper tbCategoryMapper;

    @Override
    public List<TbCategory> selectByBrandId(Long bid) {
        return tbCategoryMapper.selectByBrandId(bid);
    }
}