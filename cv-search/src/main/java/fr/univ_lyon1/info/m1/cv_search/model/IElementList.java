package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.List;

public interface IElementList {
    void addElement(Element e);
    void removeElement(Element e);
    void clear();
    int size();
}
