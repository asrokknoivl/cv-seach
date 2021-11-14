package fr.univ_lyon1.info.m1.cv_search.model.skill;

import fr.univ_lyon1.info.m1.cv_search.model.Element;

public class Skill extends Element {
    private String skill;
    private int experience;

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
    public Skill(String skill) {
        this.skill = skill;
    }
    public Skill(String skill, int exp) {
        this.skill = skill;
        this.experience = exp;
    }
    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
