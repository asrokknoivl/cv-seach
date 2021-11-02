package fr.univ_lyon1.info.m1.cv_search.model;

import javafx.scene.Node;
import javafx.scene.control.Button;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Model {
    public SkillModel getSkillModel() {
        return (SkillModel) skillModel;
    }

    public ApplicantModel getApplicantModel() {
        return (ApplicantModel) applicantModel;
    }

    public StrategyModel getStrategyModel() {
        return (StrategyModel) strategyModel;
    }

    private ListFactory listFactory = new ListFactory();
    private ModelFactory modelFactory = new ModelFactory(this, listFactory);
    private IModel skillModel = (SkillModel) modelFactory.getListOfElements("Skills");
    private IModel applicantModel = (ApplicantModel) modelFactory.getListOfElements("Applicants");
    private IModel strategyModel = (StrategyModel) modelFactory.getListOfElements("Strategies");

}
