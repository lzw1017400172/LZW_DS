package com.lzw.search.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.lzw.search.pojo.Goods;
import com.lzw.search.pojo.SearchRequest;
import com.lzw.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LZW on 2019/5/12.
 */
@RestController
@RequestMapping
public class SearchController {
    @Autowired
    private SearchService searchService;

    /**
     * 搜索商品
     *
     * @param request
     * @return
     */
    @PostMapping("/page")
    public ResponseEntity<org.springframework.data.domain.Page<Goods>> search(@RequestBody SearchRequest request) {
        org.springframework.data.domain.Page<Goods> result = this.searchService.search(request);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }
}
