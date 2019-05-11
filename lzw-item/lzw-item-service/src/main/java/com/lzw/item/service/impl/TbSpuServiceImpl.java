package com.lzw.item.service.impl;


import com.lzw.core.base.BaseServiceImpl;
import com.lzw.item.pojo.TbSku;
import com.lzw.item.pojo.TbSpu;
import com.lzw.item.pojo.TbSpuDetail;
import com.lzw.item.service.ITbSkuService;
import com.lzw.item.service.ITbSpuDetailService;
import com.lzw.item.service.ITbSpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author LZW
 * @since 2019-04-06
 */
@Service
public class TbSpuServiceImpl extends BaseServiceImpl<TbSpu> implements ITbSpuService {

    @Autowired
    private ITbSpuDetailService tbSpuDetailService;
    @Autowired
    private ITbSkuService tbSkuService;

    @Override
    @Transactional
    public void insertSpu(TbSpu tbSpu, TbSpuDetail tbSpuDetail, List<TbSku> skus) {
        tbSpu.setSaleable(true);
        tbSpu.setValid(true);
        insert(tbSpu);
        tbSpuDetail.setSpuId(tbSpu.getId());
        tbSpuDetailService.insert(tbSpuDetail);
        skus.forEach(v -> {
            v.setCreateTime(tbSpu.getCreateTime());
            v.setLastUpdateTime(tbSpu.getLastUpdateTime());
            v.setSpuId(tbSpu.getId());
            tbSkuService.insert(v);
        });
    }
}
