package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.Collection;

public class SkillModel implements IModel{
    ListFactory listFactory;
    private IElementList skills;
    private final IElementList validSkills;
    private Model model;
    private int validSize;

    public int getValidSize() {
        return validSize;
    }

    public void decrementValidSize(){
        this.validSize--;
    }
    public void setValidSize(int validSize) {
        this.validSize = validSize;
    }
    public SkillModel(Model model, ListFactory listFactory){
        this.listFactory = listFactory;
        this.model = model;
        skills  = (IElementList) listFactory.getListOfElements("Skills");
        validSkills = (IElementList) listFactory.getListOfElements("Skills");
        validSize = validSkills.size();
    }
    public void initValidSkills(){
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
    public void addSkill(Skill s){
        skills.addElement(s);
    }
    public void removeSkill(Skill s){skills.removeElement(s); }
    public void clearSkills(){skills.clear();}
    public SkillList getSkills() {
        return (SkillList) skills;
    }

    public void setSkills(SkillList skills) {
        this.skills = skills;
    }
    public boolean isValid(Skill skill){
        for (Skill s : (SkillList)validSkills){
            if (s.getSkill().equals(skill.getSkill().toLowerCase())){
                return true;
            }
        }
        return false;
    }
    public int sizeSkills(){
        return skills.size();
    }
}
