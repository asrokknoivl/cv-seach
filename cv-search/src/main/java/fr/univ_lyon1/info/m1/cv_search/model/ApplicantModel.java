package fr.univ_lyon1.info.m1.cv_search.model;

import fr.univ_lyon1.info.m1.cv_search.App;

import java.io.File;
import java.util.ArrayList;
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

    public int size_a(){
        return applicants.size();
    }

    public int size_r(){
        return resApplicants.size();
    }

    public List<Object> stat(Applicant a, int n){
        List<Object> list = new ArrayList<Object>();
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
        list.add(nom/size);
        list.add(allSelected);
        return list;
    }
    public List<Object> isAccepted(Applicant a, String type, int val){
        List<Object> list = new ArrayList<Object>();
        boolean isAccepted = false;
        switch (type){
            case "All": list.add(stat(a, val).get(0)); isAccepted = (boolean) stat(a, val).get(1); list.add(isAccepted); return list;
            case "Average": list.add(stat(a, val).get(0)); isAccepted = (double)stat(a, val).get(0)>= val; list.add(isAccepted); return list;
            case "Harmonic": list.add(harmonicMean(a)); isAccepted = (double)harmonicMean(a)>= val; list.add(isAccepted); return list;
        }
        return null;
    }
    public void filterApplicants(String type, int val){
        resApplicants.clear();
        for (Applicant a :(ApplicantList) applicants) {
            if ((boolean) isAccepted(a, type, val).get(1)){
                a.setMoyenne((double) isAccepted(a, type, val).get(0));
                resApplicants.addElement(a);
            }
        }

    }
    public double harmonicMean(Applicant a)
    {
        double size = model.getSkillModel().sizeSkills();
        double sum = 0.0;
        for (Skill skl: model.getSkillModel().getSkills()){
            if (!model.getSkillModel().isValid(skl)) {
                size--;
                continue;
            }
            String sklValue = skl.getSkill().toLowerCase();
            sum += 1.0/a.getSkill(sklValue);
        }
        return size / sum;
    }
}
