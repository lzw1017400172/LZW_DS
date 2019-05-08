package com.lzw.item.pojo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import java.io.Serializable;

/**
 * <p>
 * 商品规格参数模板，json格式。
 * </p>
 *
 * @author liuzw
 * @since 2019-04-30
 */

@TableName("tb_specification")
@Data
public class TbSpecification implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 规格模板所属商品分类id
     */
	@TableId("category_id")
	private Long categoryId;
    /**
     * 规格参数模板，json格式
     */
    @TableField("specifications")
	private String specifications;

}