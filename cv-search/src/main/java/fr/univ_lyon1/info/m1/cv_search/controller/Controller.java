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
        model.initStrategies();
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
    //----------------------------------------------------------------------------------------------------

    public Object get(String what) {
        switch (what){
            case "s": return model.getSkills(); //s : skills
            case "a": return model.getApplicants(); // a : applicants
            case "r": return model.getResApplicants(); // r : result applicants
            case "st": return model.getStrategies(); // st : strategies
            case "css": return model.getCurrentStrategyS(); // cs : current strategy : String
            case "cs": return model.getCurrentStrategy(); // cs : current strategy : Strategy
        }
        return null;
    }
    public void addSkill(Skill s){
        model.addSkill(s);
        notifyAllObservers_Skills();
    }
    public void removeSkill(Skill s){
        model.removeSkill(s);
        notifyAllObservers_Skills();
    }
    public void setCurrentStrategy(Strategy s){
        model.setCurrentStrategy(s);
        notifyAllObservers_Strategy();
    }
    public void filter(Strategy s){
        model.filterApplicants(s);
        notifyAllObservers_Applicants();
    }
}
