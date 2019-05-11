package com.lzw.item.dto;

import com.lzw.item.pojo.TbSku;
import com.lzw.item.pojo.TbSpu;
import com.lzw.item.pojo.TbSpuDetail;
import lombok.Data;

import java.util.List;

@Data
public class SpuBo extends TbSpu {

    private String cname;

    private String bname;

    private TbSpuDetail spuDetail;

    private List<TbSku> skus;
}
