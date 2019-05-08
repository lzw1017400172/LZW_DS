package com.lzw.item.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LZW on 2019/5/7.
 */
@Data
public class SpecGroup implements Serializable {

    /**
     * group
     */
    private String group;

    private List<SpecParam> params;

}
