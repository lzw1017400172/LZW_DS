package com.lzw.item.controller;


import com.lzw.core.base.BaseController;
import com.lzw.core.util.Assert;
import com.lzw.core.util.PinyinUtil;
import com.lzw.item.pojo.TbBrand;
import com.lzw.item.service.TbBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 品牌表，一个品牌下有多个商品（spu），一对多关系 前端控制器
 * </p>
 *
 * @author LZW
 * @since 2019-04-06
 */
@Controller
@RequestMapping("/tbBrand")
@Api(value = "品牌表端控制器")
public class TbBrandController extends BaseController{

    @Autowired
    private TbBrandService tbBrandService;

    @ApiOperation(value = "查询全部", notes = "查询全部")
    @GetMapping("/read/list")
    public Object queryList(ModelMap modelMap,String nameL,
                            String pageNum,String pageSize,String orderBy,boolean openSort,boolean asc){
        Map<String,Object> param = new HashMap<>();
        param.put("nameL",nameL);
        param.put("pageNum",pageNum);
        param.put("pageSize",pageSize);
        param.put("orderBy",orderBy);
        param.put("openSort",openSort);
        param.put("asc",asc);
        return setSuccessModelMap(modelMap,tbBrandService.queryList(param));
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/read/page")
    public Object query(ModelMap modelMap,String nameL,
                         String pageNum,String pageSize,String orderBy,boolean openSort,boolean asc){
        Map<String,Object> param = new HashMap<>();
        param.put("nameL",nameL);
        param.put("pageNum",pageNum);
        param.put("pageSize",pageSize);
        param.put("orderBy",orderBy);
        param.put("openSort",openSort);
        param.put("asc",asc);
        return setSuccessModelMap(modelMap,tbBrandService.query(param));
    }

    @ApiOperation(value = "添加or更新", notes = "添加or更新")
    @PostMapping
    public Object update(ModelMap modelMap, @RequestBody TbBrand tbBrand){
        if(tbBrand.getId() == null){
            Assert.length(tbBrand.getName(),1,50,"NAME");
            String initial = PinyinUtil.getPinYinHeadUperChar(tbBrand.getName());
            tbBrand.setLetter(initial.substring(0,1));
            tbBrandService.insert(tbBrand);
        } else {
            if(StringUtils.isNotBlank(tbBrand.getName())){
                String initial = PinyinUtil.getPinYinHeadUperChar(tbBrand.getName());
                tbBrand.setLetter(initial.substring(0,1));
            } else {
                tbBrand.setLetter(null);
            }
            tbBrandService.update(tbBrand);
        }
        return setSuccessModelMap(modelMap,tbBrand);
    }

    @ApiOperation(value = "删除", notes = "删除")
    @DeleteMapping
    public Object delete(ModelMap modelMap, @RequestBody TbBrand tbBrand){
        Assert.notNull(tbBrand.getId(),"ID");
        tbBrandService.deleteById(tbBrand.getId());
        return setSuccessModelMap(modelMap);
    }

}

