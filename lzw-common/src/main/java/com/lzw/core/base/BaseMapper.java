package com.lzw.core.base;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * Created by LZW on 2019/4/6.
 */
public interface BaseMapper<T> extends com.baomidou.mybatisplus.mapper.BaseMapper<T>{

    List<T> selectPageByMap(@Param("cm") Map<String, Object> params);

    List<T> selectPageByMap(RowBounds rowBounds, @Param("cm") Map<String, Object> params);

}
