package fr.univ_lyon1.info.m1.cv_search.model;

public class SkillModel implements IModel{
    ListFactory listFactory;
    private IElementList skills;
    private Model model;
    public SkillModel(Model model, ListFactory listFactory){
        this.listFactory = listFactory;
        this.model = model;
        skills  = (IElementList) listFactory.getListOfElements("Skills");
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

}
