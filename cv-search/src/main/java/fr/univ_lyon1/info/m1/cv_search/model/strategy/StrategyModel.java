package fr.univ_lyon1.info.m1.cv_search.model.strategy;

import fr.univ_lyon1.info.m1.cv_search.model.IModel;
import fr.univ_lyon1.info.m1.cv_search.model.factory.ListFactory;
import fr.univ_lyon1.info.m1.cv_search.model.Model;

public class StrategyModel extends IModel {
    private ListFactory listFactory;
    private StrategyList strategies;
    private Strategy currentStrategy = new Strategy("None");
    private Model model;

    public StrategyModel(Model model, ListFactory listFactory) {
        this.model = model;
        this.listFactory = listFactory;
        strategies = (StrategyList) listFactory.getListOfElements("Strategies");
    }
    //STRATEGIES
    //initialize current strategies into the app
    public void initStrategies() {
        Strategy none = new Strategy("None");
        Strategy all50 = new Strategy("All >= 50");
        Strategy all60 = new Strategy("All >= 60");
        Strategy avg50 = new Strategy("Average >= 50");
        Strategy hrm50 = new Strategy("Harmonic Mean >= 50");
        strategies.addElement(none);
        strategies.addElement(all50);
        strategies.addElement(all60);
        strategies.addElement(avg50);
        strategies.addElement(hrm50);
    }

    public StrategyList getStrategies() {
        return (StrategyList) strategies;
    }

    public void setStrategies(StrategyList strategies) {
        this.strategies = strategies;
    }

    public Strategy getCurrentStrategy() {
        return currentStrategy;
    }

    public void setCurrentStrategy(Strategy currentStrategy) {
        this.currentStrategy = currentStrategy;
    }

    public String getCurrentStrategyS() {
        return currentStrategy.getStrategy();
    }

}
