package fr.univ_lyon1.info.m1.cv_search.model.applicant;

import fr.univ_lyon1.info.m1.cv_search.model.Element;
import fr.univ_lyon1.info.m1.cv_search.model.experience.ExperienceList;

import java.util.HashMap;
import java.util.Map;

public class Applicant extends Element {
    private Map<String, Integer> skills = new HashMap<>();
    private ExperienceList experiences;
    private String name;
    private double moyenne = 0;
    private int professionalExp = 0;
    public double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(double moyenne) {
        this.moyenne = moyenne;
    }

    public int getSkill(String string) {
        return skills.getOrDefault(string, 0);
    }

    public void setSkill(String string, int value) {
        skills.put(string, value);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExperienceList getExperiences() {
        return experiences;
    }

    public void setExperiences(ExperienceList experiences) {
        this.experiences = experiences;
    }

    public Map<String, Integer> getSkills() {
        return skills;
    }

    public void setSkills(Map<String, Integer> skills) {
        this.skills = skills;
    }

    public int getProfessionalExp() {
        return professionalExp;
    }

    public void setProfessionalExp(int professionalExp) {
        this.professionalExp = professionalExp;
    }


}
