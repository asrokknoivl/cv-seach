package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SkillList implements Iterable<Skill>, IElementList{
    private List<Skill> skills = new ArrayList<Skill>();
    @Override
    public Iterator<Skill> iterator() {
        return skills.iterator();
    }

    @Override
    public void addElement(Element e) {
        skills.add((Skill) e);
    }

    @Override
    public void removeElement(Element e) {
        skills.remove((Skill) e);
    }

    @Override
    public void clear(){
        skills.clear();
    }
    @Override
    public int size(){
        return skills.size();
    }


    public void setSkills(SkillList skillList){
        this.skills = skillList.skills;
    }

    public List<Skill> getSkills(){
        return skills;
    }

}
