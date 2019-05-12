package com.lzw.goods.client;

import com.lzw.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by LZW on 2019/5/11.
 * feign本身 支持http协议调用
 *  断路器
 *  负载均衡
 */
@FeignClient(value = "lzw-item-service")
public interface GoodsClient extends GoodsApi {
}
