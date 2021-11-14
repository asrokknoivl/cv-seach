package fr.univ_lyon1.info.m1.cv_search.model;

import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantModel;
import fr.univ_lyon1.info.m1.cv_search.model.experience.ExperienceModel;
import fr.univ_lyon1.info.m1.cv_search.model.factory.ListFactory;
import fr.univ_lyon1.info.m1.cv_search.model.factory.ModelFactory;
import fr.univ_lyon1.info.m1.cv_search.model.skill.SkillModel;
import fr.univ_lyon1.info.m1.cv_search.model.strategy.StrategyModel;

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

    public ExperienceModel getExperienceModel() {
        return (ExperienceModel) experienceModel;
    }

    private ListFactory listFactory = new ListFactory();
    private ModelFactory modelFactory = new ModelFactory(this, listFactory);
    private IModel skillModel = (SkillModel) modelFactory.getListOfElements("S");
    private IModel applicantModel = (ApplicantModel) modelFactory.getListOfElements("A");
    private IModel strategyModel = (StrategyModel) modelFactory.getListOfElements("ST");
    private IModel experienceModel = (ExperienceModel) modelFactory.getListOfElements("E");
}
