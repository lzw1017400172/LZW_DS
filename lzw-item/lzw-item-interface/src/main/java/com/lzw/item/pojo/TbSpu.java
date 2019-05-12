package com.lzw.item.pojo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * spu表，该表描述的是一个抽象性的商品，比如 iphone8
 * </p>
 *
 * @author LZW
 * @since 2019-04-06
 */

@TableName("tb_spu")
@Data
public class TbSpu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * spu id
     */
    @TableId("id")
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 子标题
     */
    @TableField("subTitle")
    private String subTitle;

    /**
     * 1级类目id
     */
    private Long cid1;
    /**
     * 2级类目id
     */
    private Long cid2;
    /**
     * 3级类目id
     */
    private Long cid3;
    /**
     * 商品所属品牌id
     */
    @TableField("brand_id")
    private Long brandId;
    /**
     * 是否上架，0下架，1上架
     */
    private Boolean saleable;
    /**
     * 是否有效，0已删除，1有效
     */
    private Boolean valid;
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
