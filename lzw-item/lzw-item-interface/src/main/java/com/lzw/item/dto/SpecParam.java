package com.lzw.item.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZW on 2019/5/7.
 */
@Data
public class SpecParam implements Serializable {

    /**
     * 规格名
     */
    private String k;
    /**
     * 是否检索
     */
    private boolean searchable;
    private boolean global;
    /**
     * 备选值
     */
    private List<String> options;

}
