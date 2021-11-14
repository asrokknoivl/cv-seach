package fr.univ_lyon1.info.m1.cv_search.model.strategy;

import fr.univ_lyon1.info.m1.cv_search.model.Model;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.Applicant;

import java.util.HashMap;

public class StrategyAll implements IStrategy {
    private MoyenneAll stats = new MoyenneAll();
    @Override
    public HashMap<String, Object> isAccepted(Applicant a, int val, Model model) {
        double moyenne = (double) stats.stats(a, val, model).get("moyenne");
        boolean allSelected = (boolean) stats.stats(a, val, model).get("allSelect");
        HashMap<String, Object> values = new HashMap<>();
        values.put("moyenne", moyenne);
        values.put("accepted", allSelected);
        return values;
    }
}
