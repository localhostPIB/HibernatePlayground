package dao.interfaces;

import model.interfaces.IPerson;

import java.util.List;

public interface IPersonDao {
    void savePerson(IPerson iperson);

    void deleteAll();

    void deletePerson(IPerson iperson);

    List<IPerson> findPersonByName(String name);

    List<IPerson> findAllPersons();

    IPerson findPerson(int id);
}
