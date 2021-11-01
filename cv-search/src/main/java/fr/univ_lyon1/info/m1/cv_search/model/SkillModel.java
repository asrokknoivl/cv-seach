package fr.univ_lyon1.info.m1.cv_search.model;

public class SkillModel {
    private SkillList skills = new SkillList();
    private Model model;
    public SkillModel(Model model){
        this.model = model;
    }
    public void addSkill(Skill s){
        skills.addSkill(s);
    }
    public void removeSkill(Skill s){skills.removeSkill(s); }
    public void clearSkills(){skills.clear();}
    public void removeSkill(String s){skills.removeSkill(s);}
    public SkillList getSkills() {
        return skills;
    }

    public void setSkills(SkillList skills) {
        this.skills = skills;
    }

}
