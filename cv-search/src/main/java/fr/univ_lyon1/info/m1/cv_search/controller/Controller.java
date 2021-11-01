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

    //Implementation of the 'Facade' and 'delegation' patterns--------------------------------------------
    public Object get(String what) {
        switch (what){
            case "s": return model.getSkillModel().getSkills(); //s : skills
            case "a": return model.getApplicantModel().getApplicants(); // a : applicants
            case "r": return model.getApplicantModel().getResApplicants(); // r : result applicants
            case "st": return model.getStrategyModel().getStrategies(); // st : strategies
            case "css": return model.getStrategyModel().getCurrentStrategyS(); // cs : current strategy : String
            case "cs": return model.getStrategyModel().getCurrentStrategy(); // cs : current strategy : Strategy
        }
        return null;
    }
    public void add(String what, Element e){
        switch (what){
            case "s": model.getSkillModel().addSkill((Skill) e); notifyAllObservers_Skills();
        }
    }
    public void remove(String what, Element e){
        switch (what){
            case "s": model.getSkillModel().removeSkill((Skill) e); notifyAllObservers_Skills();
        }
    }
    public void set(String what, Element e){
        switch (what){
            case "cs": model.getStrategyModel().setCurrentStrategy((Strategy) e); notifyAllObservers_Strategy();
        }
    }
    public void execute(String what, Element e){
      switch (what){
          case "filter": model.getApplicantModel().filterApplicants((Strategy) e); notifyAllObservers_Applicants();

      }
    }
}
