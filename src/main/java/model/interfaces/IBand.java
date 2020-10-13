package model.interfaces;

import model.classes.Person;

import java.util.List;


public interface IBand {

    int getId();

    String getName();

    void setName(String Name);

    List<IPerson> getPerson();

    void setPerson(List<IPerson> person);

}
