package com.game;

import com.game.model.Matrix;
import com.game.model.Weapon;
import com.game.service.WeaponFilterService;
import com.game.util.ExcelReaderUtil;

import java.util.List;
import java.util.Scanner;

/**
 * 程序入口类
 */
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. 输入Excel文件路径
        System.out.print("请输入武器Excel文件路径（如：./weapon_data.xlsx）：");
        String excelPath = scanner.nextLine();

        // 2. 加载武器数据
        List<Weapon> allWeapons = ExcelReaderUtil.readWeaponsFromExcel(excelPath);
        if (allWeapons == null || allWeapons.isEmpty()) {
            System.out.println("未读取到武器数据，请检查文件路径！");
            return;
        }
        System.out.println("成功加载 " + allWeapons.size() + " 条武器数据");

        // 3. 输入基质词条
        Matrix matrix = new Matrix();
        System.out.print("请输入基质的基础属性词条（为空则不筛选）：");
        matrix.setBaseAttr(scanner.nextLine());
        System.out.print("请输入基质的附加属性词条（为空则不筛选）：");
        matrix.setExtraAttr(scanner.nextLine());
        System.out.print("请输入基质的技能属性词条（为空则不筛选）：");
        matrix.setSkillAttr(scanner.nextLine());

        // 4. 选择匹配模式
        System.out.print("是否精准匹配？（true=精准，false=模糊）：");
        boolean isExactMatch = Boolean.parseBoolean(scanner.nextLine());

        // 5. 筛选武器
        WeaponFilterService filterService = new WeaponFilterService();
        List<Weapon> matchedWeapons = filterService.filterWeapons(allWeapons, matrix, isExactMatch);

        // 6. 输出结果
        System.out.println("\n===== 筛选结果 =====");
        if (matchedWeapons.isEmpty()) {
            System.out.println("未找到匹配的武器！");
        } else {
            for (Weapon weapon : matchedWeapons) {
                System.out.println("武器名称：" + weapon.getWeaponName());
                System.out.println("基础属性：" + weapon.getBaseAttr());
                System.out.println("附加属性：" + weapon.getExtraAttr());
                System.out.println("技能属性：" + weapon.getSkillAttr());
                System.out.println("-------------------");
            }
        }

        scanner.close();
    }
}