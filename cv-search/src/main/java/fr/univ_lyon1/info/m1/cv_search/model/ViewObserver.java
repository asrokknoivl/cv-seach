package fr.univ_lyon1.info.m1.cv_search.model;

import fr.univ_lyon1.info.m1.cv_search.controller.Controller;

public abstract class ViewObserver {
    public Controller getCtrl() {
        return ctrl;
    }

    public void setCtrl(Controller ctrl) {
        this.ctrl = ctrl;
    }

    private Controller ctrl;
    public abstract void updateSkills();
    public abstract void updateStrategy();
    public abstract void updateApplicants();
    public abstract void updateExperiences();
    public abstract void updateProfExp();
}
