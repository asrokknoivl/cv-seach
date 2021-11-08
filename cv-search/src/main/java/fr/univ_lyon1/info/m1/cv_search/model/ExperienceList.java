package fr.univ_lyon1.info.m1.cv_search.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExperienceList implements Iterable<Experience>, IElementList{
    List<Experience> list = new ArrayList<Experience>();

    public List<Experience> getList() {
        return list;
    }

    public void setList(List<Experience> list) {
        this.list = list;
    }

    @Override
    public void addElement(Element e) {
        list.add((Experience) e);
    }

    @Override
    public void removeElement(Element e) {
        list.remove((Experience) e);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public Iterator<Experience> iterator() {
        return list.iterator();
    }
}
