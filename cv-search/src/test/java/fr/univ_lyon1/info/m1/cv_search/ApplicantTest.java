package fr.univ_lyon1.info.m1.cv_search;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import fr.univ_lyon1.info.m1.cv_search.model.Model;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.*;
import fr.univ_lyon1.info.m1.cv_search.model.experience.Experience;
import fr.univ_lyon1.info.m1.cv_search.model.experience.ExperienceModel;
import fr.univ_lyon1.info.m1.cv_search.model.factory.ListFactory;
import fr.univ_lyon1.info.m1.cv_search.model.factory.ModelFactory;
import fr.univ_lyon1.info.m1.cv_search.model.skill.Skill;
import fr.univ_lyon1.info.m1.cv_search.model.skill.SkillList;
import fr.univ_lyon1.info.m1.cv_search.model.skill.SkillModel;
import fr.univ_lyon1.info.m1.cv_search.model.strategy.IStrategy;
import fr.univ_lyon1.info.m1.cv_search.model.strategy.StrategyAll;
import org.junit.jupiter.api.Test;

public class ApplicantTest {

    @Test
    public void testReadApplicant() {
        // Given
        ApplicantBuilder builder = new ApplicantBuilder("applicant1.yaml");

        // When
        Applicant a = builder.build();

        // Then
        assertThat(70, is(a.getSkill("c++")));
        assertThat("John Smith", is(a.getName()));
        assertThat(2 + 2, is(4)); // TODO: Obviously incorrect, hence should fail. Make sure it does and remove this buggy assertion.
    }

    @Test
    public void testReadManyApplicant() {
        // Given
        ApplicantListBuilder builder = new ApplicantListBuilder(new File("."));

        // When
        ApplicantList list = builder.build();

        // Then
        boolean johnFound = false;
        for (Applicant a : list) {
            if (a.getName().equals("John Smith")) {
                assertThat(90, is(a.getSkill("c")));
                assertThat(70, is(a.getSkill("c++")));
                johnFound = true;
            }
        }
        assertThat(johnFound, is(true));
    }
    @Test
    public void testNone() { // testing None strategy
        //Given
        Model model = new Model();
        ListFactory lf = new ListFactory();
        ModelFactory mf = new ModelFactory(model, lf);
        ApplicantModel am = (ApplicantModel) mf.getListOfElements("A");
        SkillModel slist = (SkillModel) mf.getListOfElements("S");
        slist.getSkills().addElement(new Skill("C++"));
        slist.getSkills().addElement(new Skill("python"));
        am.filterApplicantsByAll(0);

        assertThat(am.getResApplicants().getList().size(), is(4));

    }
    @Test
    public void testAll50() { // testing all >= 50 strategy
        //Given
        Model model = new Model();
        ListFactory lf = new ListFactory();
        ModelFactory mf = new ModelFactory(model, lf);
        ApplicantModel am = (ApplicantModel) mf.getListOfElements("A");
        SkillModel slist = (SkillModel) mf.getListOfElements("S");
        slist.getSkills().addElement(new Skill("C++"));
        slist.getSkills().addElement(new Skill("python"));
        am.filterApplicantsByAll(50);
        boolean allAbove50 = true;
        for (Applicant a: am.getResApplicants()) {
            for (String s : a.getSkills().keySet()) {
                if (a.getSkills().get(s) < 50 && (s.equals("C++") || s.equals("python"))) {
                    System.out.println(a.getSkills().get(s));
                    allAbove50 = false;
                }
            }
        }
        assertThat(allAbove50, is(true));

    }

