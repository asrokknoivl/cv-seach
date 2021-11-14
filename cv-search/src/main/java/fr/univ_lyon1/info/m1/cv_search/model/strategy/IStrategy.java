package fr.univ_lyon1.info.m1.cv_search.model.strategy;

import fr.univ_lyon1.info.m1.cv_search.model.Model;
import fr.univ_lyon1.info.m1.cv_search.model.applicant.Applicant;

import java.util.HashMap;

public interface IStrategy {
    HashMap<String, Object> isAccepted(Applicant a, int val, Model model);
}
