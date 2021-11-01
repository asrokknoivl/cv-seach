package fr.univ_lyon1.info.m1.cv_search.model;

import java.io.File;

public class ApplicantModel{
    private ApplicantList applicants = new ApplicantListBuilder(new File(".")).build(); //initialized
    private ApplicantList resApplicants = new ApplicantList();
    private Model model;
    public ApplicantModel(Model model){
        this.model = model;
    }

    public ApplicantList getResApplicants() {
        return resApplicants;
    }

    public void setResApplicants(ApplicantList applicants) {
        this.resApplicants = applicants;
    }

    public ApplicantList getApplicants() {
        return applicants;
    }

    public void setApplicants(ApplicantList applicants) {
        this.applicants = applicants;
    }

    public void filterApplicants(Strategy strategy){
        String strategyValue = strategy.getStrategy();
        String[] s = strategyValue.split(" ");
        boolean all = (s[0].equals("All"));
        int n = Integer.parseInt(s[2]);
        resApplicants.clear();
        for (Applicant a : applicants) {
            double moyenne = 0;
            boolean selected = true;
            double nom = 0;
            double denom = 0;
            for (Skill skl : model.getSkillModel().getSkills()) {
                System.out.println(skl.getSkill());
                String sklValue = skl.getSkill();
                nom += a.getSkill(sklValue);
                denom += 1;
                if (a.getSkill(sklValue) < n) {
                    selected = false;
                }
            }
            moyenne = nom / denom;
            if (!all && moyenne >= 50) {
                selected = true;
            }
            if (selected){
                a.setMoyenne(moyenne);
                resApplicants.add(a);
                System.out.println(moyenne);
            }
        }

    }

}
