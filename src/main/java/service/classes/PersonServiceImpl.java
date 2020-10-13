package service.classes;

import dao.classes.PersonDaoHibernateImpl;
import dao.interfaces.IPersonDao;
import model.interfaces.IPerson;
import service.interfaces.IPersonService;

import java.util.List;


/**
 * Service-Klasse der Person.
 */
public class PersonServiceImpl implements IPersonService {
   private IPersonDao personDao;

   private static IPersonService  personService = null;


   public static IPersonService getInstance(){
       if(personService == null){
        return new PersonServiceImpl();
       }
       return personService;
   }

    private PersonServiceImpl(){

       personDao = PersonDaoHibernateImpl.getInstance();
    }

    /**
     * Service zum Speichern von Personen.
     *
     * @param iPerson - zu speichernde Person.
     */
    @Override
    public void savePerson(IPerson iPerson) {

        personDao.savePerson(iPerson);
    }

    /**
     * Service zum Löschen einer Person.
     *
     * @param iPerson -zu loeschende Person.
     */
    @Override
    public void deletePerson(IPerson iPerson) {
        personDao.deletePerson(iPerson);
    }

    /**
     * Service zum loeschen der Person.
     */
    @Override
    public void deleteAllPersons() {
        personDao.deleteAll();
    }

    /**
     * Findet Personen anhand ihres Namens.
     *
     * @param name - Name der zu suchenden Personen.
     * @return Liste mit allen gefundenen Person.
     */
    @Override
    public List<IPerson> findPersonByName(String name) {
        List<IPerson> personList = personDao.findPersonByName(name);

        return personList;
    }

    /**
     * Gibt alle Personen aus.
     *
     * @return Liste mit allen Personen.
     */
    @Override
    public List<IPerson> findAllPersons() {
        List<IPerson> personList = personDao.findAllPersons();

        return personList;
    }

    /**
     * Fidnet eine Person anhand ihrer Id.
     *
     * @param id - Id der Person.
     * @return - Gefundene Person.
     */
    @Override
    public IPerson findPersonById(int id) {

        return personDao.findPerson(id);
    }
}
