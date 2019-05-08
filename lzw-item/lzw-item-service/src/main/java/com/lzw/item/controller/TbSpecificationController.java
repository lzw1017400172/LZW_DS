package com.lzw.item.controller;

import com.lzw.core.base.BaseController;
import com.lzw.item.pojo.TbSpecification;
import com.lzw.item.service.ITbSpecificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 商品规格参数模板，json格式。  前端控制器
 * </p>
 *
 * @author liuzw
 * @since 2019-04-30
 */
@Controller
@RequestMapping("/tbSpecification")
@Api(value = "商品规格参数模板，json格式。", description = "商品规格参数模板，json格式。")
public class TbSpecificationController extends BaseController {

	@Autowired
	private ITbSpecificationService tbSpecificationService;

	@ApiOperation(value = "查询商品规格参数模板，json格式。")
	@GetMapping(value = "/read/list")
	public Object list(ModelMap modelMap) {
        Map<String,Object> param = new HashMap<>();
        //
        return setSuccessModelMap(modelMap,tbSpecificationService.queryList(param));
	}
	
	@ApiOperation(value = "分页商品规格参数模板，json格式。")
	@GetMapping(value = "/read/page")
	public Object query(ModelMap modelMap,
        String pageNum,String pageSize,String orderBy,boolean openSort,boolean asc) {
        Map<String,Object> param = new HashMap<>();
        //
        param.put("pageNum",pageNum);
        param.put("pageSize",pageSize);
        param.put("orderBy",orderBy);
        param.put("openSort",openSort);
        param.put("asc",asc);
        return setSuccessModelMap(modelMap,tbSpecificationService.query(param));
	}
	
	@ApiOperation(value = "商品规格参数模板，json格式。详情")
	@PostMapping(value = "/read/detail/{id}")
	public Object get(ModelMap modelMap,@PathVariable("id") Long id) {
        return setSuccessModelMap(modelMap,tbSpecificationService.queryById(id));
	}

	@PostMapping
	@ApiOperation(value = "修改商品规格参数模板，json格式。")
	public Object update(ModelMap modelMap, @RequestBody TbSpecification param) {
		return null;
	}

	@DeleteMapping
	@ApiOperation(value = "删除商品规格参数模板，json格式。")
	public Object delete(ModelMap modelMap, @RequestBody TbSpecification param) {
		return null;
	}
}