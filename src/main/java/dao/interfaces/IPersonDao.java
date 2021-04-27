package dao.interfaces;

import model.interfaces.IPerson;

import java.util.List;

public interface IPersonDao {
    void savePerson(IPerson iperson);

    void deleteAll();

    List<IPerson> personWithO();

    void deletePerson(IPerson iperson);

    List<IPerson> findPersonByName(String name);

    List<IPerson> findAllPersons();

    IPerson findPerson(int id);

    void updatePerson(IPerson iPerson);
}
