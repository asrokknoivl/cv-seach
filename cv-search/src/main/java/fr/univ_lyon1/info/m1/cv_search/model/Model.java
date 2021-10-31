package fr.univ_lyon1.info.m1.cv_search.model;

import javafx.scene.Node;
import javafx.scene.control.Button;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private StrategyList strategies = new StrategyList();
    private ApplicantList applicants = new ApplicantListBuilder(new File(".")).build(); //initialized
    private ApplicantList resApplicants = new ApplicantList();
    private SkillList skills = new SkillList();
    private Strategy currentStrategy = new Strategy("All >= 50");
    //SKILLS
    public void addSkill(Skill s){
        skills.addSkill(s);
    }
    public void removeSkill(Skill s){skills.removeSkill(s); }
    public void clearSkills(){skills.clear();}
    public void removeSkill(String s){skills.removeSkill(s);}

    //STRATEGIES
    //initialize current strategies into the app
    public void initStrategies(){
        Strategy all50 = new Strategy("All >= 50");
        Strategy all60 = new Strategy("All >= 60");
        Strategy avg50 = new Strategy("Average >= 50");
        strategies.addSkill(all50);
        strategies.addSkill(all60);
        strategies.addSkill(avg50);
    }

    //filter the applicants based on skills
    public void filterApplicants(Strategy strategy){
        String strategyValue = strategy.getStrategy();
        String[] s = strategyValue.split(" ");
        boolean all = (s[0].equals("All"));
        int n = Integer.parseInt(s[2]);
        resApplicants.clear();
        for (Applicant a : applicants) {
            double moyenne = 0;
            boolean selected = true;
            double nom = 0;
            double denom = 0;
            for (Skill skl : skills) {
                String sklValue = skl.getSkill();
                nom += a.getSkill(sklValue);
                denom += 1;
                if (a.getSkill(sklValue) < n) {
                    selected = false;
                }
            }
            moyenne = nom / denom;
            if (!all && moyenne >= 50) {
                selected = true;
                }
            if (selected){
                resApplicants.add(a);
            }
            }
        }

    public SkillList getSkills() {
        return skills;
    }

    public void setSkills(SkillList skills) {
        this.skills = skills;
    }

    public StrategyList getStrategies() {
        return strategies;
    }

    public void setStrategies(StrategyList strategies) {
        this.strategies = strategies;
    }

    public ApplicantList getResApplicants() {
        return resApplicants;
    }

    public void setResApplicants(ApplicantList applicants) {
        this.resApplicants = applicants;
    }

    public ApplicantList getApplicants() {
        return applicants;
    }

    public void setApplicants(ApplicantList applicants) {
        this.applicants = applicants;
    }

    public Strategy getCurrentStrategy() {
        return currentStrategy;
    }

    public String getCurrentStrategyS() {
        return currentStrategy.getStrategy();
    }
    public void setCurrentStrategy(Strategy currentStrategy) {
        this.currentStrategy = currentStrategy;
        System.out.println(currentStrategy.getStrategy());
    }
}
