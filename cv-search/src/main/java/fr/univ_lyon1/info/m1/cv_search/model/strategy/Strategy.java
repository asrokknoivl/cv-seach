package fr.univ_lyon1.info.m1.cv_search.model.strategy;

import fr.univ_lyon1.info.m1.cv_search.model.Element;

public class Strategy extends Element {
    private String strategy;

    public Strategy(String strategy) {
        this.strategy = strategy;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String skill) {
        this.strategy = strategy;
    }
}
