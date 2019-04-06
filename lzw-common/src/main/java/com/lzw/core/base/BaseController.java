package com.lzw.core.base;

import com.baomidou.mybatisplus.plugins.Page;
import com.lzw.core.util.HttpCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制器基类
 * Created by LZW on 2019/3/31.
 */
public abstract class BaseController {

    protected final Logger logger = LogManager.getLogger(this.getClass());

    /** 设置成功响应代码 */
    protected ResponseEntity<ModelMap> setSuccessModelMap(ModelMap modelMap) {
        return setSuccessModelMap(modelMap, null);
    }

    /** 设置成功响应代码 */
    protected ResponseEntity<ModelMap> setSuccessModelMap(ModelMap modelMap, Object data) {
        return setModelMap(modelMap, HttpCode.OK, data);
    }
    /** 设置成功响应代码 */
    protected ResponseEntity<ModelMap> setSuccessModelMapPage(ModelMap modelMap, Object data,Page page) {
        return setModelMapPage(modelMap, HttpCode.OK, data,page);
    }
    /** 设置响应代码 */
    protected ResponseEntity<ModelMap> setModelMap(ModelMap modelMap, HttpCode code) {
        return setModelMap(modelMap, code, null);
    }

    /** 设置响应代码 */
    protected ResponseEntity<ModelMap> setModelMap(ModelMap modelMap, HttpCode code, Object data) {
        Map<String, Object> map = new LinkedHashMap();
        map.putAll(modelMap);
        modelMap.clear();
        for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext();) {
            String key = iterator.next();
            if (!key.startsWith("org.springframework.validation.BindingResult") && !key.equals("void")) {
                modelMap.put(key, map.get(key));
            }
        }
        if (data != null) {
            if (data instanceof Page) {
                Page<?> page = (Page<?>) data;
                modelMap.put("data", page.getRecords());
                modelMap.put("current", page.getCurrent());
                modelMap.put("size", page.getSize());
                modelMap.put("pages", page.getPages());
                modelMap.put("total", page.getTotal());
                modelMap.put("iTotalRecords", page.getTotal());
                modelMap.put("iTotalDisplayRecords", page.getTotal());
            } else if (data instanceof List<?>) {
                modelMap.put("data", data);
                modelMap.put("iTotalRecords", ((List<?>) data).size());
                modelMap.put("iTotalDisplayRecords", ((List<?>) data).size());
            } else {
                modelMap.put("data", data);
            }
        }
        modelMap.put("httpCode", code.value());
        if(!modelMap.containsKey("msg")){
            modelMap.put("msg", code.msg());
        }
        modelMap.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.ok(modelMap);
    }
    /** 设置响应代码 */
    protected ResponseEntity<ModelMap> setModelMapPage(ModelMap modelMap, HttpCode code, Object data, Page page) {
        Map<String, Object> map = new LinkedHashMap();
        map.putAll(modelMap);
        modelMap.clear();
        for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext();) {
            String key = iterator.next();
            if (!key.startsWith("org.springframework.validation.BindingResult") && !key.equals("void")) {
                modelMap.put(key, map.get(key));
            }
        }
        if (data != null) {
            modelMap.put("data", data);
            modelMap.put("current", page.getCurrent());
            modelMap.put("size", page.getSize());
            modelMap.put("pages", page.getPages());
            modelMap.put("total", page.getTotal());
            modelMap.put("iTotalRecords", page.getTotal());
            modelMap.put("iTotalDisplayRecords", page.getTotal());
        }
        modelMap.put("httpCode", code.value());
        modelMap.put("msg", code.msg());
        modelMap.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.ok(modelMap);
    }

    /**
     * 统一异常处理
     */
//    @ExceptionHandler(Exception.class)
//    public void exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex) throws Exception{
//        ModelMap modelMap = new ModelMap();
//        modelMap.put("httpCode",500);
//        modelMap.put("msg","系统走神了,请稍候再试." );
//        modelMap.put("timestamp", System.currentTimeMillis());
//        response.setContentType("application/json;charset=UTF-8");
//        byte[] bytes = JSON.toJSONBytes(modelMap, SerializerFeature.DisableCircularReferenceDetect);
//        response.getOutputStream().write(bytes);
//    }


}
