package com.lzw.item.service;

import com.lzw.core.base.BaseService;
import com.lzw.item.pojo.TbSku;
import com.lzw.item.pojo.TbSpu;
import com.lzw.item.pojo.TbSpuDetail;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author LZW
 * @since 2019-04-06
 */
public interface ITbSpuService extends BaseService<TbSpu> {

    void insertSpu(TbSpu tbSpu, TbSpuDetail tbSpuDetail, List<TbSku> skus);
}
