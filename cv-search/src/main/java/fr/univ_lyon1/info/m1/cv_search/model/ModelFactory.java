package fr.univ_lyon1.info.m1.cv_search.model;


import java.io.File;

public class ModelFactory {
    Model model;
    ListFactory listFactory;
    public ModelFactory(Model model, ListFactory listFactory){
        this.model = model;
        this.listFactory = listFactory;
    }
    public Object getListOfElements(String list){
        switch (list){
            case "Applicants": return new ApplicantModel(model, listFactory);
            case "Skills": return new SkillModel(model, listFactory);
            case "Strategies": return new StrategyModel(model, listFactory);
        }
        return null;
    }
}
