package fr.univ_lyon1.info.m1.cv_search.model.strategy;

import fr.univ_lyon1.info.m1.cv_search.model.Element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StrategyList implements Iterable<Strategy> {
    private List<Strategy> strategies = new ArrayList<Strategy>();

    @Override
    public Iterator<Strategy> iterator() {
        return strategies.iterator();
    }
    public void addElement(Element e) {
        strategies.add((Strategy) e);
    }
    public void removeElement(Element e) {
        strategies.remove((Strategy) e);
    }
    public void clear() {
        strategies.clear();
    }
    public int size() {
        return strategies.size();
    }

    public void setSkills(StrategyList strategyList) {
        this.strategies = strategyList.strategies;
    }
    public List<Strategy> getSkills() {
        return strategies;
    }

}
