package com.game.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 武器实体类，对应Excel中的武器数据
 */
@Data // Lombok注解，自动生成get/set/toString
public class Weapon {
    // Excel列名映射
    @ExcelProperty("武器名称")
    private String weaponName;

    @ExcelProperty("基础属性")
    private String baseAttr;

    @ExcelProperty("附加属性")
    private String extraAttr;

    @ExcelProperty("技能属性")
    private String skillAttr;
}