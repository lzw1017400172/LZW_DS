package com.lzw.item.mapper;

import com.lzw.core.base.BaseMapper;
import com.lzw.item.pojo.TbCategory;
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
public interface TbCategoryMapper extends BaseMapper<TbCategory> {

    List<TbCategory> selectByBrandId(Long bid);
}