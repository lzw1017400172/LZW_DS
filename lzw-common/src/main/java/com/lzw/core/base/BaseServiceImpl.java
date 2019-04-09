package com.lzw.core.base;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lzw.core.util.DataUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by LZW on 2019/4/6.
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    protected Logger logger = LogManager.getLogger(getClass());

    @Autowired
    protected BaseMapper<T> mapper;

    /** 分页查询 */
    public Page getPage(Map<String, Object> params) {
        Integer current = 1;
        Integer size = 10;
        boolean openSort = false;
        boolean asc = false;
        String orderBy = "id";
        if (DataUtil.isNotEmpty(params.get("pageNum"))) {
            current = Integer.valueOf(params.get("pageNum").toString());
        }
        if (DataUtil.isNotEmpty(params.get("pageIndex"))) {
            current = Integer.valueOf(params.get("pageIndex").toString());
        }
        if (DataUtil.isNotEmpty(params.get("pageSize"))) {
            size = Integer.valueOf(params.get("pageSize").toString());
        }
        if (DataUtil.isNotEmpty(params.get("openSort"))) {
            if(params.get("openSort") instanceof Boolean){
                openSort = (boolean) params.get("openSort");
                params.remove("openSort");
            }
        }
        if (DataUtil.isNotEmpty(params.get("asc"))) {
            if(params.get("asc") instanceof Boolean){
                asc = (boolean) params.get("asc");
                params.remove("asc");
            }
        }
        if (DataUtil.isNotEmpty(params.get("orderBy"))) {
            orderBy = (String) params.get("orderBy");
            params.remove("orderBy");
        }
        if (size == -1) {
            Page<T> page = new Page<>();
            page.setOrderByField(orderBy);//排序字段
            page.setOpenSort(openSort);//是否排序
            page.setAsc(asc);//是否是正序
            return page;
        }
        Page<T> page = new Page<>(current, size, orderBy);
        page.setOpenSort(openSort);//是否排序
        page.setAsc(asc);//是否是正序
        return page;
    }


    @Override
    public void update(T record) {
        mapper.updateById(record);
    }

    @Override
    public void insert(T record) {
        mapper.insert(record);
    }

    @Override
    public void deleteById(Long id) {
        mapper.deleteById(id);
    }

    @Override
    public void delete(T record) {
        Wrapper<T> wrapper = new EntityWrapper<>(record);
        mapper.delete(wrapper);
    }

    @Override
    public void deleteByMap(Map<String, Object> param) {
        mapper.deleteByMap(param);
    }

    @Override
    public T queryById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public List<T> queryList(Map<String, Object> params) {
        return mapper.selectPageByMap(params);
    }

    @Override
    public List<T> selectList(T record) {
        Wrapper<T> wrapper = new EntityWrapper<>(record);
        return mapper.selectList(wrapper);
    }

    @Override
    public Page<T> query(Map<String, Object> params) {
        Page<T> page = getPage(params);
        page.setRecords(mapper.selectPageByMap(page, params));
        return page;
    }
}
