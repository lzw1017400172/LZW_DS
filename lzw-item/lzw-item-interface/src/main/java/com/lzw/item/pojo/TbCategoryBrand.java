package com.lzw.item.pojo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import java.io.Serializable;

/**
 * <p>
 * 商品分类和品牌的中间表，两者是多对多关系
 * </p>
 *
 * @author liuzw
 * @since 2019-04-07
 */

@TableName("tb_category_brand")
@Data
public class TbCategoryBrand implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商品类目id
     */
	@TableField("category_id")
	private Long categoryId;
    /**
     * 品牌id
     */
	@TableField("brand_id")
	private Long brandId;

}