package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.HashMap;

public interface IStrategy {
    HashMap<String, Object> isAccepted(Applicant a, int val, Model model);
}
