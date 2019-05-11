package com.lzw.item.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by LZW on 2019/5/11.
 */
@RequestMapping("/tbCategory")
public interface CategoryApi {

    @GetMapping("/names")
    List<String> queryNameByIds(@RequestParam("ids") List<Long> ids);

}