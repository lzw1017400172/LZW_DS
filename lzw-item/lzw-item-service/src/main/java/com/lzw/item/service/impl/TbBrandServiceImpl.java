package com.lzw.item.service.impl;


import com.lzw.core.base.BaseServiceImpl;
import com.lzw.item.pojo.TbBrand;
import com.lzw.item.pojo.TbCategoryBrand;
import com.lzw.item.service.ITbBrandService;
import com.lzw.item.service.ITbCategoryBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 品牌表，一个品牌下有多个商品（spu），一对多关系 服务实现类
 * </p>
 *
 * @author LZW
 * @since 2019-04-06
 */
@Service
public class TbBrandServiceImpl extends BaseServiceImpl<TbBrand> implements ITbBrandService {

    @Autowired
    private ITbCategoryBrandService tbCategoryBrandService;

    @Override
    @Transactional
    public void insertBrandAndCategory(TbBrand tbBrand) {
        tbBrand.setId(null);
        this.insert(tbBrand);
        List<TbCategoryBrand> cbs = handlerCBs(tbBrand);
        if(cbs.size() > 0) {
            for(TbCategoryBrand tbCategoryBrand:cbs){
                tbCategoryBrandService.insert(tbCategoryBrand);
            }
        }
    }

    @Override
    @Transactional
    public void updateBrandAndCategory(TbBrand tbBrand) {
        if(tbBrand.getId() != null){
            this.update(tbBrand);
            if(tbBrand.getCids() != null){
                List<TbCategoryBrand> cbs = handlerCBs(tbBrand);
                TbCategoryBrand delCb = new TbCategoryBrand();
                delCb.setBrandId(tbBrand.getId());
                tbCategoryBrandService.delete(delCb);
                if(cbs.size() > 0) {
                    for(TbCategoryBrand tbCategoryBrand:cbs){
                        tbCategoryBrandService.insert(tbCategoryBrand);
                    }
                }
            }
        }
    }

    private List<TbCategoryBrand> handlerCBs(TbBrand tbBrand){
        List<TbCategoryBrand> cbs = new ArrayList<>();
        if(tbBrand.getCids() != null && tbBrand.getCids().length() > 0){
            String[] cids = tbBrand.getCids().split(",");
            for(String cidStr:cids){
                Long cid = Long.valueOf(cidStr);
                TbCategoryBrand tbCategoryBrand = new TbCategoryBrand();
                tbCategoryBrand.setBrandId(tbBrand.getId());
                tbCategoryBrand.setCategoryId(cid);
                cbs.add(tbCategoryBrand);
            }
        }
        return cbs;
    }
}
