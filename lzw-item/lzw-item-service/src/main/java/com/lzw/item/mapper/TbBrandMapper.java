package com.lzw.item.mapper;

import com.lzw.core.base.BaseMapper;
import com.lzw.item.pojo.TbBrand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 品牌表，一个品牌下有多个商品（spu），一对多关系 Mapper 接口
 * </p>
 *
 * @author LZW
 * @since 2019-04-06
 */
public interface TbBrandMapper extends BaseMapper<TbBrand> {

    List<TbBrand> selectByCid(@Param("cid") Long cid);
}
