package fr.univ_lyon1.info.m1.cv_search.model.strategy;

import fr.univ_lyon1.info.m1.cv_search.model.Model;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.Applicant;
import fr.univ_lyon1.info.m1.cv_search.model.skill.Skill;

import java.util.HashMap;

public class MoyenneAll {
    public HashMap<String, Object> stats(Applicant a, int n, Model model) {
        HashMap<String, Object>  map = new HashMap<String, Object>();
        int size  = model.getSkillModel().sizeSkills();
        double nom = 0;
        boolean allSelected = true;
        for (Skill skl: model.getSkillModel().getSkills()) {
            int sklVal = 0;
            String sklValue = skl.getSkill().toLowerCase();
            sklVal = a.getSkill(sklValue);
            nom += sklVal;

            if (sklVal < n) {
                allSelected = false;
            }
        }
        map.put("moyenne", nom / size);
        map.put("allSelect", allSelected);
        return map;
    }

}
