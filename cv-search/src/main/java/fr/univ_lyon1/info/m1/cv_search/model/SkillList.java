package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SkillList implements Iterable<Skill>{
    private List<Skill> skills = new ArrayList<Skill>();
    @Override
    public Iterator<Skill> iterator() {
        return skills.iterator();
    }

    public void addSkill(Skill s){
        skills.add(s);
    }
    public void removeSkill(Skill s){
        skills.remove(s);
    }
    public void clear(){
        skills.clear();
    }
    public int size(){
        return skills.size();
    }
    public void setSkills(SkillList skillList){
        this.skills = skillList.skills;
    }
    public List<Skill> getSkills(){
        return skills;
    }
    public void removeSkill(String s){
        skills.removeIf(skill -> skill.getSkill().equals(s));
    }


}
