package com.game.util;

import com.alibaba.excel.EasyExcel;
import com.game.model.Weapon;

import java.util.List;

/**
 * Excel读取工具，加载武器数据
 */
public class ExcelReaderUtil {

    /**
     * 读取Excel中的武器数据
     * @param filePath Excel文件路径（如：./weapon_data.xlsx）
     * @return 武器列表
     */
    public static List<Weapon> readWeaponsFromExcel(String filePath) {
        try {
            // EasyExcel读取Excel（无监听器，直接返回列表）
            return EasyExcel.read(filePath)
                    .head(Weapon.class) // 指定实体类映射
                    .sheet(0) // 读取第一个sheet
                    .doReadSync(); // 同步读取
        } catch (Exception e) {
            System.err.println("读取Excel失败：" + e.getMessage());
            return null;
        }
    }
}
