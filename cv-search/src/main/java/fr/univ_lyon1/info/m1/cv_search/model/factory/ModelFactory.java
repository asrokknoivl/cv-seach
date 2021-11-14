package fr.univ_lyon1.info.m1.cv_search.model.factory;

import fr.univ_lyon1.info.m1.cv_search.model.Model;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.ApplicantModel;
import fr.univ_lyon1.info.m1.cv_search.model.experience.ExperienceModel;
import fr.univ_lyon1.info.m1.cv_search.model.skill.SkillModel;
import fr.univ_lyon1.info.m1.cv_search.model.strategy.StrategyModel;

public class ModelFactory {
    private Model model;
    private ListFactory listFactory;
    public ModelFactory(Model model, ListFactory listFactory) {
        this.model = model;
        this.listFactory = listFactory;
    }
    public Object getListOfElements(String list) {
        switch (list) {
            case "A": return new ApplicantModel(model, listFactory);
            case "S": return new SkillModel(model, listFactory);
            case "ST": return new StrategyModel(model, listFactory);
            case "E": return new ExperienceModel(model, listFactory);
            default: System.out.println();
        }
        return null;
    }
}
