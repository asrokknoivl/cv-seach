package fr.univ_lyon1.info.m1.cv_search.controller;

import fr.univ_lyon1.info.m1.cv_search.model.Element;
import fr.univ_lyon1.info.m1.cv_search.model.Skill;
import fr.univ_lyon1.info.m1.cv_search.model.Strategy;
import fr.univ_lyon1.info.m1.cv_search.model.Experience;
import fr.univ_lyon1.info.m1.cv_search.model.Model;
import fr.univ_lyon1.info.m1.cv_search.model.ViewObserver;

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

    //Observer pattern implementation
    public void attach(ViewObserver view) {
        views.add(view);
    }

    public void notifyAllObserversSkills() {
        for (ViewObserver view : views) {
            view.updateSkills();
        }
    }
    public void notifyAllObserversStrategy() {
        for (ViewObserver view : views) {
            view.updateStrategy();
        }
    }
    public void notifyAllObserversApplicants() {
        for (ViewObserver view : views) {
            view.updateApplicants();
        }
    }
    public void notifyAllObserversExperiences() {
        for (ViewObserver view : views) {
            view.updateExperiences();
        }
    }

    //Implementation of the 'Facade' and 'delegation' patterns
    public Object get(String what) {
        switch (what) {
            case "s": return model.getSkillModel().getSkills();
            //s : skills
            case "a": return model.getApplicantModel().getApplicants();
            // a : applicants
            case "r": return model.getApplicantModel().getResApplicants();
            // r : result applicants
            case "st": return model.getStrategyModel().getStrategies();
            // st : strategies
            case "css": return model.getStrategyModel().getCurrentStrategyS();
            // cs : current strategy : String
            case "cs": return model.getStrategyModel().getCurrentStrategy();
            // cs : current strategy : Strategy
            case "exp": return model.getExperienceModel().getExperiences();
            // exp : experience :')
            case "pexp": return model.getApplicantModel().getProfExp();
            // pexp : Professional experience :')
            default: System.out.println();
        }
        return null;
    }
    public void add(String what, Element e) {
        switch (what) {
            case "s": model.getSkillModel().addSkill((Skill) e); notifyAllObserversSkills(); break;
            case "exp": model.getExperienceModel().addExp((Experience) e);
                        notifyAllObserversExperiences(); break;
            default:System.out.println();
        }
    }

    public void remove(String what, Element e) {
        switch (what) {
            case "s": model.getSkillModel().removeSkill((Skill) e);
                      notifyAllObserversSkills(); break;
            case "exp": model.getExperienceModel().removeExp((Experience) e);
                        notifyAllObserversExperiences(); break;
            default: System.out.println();
        }

    }
    public void set(String what, Element e) {
        switch (what) {
            case "cs": model.getStrategyModel().setCurrentStrategy((Strategy) e);
                       notifyAllObserversStrategy();
            default: System.out.println();
        }
    }

    public void setProfExp(int pexp) {
        model.getApplicantModel().setProfExp(pexp);
        notifyAllObserversApplicants();
    }
    public void filterAll(int val) {
        model.getApplicantModel().filterApplicantsByAll(val);
        notifyAllObserversApplicants();
    }
    public void filterAvg(int val) {
        model.getApplicantModel().filterApplicantsByAverage(val);
        notifyAllObserversApplicants();
    }
    public void filterHrmnc(int val) {
        model.getApplicantModel().filterApplicantsByHarmonicAverage(val);
        notifyAllObserversApplicants();
    }
}
