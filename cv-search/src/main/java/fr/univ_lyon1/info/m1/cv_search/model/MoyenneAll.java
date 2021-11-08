package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.HashMap;

public class MoyenneAll {
    public HashMap<String, Object> stats(Applicant a, int n, Model model){
        HashMap<String, Object>  map = new HashMap<String, Object>();
        int size  = model.getSkillModel().sizeSkills();
        double nom = 0;
        boolean allSelected = true;
        for (Skill skl: model.getSkillModel().getSkills()){
            if (!model.getSkillModel().isValid(skl)) {
                size--;
                continue;
            }
            String sklValue = skl.getSkill().toLowerCase();
            nom += a.getSkill(sklValue);
            if (a.getSkill(sklValue) < n) {
                allSelected = false;
            }
        }
        map.put("moyenne", nom/size);
        map.put("allSelect", allSelected);
        return map;
    }

}
