package fr.univ_lyon1.info.m1.cv_search.model;

import fr.univ_lyon1.info.m1.cv_search.App;

public class ApplicantModel implements IModel{
    ListFactory listFactory;
    private IElementList applicants;
    private IElementList resApplicants;
    private Model model;
    private IStrategy sAll = new StrategyAll();
    private IStrategy sHrmnc = new StrategyHarmonic();
    private IStrategy sAvg = new StrategyAverage();

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

    public void filterApplicants(int val, IStrategy strategy){
        resApplicants.clear();
        for (Applicant a :(ApplicantList) applicants) {
            boolean accepted = (boolean) strategy.isAccepted(a, val, model).get("accepted");
            boolean expChecked = filterExperience(a);
            if (accepted && expChecked){
                double moy = (double) strategy.isAccepted(a, val, model).get("moyenne");
                a.setMoyenne(moy);
                resApplicants.addElement(a);
            }
        }
    }
    public void filterApplicantsByAll(int val){
        filterApplicants(val ,sAll);
    }
    public void filterApplicantsByAverage(int val){
        filterApplicants(val ,sAvg);
    }
    public void filterApplicantsByHarmonicAverage(int val){
        filterApplicants(val ,sHrmnc);
    }
    public boolean filterApplicantByExperience(Applicant a, Experience exp) {
        for (Experience e : (ExperienceList) a.getExperiences()) {
            if (e.getCompany().toLowerCase().equals(exp.getCompany().toLowerCase())) {
                if(exp.getDurationS().equals("undefined")){
                    return true;
                }else{
                    System.out.println(e.getDuration());
                    System.out.println(exp.getDurationS());
                    if (e.getDuration() >= Integer.parseInt(exp.getDurationS())){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean filterExperience(Applicant a){
        for (Experience exp: (ExperienceList)model.getExperienceModel().getExperiences()){
            if (!filterApplicantByExperience(a, exp)){
                return false;
            }
        }
        return true;
    }

}
