package com.lzw.item.mapper;

import com.lzw.core.base.BaseMapper;
import com.lzw.item.pojo.TbCategoryBrand;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author liuzw
 * @since 2019-04-07
 */
public interface TbCategoryBrandMapper extends BaseMapper<TbCategoryBrand> {

    List<Long> selectCids(@Param("cm") Map<String,Object> param);

}