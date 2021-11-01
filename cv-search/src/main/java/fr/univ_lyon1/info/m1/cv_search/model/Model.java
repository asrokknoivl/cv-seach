package fr.univ_lyon1.info.m1.cv_search.model;

import javafx.scene.Node;
import javafx.scene.control.Button;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Model {
    public SkillModel getSkillModel() {
        return skillModel;
    }

    public ApplicantModel getApplicantModel() {
        return applicantModel;
    }

    public StrategyModel getStrategyModel() {
        return strategyModel;
    }

    private SkillModel skillModel = new SkillModel(this);
    private ApplicantModel applicantModel = new ApplicantModel(this);
    private StrategyModel strategyModel = new StrategyModel(this);

}
