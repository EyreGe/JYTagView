package com.tagdemo;

import java.util.List;
import java.util.Map;

/**
 * Created by Flynn on 16/12/26.
 */

public interface OnSkillTagSelectListener {
    void onItemSelect(SkillFlowTagLayout parent, List<Integer> selectedList);
    void onItemSelects(SkillFlowTagLayout parent, List<Map<Integer, Boolean>> selectedList);
    void onShowInput(String show);
}
