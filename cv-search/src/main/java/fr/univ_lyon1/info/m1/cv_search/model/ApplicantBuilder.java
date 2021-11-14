package fr.univ_lyon1.info.m1.cv_search.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class ApplicantBuilder {

    private File file;

    public ApplicantBuilder(File f) {
        this.file = f;
    }

    public ApplicantBuilder(String filename) {
        this.file = new File(filename);
    }

    /**
     * Build the applicant from the Yaml file provided to the constructor.
     */
    public Applicant build() {
        Applicant a = new Applicant();
        Yaml yaml = new Yaml();
        Map<String, Object> map;
        int profExp = 0;
        try {
            map = yaml.load(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new Error(e);
        }

        a.setName((String) map.get("name"));

        // Cast may fail if the Yaml is incorrect. Ideally we should provide
        // clean error messages.
        @SuppressWarnings("unchecked")
        Map<String, Integer> skills = (Map<String, Integer>) map.get("skills");

        ExperienceList expList = new ExperienceList();

        for (String skill : skills.keySet()) {
            Integer value = skills.get(skill);
            a.setSkill(skill, value);
        }



        Map<String, HashMap> experiences = (Map<String, HashMap>) map.get("experience");

        for (String exp : experiences.keySet()) {
            Experience e = new Experience();
            e.setCompany(exp);
            Map<String, Object> expSpecs = experiences.get(exp);
            e.setStartDate((int) expSpecs.get("start"));
            e.setEndDate((int) expSpecs.get("end"));
            e.setKeywords((ArrayList) expSpecs.get("keywords"));
            e.setDuration((int) expSpecs.get("end") - (int) expSpecs.get("start"));
            expList.addElement(e);
            profExp += ((int) expSpecs.get("end") - (int) expSpecs.get("start"));
        }
        a.setProfessionalExp(profExp);
        a.setExperiences(expList);
        return a;
    }
}
