package fr.univ_lyon1.info.m1.cv_search.model.experience;

import fr.univ_lyon1.info.m1.cv_search.model.Element;

import java.util.ArrayList;
import java.util.List;

public class Experience extends Element {
    private String company;
    private int duration;
    private String durationS;
    private int startDate;
    private int endDate;

    public Experience() { };
    public Experience(String company, int duration) {
        this.company = company;
        this.duration = duration;
    }
    public Experience(String company, String duration) {
        this.company = company;
        this.durationS = duration;
    }

    private List<String> keywords = new ArrayList<>();

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getStartDate() {
        return startDate;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public String getDurationS() {
        return durationS;
    }

    public void setDurationS(String durationS) {
        this.durationS = durationS;
    }


}
