package com.lzw.item.core.config;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZW on 2019/4/7.
 */
@Configuration
public class JsonConfig {
    /**
     * 配置fasetjson--解决js精度
     * @return
     */
    @Bean
    FastJsonHttpMessageConverter fastJsonHttpMessageConverter(){
        //创建fastJson消息转换器
        FastJsonHttpMessageConverter fastJsonConverter =new FastJsonHttpMessageConverter();
        //创建配置类
        FastJsonConfig fastJsonConfig  = new FastJsonConfig();
        //修改配置返回内容的过滤
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.DisableCircularReferenceDetect,   //消除对同一对象循环引用的问题，默认为false
                SerializerFeature.WriteMapNullValue,    //是否输出值为null的字段,默认为false
                //SerializerFeature.WriteNullNumberAsZero,    //数值字段如果为null,输出为0,而非null
                SerializerFeature.WriteNullStringAsEmpty,   //字符类型字段如果为null,输出为"",而非null
                SerializerFeature.WriteNullListAsEmpty,     //List字段如果为null,输出为[],而非null
                SerializerFeature.WriteNullBooleanAsFalse  //Boolean字段如果为null,输出为false,而非null
        );

        // 日期格式
        //fastJsonConfig.dateFormat = "yyyy-MM-dd HH:mm:ss";

        // long精度问题
        SerializeConfig serializeConfig = SerializeConfig.globalInstance;
        serializeConfig.put(BigInteger.class, ToStringSerializer.instance);
        serializeConfig.put(Long.class, ToStringSerializer.instance);
        serializeConfig.put(Long.TYPE, ToStringSerializer.instance);
        fastJsonConfig.setSerializeConfig(serializeConfig);

        //处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<MediaType>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);

        fastJsonConverter.setSupportedMediaTypes(fastMediaTypes);
        fastJsonConverter.setFastJsonConfig(fastJsonConfig);
        return fastJsonConverter;
    }

}
