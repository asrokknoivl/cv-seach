package fr.univ_lyon1.info.m1.cv_search.model;

public class ExperienceModel implements IModel{
    ListFactory listFactory;
    private ExperienceList experiences;
    private Model model;

    public ListFactory getListFactory() {
        return listFactory;
    }

    public void setListFactory(ListFactory listFactory) {
        this.listFactory = listFactory;
    }

    public ExperienceList getExperiences() {
        return experiences;
    }

    public void setExperiences(ExperienceList experiences) {
        this.experiences = experiences;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public ExperienceModel(Model model, ListFactory listFactory){
        this.model = model;
        this.listFactory = listFactory;
        experiences = (ExperienceList) listFactory.getListOfElements("Experiences");
    }
    public void addExp(Experience e){
        experiences.addElement(e);
    }
    public void removeExp(Experience e){
        experiences.removeElement(e);
    }

    public void clearExp(){
        experiences.clear();
    }


}
