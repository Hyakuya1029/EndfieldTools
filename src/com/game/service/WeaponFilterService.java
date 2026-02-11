package com.game.service;

import com.game.model.Matrix;
import com.game.model.Weapon;

import java.util.ArrayList;
import java.util.List;

/**
 * 武器筛选服务，实现词条匹配逻辑
 */
public class WeaponFilterService {

    /**
     * 根据基质词条筛选武器
     * @param weapons 所有武器列表
     * @param matrix  筛选用的基质
     * @param isExactMatch 是否精准匹配（false=模糊匹配）
     * @return 匹配的武器列表
     */
    public List<Weapon> filterWeapons(List<Weapon> weapons, Matrix matrix, boolean isExactMatch) {
        List<Weapon> matchedWeapons = new ArrayList<>();

        if (weapons == null || matrix == null) {
            return matchedWeapons;
        }

        for (Weapon weapon : weapons) {
            // 匹配逻辑：三个词条至少满足一个（可根据需求调整）
            boolean baseMatch = matchAttr(weapon.getBaseAttr(), matrix.getBaseAttr(), isExactMatch);
            boolean extraMatch = matchAttr(weapon.getExtraAttr(), matrix.getExtraAttr(), isExactMatch);
            boolean skillMatch = matchAttr(weapon.getSkillAttr(), matrix.getSkillAttr(), isExactMatch);

            // 筛选规则：三个词条至少一个匹配（可自定义，比如“基础属性必须匹配+附加/技能二选一”）
            if (baseMatch || extraMatch || skillMatch) {
                matchedWeapons.add(weapon);
            }
        }
        return matchedWeapons;
    }

    /**
     * 单个词条的匹配逻辑
     * @param weaponAttr 武器的属性值
     * @param matrixAttr 基质的筛选词条
     * @param isExactMatch 是否精准匹配
     * @return 匹配结果
     */
    private boolean matchAttr(String weaponAttr, String matrixAttr, boolean isExactMatch) {
        // 若基质词条为空，默认匹配（不筛选该维度）
        if (matrixAttr == null || matrixAttr.trim().isEmpty()) {
            return true;
        }
        // 武器属性为空，不匹配
        if (weaponAttr == null || weaponAttr.trim().isEmpty()) {
            return false;
        }
        // 精准/模糊匹配
        return isExactMatch ? weaponAttr.equals(matrixAttr) : weaponAttr.contains(matrixAttr);
    }
}