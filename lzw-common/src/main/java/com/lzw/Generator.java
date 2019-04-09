package com.lzw;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * 代码生成 注意：不生成service接口 注意：不生成service接口 注意：不生成service接口
 * 
 * @author LZW
 */
public class Generator {
	/**
	 * 测试 run 执行 注意：不生成service接口 注意：不生成service接口 注意：不生成service接口
	 * <p>
	 * 配置方法查看 {@link ConfigGenerator}
	 * </p>
	 */
	public static void main(String[] args) {
	
		
		AutoGenerator mpg = new AutoGenerator();
		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		gc.setOutputDir("D:/");
		gc.setFileOverride(false);
		gc.setActiveRecord(false);
		gc.setEnableCache(false);// XML 二级缓存
		gc.setBaseResultMap(false);// XML ResultMap
		gc.setBaseColumnList(false);// XML columList
		gc.setOpen(false);
		gc.setAuthor("liuzw");
		// 自定义文件命名，注意 %s 会自动填充表实体属性！
		// gc.setMapperName("%sDao");
		// gc.setXmlName("%sDao");
		gc.setServiceImplName("%sService");
		// gc.setServiceImplName("%sServiceDiy");
		// gc.setControllerName("%sAction");
		mpg.setGlobalConfig(gc);
		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		dsc.setDriverName("com.mysql.jdbc.Driver");
		dsc.setUsername("root");
		dsc.setPassword("rootroot");
		dsc.setUrl("jdbc:mysql://localhost:3306/ds?characterEncoding=utf8");
		mpg.setDataSource(dsc);
		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		// strategy.setTablePrefix("sys_");// 此处可以修改为您的表前缀
		strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
		strategy.setInclude(new String[] {"tb_category_brand","tb_category" }); // 需要生成的表
		// strategy.setExclude(new String[]{"test"}); // 排除生成的表
		// 自定义实体父类
		//strategy.setSuperEntityClass("com.deehow.core.base.BaseModel");
		// 自定义实体，公共字段
		//strategy.setSuperEntityColumns(
		//		new String[] { "id_", "enable_", "remark_", "create_by", "create_time", "update_by", "update_time"});
		// 自定义 mapper 父类
		strategy.setSuperMapperClass("com.lzw.core.base.BaseMapper");
		// 自定义 service 父类
		strategy.setSuperServiceClass("com.lzw.core.base.BaseService");
		// 自定义 service 实现类父类
		strategy.setSuperServiceImplClass("com.lzw.core.base.BaseServiceImpl");
		// 自定义 controller 父类
		strategy.setSuperControllerClass("com.lzw.core.base.BaseController");
		// 【实体】是否生成字段常量（默认 false）
		// public static final String ID = "test_id";
		// strategy.setEntityColumnConstant(true);
		// 【实体】是否为构建者模型（默认 false）
		// public User setName(String name) {this.name = name; return this;}
		// strategy.setEntityBuliderModel(true);
		mpg.setStrategy(strategy);
		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent("com.lzw");
		pc.setEntity("model");
		pc.setMapper("mapper");
		pc.setXml("mapper.xml");
		pc.setServiceImpl("service");
		pc.setService("ignore");
		pc.setController("web");
		mpg.setPackageInfo(pc);
//		 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/template 下面内容修改，
//		 放置自己项目的 src/main/resources/template 目录下, 默认名称一下可以不配置，也可以自定义模板名称
		TemplateConfig tc = new TemplateConfig();
		tc.setEntity("tpl/entity.java.vm");
		tc.setMapper("tpl/mapper.java.vm");
		tc.setXml("tpl/mapper.xml.vm");
		tc.setService("tpl/service.java.vm");
		tc.setController("tpl/controller.java.vm");
		mpg.setTemplate(tc);
		// 执行生成
		mpg.execute();
		System.out.println("1233211234567");
	}
}
