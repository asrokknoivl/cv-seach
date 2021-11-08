package fr.univ_lyon1.info.m1.cv_search.model;

import fr.univ_lyon1.info.m1.cv_search.App;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StrategyAverage implements IStrategy{
    MoyenneAll stats = new MoyenneAll();
    @Override
    public HashMap<String, Object> isAccepted(Applicant a, int val, Model model) {
        double moyenne = (double) stats.stats(a, val, model).get("moyenne");
        boolean allSelected = (boolean) stats.stats(a, val, model).get("allSelect");
        HashMap<String, Object> values = new HashMap<>();
        values.put("moyenne", moyenne);
        boolean isAccepted = moyenne>= val;
        values.put("accepted", isAccepted);
        return values;
    }
}
