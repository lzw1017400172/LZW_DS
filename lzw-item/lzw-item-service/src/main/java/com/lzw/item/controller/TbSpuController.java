package com.lzw.item.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.lzw.core.base.BaseController;
import com.lzw.item.dto.SpuBo;
import com.lzw.item.pojo.*;
import com.lzw.item.service.ITbBrandService;
import com.lzw.item.service.ITbCategoryService;
import com.lzw.item.service.ITbSpuDetailService;
import com.lzw.item.service.ITbSpuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/spu")
@Api(value = "spu端控制器")
public class TbSpuController extends BaseController{

    @Autowired
    private ITbSpuService tbSpuService;
    @Autowired
    private ITbSpuDetailService tbSpuDetailService;
    @Autowired
    private ITbBrandService tbBrandService;
    @Autowired
    private ITbCategoryService tbCategoryService;

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/read/page")
    public Object query(ModelMap modelMap,String key,Boolean saleable,
                         String pageNum,String pageSize,String orderBy,boolean openSort,boolean asc){
        Map<String,Object> param = new HashMap<>();
        param.put("titleL",key);
        param.put("saleable",saleable);
        param.put("pageNum",pageNum);
        param.put("pageSize",pageSize);
        param.put("orderBy",orderBy);
        param.put("openSort",openSort);
        param.put("asc",asc);
        Page<TbSpu> pages = tbSpuService.query(param);
        Page<SpuBo> pages2 = new Page<>();
        BeanUtils.copyProperties(pages, pages2);
        List<SpuBo> list = new ArrayList<>();
        for(TbSpu spu:pages.getRecords()){
            SpuBo spuBo = new SpuBo();
            BeanUtils.copyProperties(spu, spuBo);
            TbBrand tbBrand = tbBrandService.queryById(spu.getBrandId());
            if(tbBrand != null){
                spuBo.setBname(tbBrand.getName());
            }
            List<String> categories = tbCategoryService.getList(Arrays.asList(spu.getCid1(),spu.getCid2(),spu.getCid3())).stream().map(TbCategory::getName).collect(Collectors.toList());
            spuBo.setCname(StringUtils.join(categories,"/"));
            list.add(spuBo);
        }
        pages2.setRecords(list);
        return setSuccessModelMap(modelMap,pages2);
    }

    @ApiOperation(value = "添加", notes = "添加")
    @PostMapping
    public Object insert(ModelMap modelMap, @RequestBody SpuBo spuBo){
        //insert spu
        TbSpu tbSpu = new TbSpu();
        BeanUtils.copyProperties(spuBo,tbSpu);
        tbSpu.setId(null);
        tbSpu.setCreateTime(new Date());
        tbSpu.setLastUpdateTime(new Date());
        //insert spu_detail
        TbSpuDetail tbSpuDetail = spuBo.getSpuDetail();
        //insert sku
        List<TbSku> skus = spuBo.getSkus();
        tbSpuService.insertSpu(tbSpu,tbSpuDetail,skus);
        return setSuccessModelMap(modelMap);
    }

    @ApiOperation(value = "", notes = "")
    @GetMapping("/detail/{spuId}")
    public Object detailSpuid(ModelMap modelMap, @PathVariable("spuId") Long spuId){
        TbSpuDetail tbSpuDetail = new TbSpuDetail();
        tbSpuDetail.setSpuId(spuId);
        tbSpuDetail = tbSpuDetailService.selectOne(tbSpuDetail);
        return setSuccessModelMap(modelMap,tbSpuDetail);
    }

//    @ApiOperation(value = "删除", notes = "删除")
//    @DeleteMapping
//    public Object delete(ModelMap modelMap, @RequestBody TbBrand tbBrand){
//        Assert.notNull(tbBrand.getId(),"ID");
//        tbBrandService.deleteById(tbBrand.getId());
//        return setSuccessModelMap(modelMap);
//    }

}

