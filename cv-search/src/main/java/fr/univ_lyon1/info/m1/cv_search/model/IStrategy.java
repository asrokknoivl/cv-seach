package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.HashMap;
import java.util.List;

public interface IStrategy {
    public HashMap<String, Object> isAccepted(Applicant a, int val, Model model);
}
