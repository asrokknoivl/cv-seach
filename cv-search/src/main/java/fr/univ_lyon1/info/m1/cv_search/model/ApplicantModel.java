package fr.univ_lyon1.info.m1.cv_search.model;

public class ApplicantModel extends IModel {
    private ListFactory listFactory;
    private ApplicantList applicants;
    private ApplicantList resApplicants;
    private Model model;
    private IStrategy sAll = new StrategyAll();
    private IStrategy sHrmnc = new StrategyHarmonic();
    private IStrategy sAvg = new StrategyAverage();
    private int profExp = 0;
    public ApplicantModel(Model model, ListFactory listFactory) {
        this.model = model;
        this.listFactory = listFactory;
        applicants = (ApplicantList) listFactory.getListOfElements("Applicants:init");
        resApplicants = (ApplicantList) listFactory.getListOfElements("Applicants");
    }

    public void setProfExp(int pexp) {
        this.profExp = pexp;
    }
    public int getProfExp() {
        return profExp;
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

    public int sizeAa() {
        return applicants.size();
    }

    public int sizeR() {
        return resApplicants.size();
    }

    public void filterApplicants(int val, IStrategy strategy) {
        resApplicants.clear();
        for (Applicant a :(ApplicantList) applicants) {
            boolean accepted = (boolean) strategy.isAccepted(a, val, model).get("accepted");
            boolean expChecked = filterExperience(a);
            int pexp = model.getApplicantModel().getProfExp();
            if (accepted && expChecked && a.getProfessionalExp() >= pexp) {
                double moy = (double) strategy.isAccepted(a, val, model).get("moyenne");
                a.setMoyenne(moy);
                boolean notAdded = true;
                int i = 0;
                for (Applicant ra: resApplicants) {
                    if (moy >= ra.getMoyenne()) {
                        resApplicants.addElementAtIndex(i, a);
                        notAdded = false;
                        break;
                    } else {
                        i++;
                    }
                }

                if (notAdded) {
                    resApplicants.addElement(a);
                    continue;
                }
            }
        }
    }
    public void filterApplicantsByAll(int val) {
        filterApplicants(val, sAll);
    }
    public void filterApplicantsByAverage(int val) {
        filterApplicants(val, sAvg);
    }
    public void filterApplicantsByHarmonicAverage(int val) {
        filterApplicants(val, sHrmnc);
    }
    public boolean filterApplicantByExperience(Applicant a, Experience exp) {
        for (Experience e : (ExperienceList) a.getExperiences()) {
            if (e.getCompany().toLowerCase().equals(exp.getCompany().toLowerCase())) {
                if (exp.getDurationS().equals("undefined")) {
                    return true;
                } else {
                    if (e.getDuration() >= Integer.parseInt(exp.getDurationS())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean filterExperience(Applicant a) {
        for (Experience exp: (ExperienceList) model.getExperienceModel().getExperiences()) {
            if (!filterApplicantByExperience(a, exp)) {
                return false;
            }
        }
        return true;
    }

}
