package fr.univ_lyon1.info.m1.cv_search.model.applicant;

import fr.univ_lyon1.info.m1.cv_search.model.Element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ApplicantList implements Iterable<Applicant> {

    private List<Applicant> list = new ArrayList<Applicant>();

    @Override
    public Iterator<Applicant> iterator() {
        return list.iterator();
    }

    public void addElement(Element e) {
        list.add((Applicant) e);
    }
    public void addElementAtIndex(int x, Element e) {
        list.add(x, (Applicant) e);
    }
    public void removeElement(Element e) {
        list.remove((Applicant) e);
    }
    public void clear() {
        list.clear();
    }

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
