package com.game.model;

import lombok.Data;

/**
 * 基质实体类，存储筛选用的词条
 */
@Data
public class Matrix {
    private String baseAttr;    // 基础属性词条
    private String extraAttr;   // 附加属性词条
    private String skillAttr;   // 技能属性词条
}
