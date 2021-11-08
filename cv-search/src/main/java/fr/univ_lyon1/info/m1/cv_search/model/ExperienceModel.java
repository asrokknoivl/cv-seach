package fr.univ_lyon1.info.m1.cv_search.model;

public class ExperienceModel implements IModel{
    ListFactory listFactory;
    private IElementList experiences;
    private Model model;

    public ExperienceModel(Model model, ListFactory listFactory){
        this.model = model;
        this.listFactory = listFactory;
        experiences = (IElementList) listFactory.getListOfElements("Experiences");
    }

}
