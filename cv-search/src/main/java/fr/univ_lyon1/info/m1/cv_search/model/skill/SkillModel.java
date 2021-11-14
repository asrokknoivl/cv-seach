package fr.univ_lyon1.info.m1.cv_search.model.skill;

import fr.univ_lyon1.info.m1.cv_search.model.IModel;
import fr.univ_lyon1.info.m1.cv_search.model.factory.ListFactory;
import fr.univ_lyon1.info.m1.cv_search.model.Model;

public class SkillModel extends IModel {
    private ListFactory listFactory;
    private SkillList skills;
    private final SkillList validSkills;
    private Model model;
    private int validSize;

    public int getValidSize() {
        return validSize;
    }

    public void decrementValidSize() {
        this.validSize--;
    }
    public void setValidSize(int validSize) {
        this.validSize = validSize;
    }
    public SkillModel(Model model, ListFactory listFactory) {
        this.listFactory = listFactory;
        this.model = model;
        skills  = (SkillList) listFactory.getListOfElements("Skills");
        validSkills = (SkillList) listFactory.getListOfElements("Skills");
        validSize = validSkills.size();
    }
    public void initValidSkills() { // when I added this part below,I was hoping to
                                    // accept only certain skill values that are defined by
                                    // the developer, but it doesn't seem so practical
                                    // after a second thought, will keep it nonetheless
                                    // for another use in the future.
        validSkills.addElement(new Skill("c"));
        validSkills.addElement(new Skill("c++"));
        validSkills.addElement(new Skill("java"));
        validSkills.addElement(new Skill("python"));
        validSkills.addElement(new Skill("c#"));
        validSkills.addElement(new Skill("html"));
        validSkills.addElement(new Skill("css"));
        validSkills.addElement(new Skill("js"));
        validSkills.addElement(new Skill("javascript"));
        validSkills.addElement(new Skill("kotlin"));
        validSkills.addElement(new Skill("asp"));
        validSkills.addElement(new Skill("xml"));
        validSkills.addElement(new Skill("go"));
        validSkills.addElement(new Skill("prolog"));
    }
    public void addSkill(Skill s) {
        skills.addElement(s);
    }
    public void removeSkill(Skill s) {
        skills.removeElement(s); }
    public void clearSkills() {
        skills.clear();
    }
    public SkillList getSkills() {
        return (SkillList) skills;
    }

    public void setSkills(SkillList skills) {
        this.skills = skills;
    }
    public boolean isValid(Skill skill) {
        for (Skill s : (SkillList) validSkills) {
            if (s.getSkill().equals(skill.getSkill().toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    public int sizeSkills() {
        return skills.size();
    }
}
