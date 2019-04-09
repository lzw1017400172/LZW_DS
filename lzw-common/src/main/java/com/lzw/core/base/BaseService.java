package com.lzw.core.base;

import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by LZW on 2019/4/6.
 */
public interface BaseService<T> {

    /**
     * 者修改
     * @param record
     * @return
     */
    void update(T record);

    /**
     * 新增
     * @param record
     * @return
     */
    void insert(T record);

    /**
     * 删除byid
     * @param id
     */
    void deleteById(Long id);

    /**
     * 删除
     * @param record
     */
    void delete(T record);

    /**
     * key为字段，val为值作为条件删除
     * @param param
     */
    void deleteByMap(Map<String,Object> param);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    T queryById(Long id);

    /**
     * 查询集合
     * @param param
     * @return
     */
    List<T> queryList(Map<String,Object> param);

    /**
     * 查询集合
     * @param record
     * @return
     */
    List<T> selectList(T record);

    /**
     * 分页查询
     * @param param
     * @return
     */
    Page<T> query(Map<String,Object> param);
}
