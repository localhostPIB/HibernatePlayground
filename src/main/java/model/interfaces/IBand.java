package model.interfaces;


import java.util.List;

public interface IBand {

    int getId();

    String getName();

    void setName(String Name);

    List<IPerson> getPerson();

    void setPerson(List<IPerson> person);

    void add(IPerson person);

}
