package service.classes;

import dao.classes.PersonDaoHibernateImpl;
import dao.interfaces.IPersonDao;
import model.interfaces.IPerson;
import service.interfaces.IPersonService;

import java.util.List;


public class PersonServiceImpl implements IPersonService {
   private IPersonDao personDao;

   private static IPersonService  personService = null;


   public static IPersonService getInstance(){
       if(personService == null){
        return personService = new PersonServiceImpl();
       }
       return personService;
   }

    private PersonServiceImpl(){
        personDao = PersonDaoHibernateImpl.getInstance();
    }

    @Override
    public void savePerson(IPerson iPerson) {
        personDao.savePerson(iPerson);
    }

    @Override
    public void deletePerson(IPerson iPerson) {
        personDao.deletePerson(iPerson);
    }

    @Override
    public void deleteAllPersons() {
        personDao.deleteAll();
    }

    @Override
    public List<IPerson> findPersonByName(String name) {
        List<IPerson> personList = personDao.findPersonByName(name);

        return personList;
    }

    @Override
    public List<IPerson> findAllPersons() {
        List<IPerson> personList = personDao.findAllPersons();

        return personList;
    }

    @Override
    public IPerson findPerson(int id) {
        return personDao.findPerson(id);
    }
}
