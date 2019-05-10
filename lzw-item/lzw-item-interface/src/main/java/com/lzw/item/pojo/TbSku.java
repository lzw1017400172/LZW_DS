package com.lzw.item.pojo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *     sku，取决于SPU维护时特殊属性的多选，自动计算出所有组合--就是sku
 * </p>
 *
 * @author LZW
 * @since 2019-04-06
 */

@TableName("tb_sku")
@Data
public class TbSku implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * sku id
     */
    @TableId("id")
    private Long id;
    /**
     * spu id
     */
    @TableField("spu_id")
    private Long spuId;
    /**
     * 商品标题
     */
    private String title;
    /**
     * 商品的图片，多个图片以‘,’分割
     */
    private String images;
    /**
     * 销售价格，单位为分
     */
    private Long price;
    /**
     * 特有规格属性在spu属性模板中的对应下标组合
     * 保证spu特殊属性模板的有序，这种方式能够快速定位 sku
     */
    private String indexes;
    /**
     * sku的特有规格参数键值对，json格式，反序列化时请使用linkedHashMap，保证有序
     * 每个sku对应的特殊属性值冗余存，方便使用
     */
    @TableField("own_spec")
    private String ownSpec;
    /**
     * 是否有效，0无效，1有效
     */
    private Boolean enable;
    /**
     * 添加时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 最后修改时间
     */
    @TableField("last_update_time")
    private Date lastUpdateTime;
}
