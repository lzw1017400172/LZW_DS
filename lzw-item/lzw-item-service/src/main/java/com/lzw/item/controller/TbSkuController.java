package com.lzw.item.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.lzw.core.base.BaseController;
import com.lzw.item.dto.SpuBo;
import com.lzw.item.pojo.TbBrand;
import com.lzw.item.pojo.TbCategory;
import com.lzw.item.pojo.TbSku;
import com.lzw.item.pojo.TbSpu;
import com.lzw.item.service.ITbSkuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * </p>
 *
 * @author LZW
 * @since 2019-04-06
 */
@Controller
@RequestMapping("/sku")
@Api(value = "sku端控制器")
public class TbSkuController extends BaseController{

    @Autowired
    private ITbSkuService tbSkuService;

    @ApiOperation(value = "", notes = "")
    @GetMapping("/list")
    public Object list(ModelMap modelMap,@RequestParam("id")Long id){
        Map<String,Object> param = new HashMap<>();
        param.put("spuId",id);
        return setSuccessModelMap(modelMap,tbSkuService.queryList(param));
    }

    @ApiOperation(value = "", notes = "")
    @GetMapping("/list2")
    public ResponseEntity<List<TbSku>> list2(ModelMap modelMap, @RequestParam("id")Long id){
        Map<String,Object> param = new HashMap<>();
        param.put("spuId",id);
        return ResponseEntity.ok(tbSkuService.queryList(param));
    }

}

