package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ApplicantList implements Iterable<Applicant>, IElementList{

    private List<Applicant> list = new ArrayList<Applicant>();

    @Override
    public Iterator<Applicant> iterator() {
        return list.iterator();
    }

    @Override
    public void addElement(Element e) {
        list.add((Applicant) e);
    }
    @Override
    public void removeElement(Element e) {
        list.remove((Applicant) e);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public int size() {
        return list.size();
    }

    public void setList(ApplicantList list) {
        this.list = list.list;
    }
    public List<Applicant> getList() {
        return list;
    }

}
