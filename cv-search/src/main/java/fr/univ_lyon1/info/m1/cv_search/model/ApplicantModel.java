package fr.univ_lyon1.info.m1.cv_search.model;

import java.io.File;
import java.util.List;

public class ApplicantModel implements IModel{
    ListFactory listFactory;
    private IElementList applicants;
    private IElementList resApplicants;
    private Model model;
    public ApplicantModel(Model model, ListFactory listFactory){
        this.model = model;
        this.listFactory = listFactory;
        applicants = (ApplicantList) listFactory.getListOfElements("Applicants:init");
        resApplicants = (ApplicantList) listFactory.getListOfElements("Applicants");
    }

    public ApplicantList getResApplicants() {
        return (ApplicantList) resApplicants;
    }

    public void setResApplicants(ApplicantList applicants) {
        this.resApplicants = applicants;
    }

    public ApplicantList getApplicants() {
        return (ApplicantList) applicants;
    }

    public void setApplicants(ApplicantList applicants) {
        this.applicants = applicants;
    }

    public void filterApplicants(Strategy strategy){
        System.out.println("here");
        String strategyValue = strategy.getStrategy();
        String[] s = strategyValue.split(" ");
        boolean all = (s[0].equals("All"));
        int n = Integer.parseInt(s[2]);
        resApplicants.clear();
        for (Applicant a :(ApplicantList) applicants) {
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
                resApplicants.addElement(a);
                System.out.println(moyenne);
            }
        }

    }

}