    @Test
    public void testAvg50() { // testing average >= 50 strategy
        //Given
        Model model = new Model();
        ListFactory lf = new ListFactory();
        ModelFactory mf = new ModelFactory(model, lf);
        ApplicantModel am = (ApplicantModel) mf.getListOfElements("A");
        SkillModel slist = (SkillModel) mf.getListOfElements("S");
        slist.getSkills().addElement(new Skill("C++"));
        slist.getSkills().addElement(new Skill("python"));
        am.filterApplicantsByAverage(50);
        boolean allAbove50 = true;
        for (Applicant a: am.getResApplicants()){
            if (a.getMoyenne() < 50){
                allAbove50 = false;
            }
        }
        assertThat(allAbove50, is(true));

    }
    @Test
    public void testHrmnc50() { // testing harmonic mean >= 50 strategy
        //Given
        Model model = new Model();
        ListFactory lf = new ListFactory();
        ModelFactory mf = new ModelFactory(model, lf);
        ApplicantModel am = (ApplicantModel) mf.getListOfElements("A");
        SkillModel slist = (SkillModel) mf.getListOfElements("S");
        slist.getSkills().addElement(new Skill("C++"));
        slist.getSkills().addElement(new Skill("python"));
        am.filterApplicantsByHarmonicAverage(50);
        boolean allAbove50 = true;
        for (Applicant a: am.getResApplicants()){
            if (a.getMoyenne() < 50){
                allAbove50 = false;
            }
        }
        assertThat(allAbove50, is(true));

    }
    public void testAddApplicant() { // testing add applicant func
        //Given
        Model model = new Model();
        ListFactory lf = new ListFactory();
        ModelFactory mf = new ModelFactory(model, lf);
        ApplicantModel am = (ApplicantModel) mf.getListOfElements("A");

        assertThat(am.getResApplicants().size(), is(0));
        am.getResApplicants().addElement(new Applicant());
        assertThat(am.getResApplicants().size(), is(1));

    }
    public void testdeleteApplicant() { // testing delete applicant func
        //Given
        Model model = new Model();
        ListFactory lf = new ListFactory();
        ModelFactory mf = new ModelFactory(model, lf);
        ApplicantModel am = (ApplicantModel) mf.getListOfElements("A");

        assertThat(am.getResApplicants().size(), is(0));
        Applicant a = new Applicant();
        am.getResApplicants().addElement(a);
        assertThat(am.getResApplicants().size(), is(1));
        am.getResApplicants().removeElement(a);
        assertThat(am.getResApplicants().size(), is(0));
    }
    public void testaddAndRemoveSkill() { // testing add and remove funcs for skills
        //Given
        Model model = new Model();
        ListFactory lf = new ListFactory();
        ModelFactory mf = new ModelFactory(model, lf);
        SkillModel slist = (SkillModel) mf.getListOfElements("S");


        assertThat(slist.getSkills().size(), is(0));
        Skill s = new Skill("Apple");
        slist.getSkills().addElement(s);
        assertThat(slist.getSkills().size(), is(1));
        slist.getSkills().removeElement(s);
        assertThat(slist.getSkills().size(), is(0));
    }
    public void testaddAndRemoveExperience() { // testing adding and remove experiences funcs
        //Given
        Model model = new Model();
        ListFactory lf = new ListFactory();
        ModelFactory mf = new ModelFactory(model, lf);
        ExperienceModel exp = (ExperienceModel) mf.getListOfElements("E");


        assertThat(exp.getExperiences().size(), is(0));
        Experience s = new Experience();
        exp.getExperiences().addElement(s);
        assertThat(exp.getExperiences().size(), is(1));
        exp.getExperiences().removeElement(s);
        assertThat(exp.getExperiences().size(), is(0));
    }
    public void testingInvaldSkills() { //
        //Given
        Model model = new Model();
        ListFactory lf = new ListFactory();
        ModelFactory mf = new ModelFactory(model, lf);
        SkillModel slist = (SkillModel) mf.getListOfElements("S");
        ApplicantModel am = (ApplicantModel) mf.getListOfElements("A");
        slist.getSkills().addElement(new Skill("dazndjakzd"));
        slist.getSkills().addElement(new Skill("da15d3az1d3ad1"));
        slist.getSkills().addElement(new Skill("dzdz6222za3d++"));
        am.filterApplicantsByAverage(50);
        for (Applicant a : am.getResApplicants()){
            assertThat(a.getMoyenne(), is(0));
        }
    }
    public void testingIdenticalSkills() { //
        //Given
        Model model = new Model();
        ListFactory lf = new ListFactory();
        ModelFactory mf = new ModelFactory(model, lf);
        SkillModel slist = (SkillModel) mf.getListOfElements("S");
        ApplicantModel am = (ApplicantModel) mf.getListOfElements("A");
        slist.getSkills().addElement(new Skill("C++"));
        slist.getSkills().addElement(new Skill("C++"));
        slist.getSkills().addElement(new Skill("python"));
        am.filterApplicantsByAverage(50);
        for (Applicant a : am.getResApplicants()){
            assertThat(a.getMoyenne(), is((a.getSkills().get("C++")+a.getSkills().get("python"))/2));
        }
    }






}
