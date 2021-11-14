package fr.univ_lyon1.info.m1.cv_search.controller;

import fr.univ_lyon1.info.m1.cv_search.model.*;
import fr.univ_lyon1.info.m1.cv_search.view.View;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private Model model;
    private List<ViewObserver> views = new ArrayList<ViewObserver>();

    public Controller(Model model) {
        this.model = model;
        model.getStrategyModel().initStrategies();
        model.getSkillModel().initValidSkills();
    }

    //Observer pattern implementation-------------------------------------------------------------------
    public void attach(ViewObserver view){
        views.add(view);
    }
    public void notifyAllObservers_Skills(){
        for (ViewObserver view : views){
            view.updateSkills();
        }
    }
    public void notifyAllObservers_Strategy(){
        for (ViewObserver view : views){
            view.updateStrategy();
        }
    }
    public void notifyAllObservers_Applicants(){
        for (ViewObserver view : views){
            view.updateApplicants();
        }
    }
    public void notifyAllObservers_Experiences(){
        for (ViewObserver view : views){
            view.updateExperiences();
        }
    }

    //Implementation of the 'Facade' and 'delegation' patterns--------------------------------------------
    public Object get(String what) {
        switch (what){
            case "s": return model.getSkillModel().getSkills(); //s : skills
            case "a": return model.getApplicantModel().getApplicants(); // a : applicants
            case "r": return model.getApplicantModel().getResApplicants(); // r : result applicants
            case "st": return model.getStrategyModel().getStrategies(); // st : strategies
            case "css": return model.getStrategyModel().getCurrentStrategyS(); // cs : current strategy : String
            case "cs": return model.getStrategyModel().getCurrentStrategy(); // cs : current strategy : Strategy
            case "exp": return model.getExperienceModel().getExperiences(); // exp : experience :')
            case "pexp": return model.getApplicantModel().getProfExp(); // pexp : Professional experience :')
        }
        return null;
    }
    public void add(String what, Element e){
        switch (what){
            case "s": model.getSkillModel().addSkill((Skill) e);notifyAllObservers_Skills();break;
            case "exp": model.getExperienceModel().addExp((Experience) e);notifyAllObservers_Experiences();break;
        }

    }
    public void remove(String what, Element e){
        switch (what){
            case "s": model.getSkillModel().removeSkill((Skill) e);notifyAllObservers_Skills();break;
            case "exp": model.getExperienceModel().removeExp((Experience) e);notifyAllObservers_Experiences();break;
        }

    }
    public void set(String what, Element e){
        switch (what){
            case "cs": model.getStrategyModel().setCurrentStrategy((Strategy) e); notifyAllObservers_Strategy();
        }
    }
    public void setProfExp(int pexp){
        model.getApplicantModel().setProfExp(pexp);
        notifyAllObservers_Applicants();
    }
    public void filter(String strategy){
        try{
            int val = Integer.parseInt(strategy.split(" ")[strategy.split(" ").length-1]);
            String type = strategy.split(" ")[0];
            System.out.println(type);
            switch (type){
                case "All": model.getApplicantModel().filterApplicantsByAll(val); break;
                case "Average": model.getApplicantModel().filterApplicantsByAverage(val); break;
                case "Harmonic": model.getApplicantModel().filterApplicantsByHarmonicAverage(val); break;
        }
        }catch (Exception e){
            model.getApplicantModel().filterApplicantsByAll(0);
        }
      notifyAllObservers_Applicants();
    }


}
