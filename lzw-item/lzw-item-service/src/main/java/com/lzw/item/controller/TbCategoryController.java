package com.lzw.item.controller;

import com.lzw.core.base.BaseController;
import com.lzw.item.pojo.TbCategory;
import com.lzw.item.pojo.TbCategoryBrand;
import com.lzw.item.service.ITbCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系  前端控制器
 * </p>
 *
 * @author liuzw
 * @since 2019-04-07
 */
@Controller
@RequestMapping("/tbCategory")
@Api(value = "商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系", description = "商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系")
public class TbCategoryController extends BaseController {

	@Autowired
	private ITbCategoryService tbCategoryService;

	@ApiOperation(value = "查询商品类目表，类目和商品(spu)是一对多关系，类目与品牌是多对多关系")
	@GetMapping(value = "/read/list")
	public Object list(ModelMap modelMap,Long pid) {
        Map<String,Object> param = new HashMap<>();
		param.put("parentId",pid);
        return setSuccessModelMap(modelMap,tbCategoryService.queryList(param));
	}
	
	@ApiOperation(value = "查询分类，根据品牌id")
	@GetMapping(value = "/bid/{bid}")
	public Object readByBId(ModelMap modelMap,@PathVariable("bid") Long bid) {
		if(bid != null){
			List<TbCategory> list = tbCategoryService.selectByBrandId(bid);
			return setSuccessModelMap(modelMap,list);
		} else {
			return setSuccessModelMap(modelMap,new ArrayList<>());
		}
	}


}