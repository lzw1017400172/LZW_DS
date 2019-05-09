package com.lzw.item.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.lzw.core.base.BaseController;
import com.lzw.item.dto.SpuBo;
import com.lzw.item.pojo.TbBrand;
import com.lzw.item.pojo.TbCategory;
import com.lzw.item.pojo.TbSpu;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

//    @ApiOperation(value = "添加or更新", notes = "添加or更新")
//    @PostMapping
//    public Object update(ModelMap modelMap, @RequestBody TbBrand tbBrand){
//        if(tbBrand.getId() == null){
//            Assert.length(tbBrand.getName(),1,50,"NAME");
//            String initial = PinyinUtil.getPinYinHeadUperChar(tbBrand.getName());
//            tbBrand.setLetter(initial.substring(0,1));
//            tbBrandService.insertBrandAndCategory(tbBrand);
//        } else {
//            if(StringUtils.isNotBlank(tbBrand.getName())){
//                String initial = PinyinUtil.getPinYinHeadUperChar(tbBrand.getName());
//                tbBrand.setLetter(initial.substring(0,1));
//            } else {
//                tbBrand.setLetter(null);
//            }
//            tbBrandService.updateBrandAndCategory(tbBrand);
//        }
//        return setSuccessModelMap(modelMap,tbBrand);
//    }
//
//    @ApiOperation(value = "删除", notes = "删除")
//    @DeleteMapping
//    public Object delete(ModelMap modelMap, @RequestBody TbBrand tbBrand){
//        Assert.notNull(tbBrand.getId(),"ID");
//        tbBrandService.deleteById(tbBrand.getId());
//        return setSuccessModelMap(modelMap);
//    }

}

