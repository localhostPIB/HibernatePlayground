package service.interfaces;

import model.interfaces.IPerson;

import java.util.List;

public interface IPersonService {

    void savePerson(IPerson iPerson);

    void deletePerson(IPerson iPerson);

    void deleteAllPersons();

    List<IPerson> findPersonByName(String name);

    List<IPerson> findAllPersons();

    IPerson findPerson(int id);
}
