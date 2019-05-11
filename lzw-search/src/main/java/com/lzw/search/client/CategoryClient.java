package com.lzw.search.client;

import com.lzw.item.api.CategoryApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Created by LZW on 2019/5/11.
 */
@FeignClient(value = "lzw-item-service")
public interface CategoryClient extends CategoryApi {
}
