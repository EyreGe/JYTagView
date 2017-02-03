package com.tagdemo;

import java.util.List;

/**
 * Created by Flynn on 16/12/28.
 */

public class SkillBean {
    /**
     * name : 技术合伙人
     * choosed : true
     */

    private List<SkillsBean> skills;

    public List<SkillsBean> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillsBean> skills) {
        this.skills = skills;
    }

    public static class SkillsBean {
        private String name;
        private boolean choosed;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isChoosed() {
            return choosed;
        }

        public void setChoosed(boolean choosed) {
            this.choosed = choosed;
        }
    }
}
