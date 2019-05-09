package com.lzw.item.pojo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * </p>
 *
 * @author LZW
 * @since 2019-04-06
 */

@TableName("tb_spu_detail")
@Data
public class TbSpuDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * spu id
     */
    @TableField("spu_id")
    private Long spu_id;
    /**
     * 商品描述信息
     */
    private String description;
    /**
     * 全部规格参数数据
     */
    private String specifications;
    /**
     * 特有规格参数及可选值信息，json格式
     */
    @TableField("spec_template")
    private String specTemplate;
    /**
     * 包装清单
     */
    @TableField("packing_list")
    private String packingList;
    /**
     * 售后服务
     */
    @TableField("after_service")
    private String afterService;
}
