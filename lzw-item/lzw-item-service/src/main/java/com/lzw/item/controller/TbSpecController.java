package com.lzw.item.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lzw.core.base.BaseController;
import com.lzw.item.dto.SpecGroup;
import com.lzw.item.pojo.TbSpecification;
import com.lzw.item.service.ITbSpecificationService;
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
 * </p>
 *
 * @author liuzw
 * @since 2019-04-07
 */
@Controller
@RequestMapping("/spec")
@Api(value = "分类-规格模版", description = "分类-规格模版")
public class TbSpecController extends BaseController {

	@Autowired
	private ITbSpecificationService tbSpecificationService;

	@ApiOperation(value = "")
	@GetMapping(value = "/groups/{cid}")
	public Object groups(ModelMap modelMap,@PathVariable("cid") Long cid) {
        Map<String,Object> param = new HashMap<>();
		TbSpecification tbSpecification = new TbSpecification();
		tbSpecification.setCategoryId(cid);
		tbSpecification = tbSpecificationService.selectOne(tbSpecification);
		List<SpecGroup> list = new ArrayList<>();
		if(tbSpecification != null){
			list = JSONObject.parseArray(tbSpecification.getSpecifications(),SpecGroup.class);
		}
        return setSuccessModelMap(modelMap,list);
	}
	
//	@ApiOperation(value = "查询分类，根据品牌id")
//	@GetMapping(value = "/bid/{bid}")
//	public Object readByBId(ModelMap modelMap,@PathVariable("bid") Long bid) {
//		if(bid != null){
//			List<TbCategory> list = tbCategoryService.selectByBrandId(bid);
//			return setSuccessModelMap(modelMap,list);
//		} else {
//			return setSuccessModelMap(modelMap,new ArrayList<>());
//		}
//	}


}