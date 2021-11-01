package fr.univ_lyon1.info.m1.cv_search.model;

public class StrategyModel {
    private StrategyList strategies = new StrategyList();
    private Strategy currentStrategy = new Strategy("All >= 50");
    private Model model;

    public StrategyModel(Model model){
        this.model = model;
    }
    //STRATEGIES
    //initialize current strategies into the app
    public void initStrategies(){
        Strategy all50 = new Strategy("All >= 50");
        Strategy all60 = new Strategy("All >= 60");
        Strategy avg50 = new Strategy("Average >= 50");
        strategies.addSkill(all50);
        strategies.addSkill(all60);
        strategies.addSkill(avg50);
    }

    public StrategyList getStrategies() {
        return strategies;
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
