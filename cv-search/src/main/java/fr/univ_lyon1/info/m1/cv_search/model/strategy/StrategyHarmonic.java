package fr.univ_lyon1.info.m1.cv_search.model.strategy;

import fr.univ_lyon1.info.m1.cv_search.model.Model;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.skill.Skill;

import java.util.HashMap;

public class StrategyHarmonic implements IStrategy {
    @Override
    public HashMap<String, Object> isAccepted(Applicant a, int val, Model model) {
        double moyenne = harmonicMean(a, model);
        HashMap<String, Object> values = new HashMap<>();
        values.put("moyenne", moyenne);
        boolean isAccepted = moyenne >= val;
        values.put("accepted", isAccepted);
        return values;

    }
    public double harmonicMean(Applicant a, Model model) {
        double size = model.getSkillModel().sizeSkills();
        double sum = 0.0;
        for (Skill skl: model.getSkillModel().getSkills()) {
            if (!model.getSkillModel().isValid(skl)) {
                size--;
                continue;
            }
            String sklValue = skl.getSkill().toLowerCase();
            sum += 1.0 / a.getSkill(sklValue);
        }
        return size / sum;
    }

}
