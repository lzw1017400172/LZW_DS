package com.lzw.item.service;

import com.lzw.core.base.BaseService;
import com.lzw.item.pojo.TbSpecification;

/**
 * <p>
 * </p>
 *
 * @author LZW
 * @since 2019-04-06
 */
public interface ITbSpecificationService extends BaseService<TbSpecification> {

    TbSpecification selectOne(TbSpecification model);

}
