package fr.univ_lyon1.info.m1.cv_search.model;

import java.io.File;

public class ListFactory {
    public Object getListOfElements(String list) {
        switch (list) {
            case "Applicants:init": return new ApplicantListBuilder(new File(".")).build();
            case "Applicants": return new ApplicantList();
            case "Skills": return new SkillList();
            case "Strategies": return new StrategyList();
            case "Experiences": return new ExperienceList();
            default: System.out.println();
        }
        return null;
    }
}
