package dao.classes;

import dao.interfaces.IPersonDao;
import model.classes.Person;
import model.interfaces.IPerson;
import org.hibernate.Session;
import util.HibernateUtils;
import validator.PersonValidator;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Die Methoden welche fuer die Datenbank (Tabelle fuer die Person) wichtig sind.
 */
public class PersonDaoHibernateImpl implements IPersonDao {
    private List<IPerson> personList;

    private static PersonDaoHibernateImpl pDaoInstance = null;

    /**
     * Singelton fuer die Datenbankzugriffe.
     *
     * @return - 'Eine' Instanz/Objekt von PersonDaoHibernate.
     */
    public static PersonDaoHibernateImpl getInstance(){
        if(pDaoInstance == null){
            return new PersonDaoHibernateImpl();
        }

        return  pDaoInstance;
    }

    /**
     * Speichert eine Person in die Datenbank ab.
     *
     * @param iPerson - Die Person die gespeichert werden soll.
     */
    @Override
    public void savePerson(IPerson iPerson) {
        Session session = null;

        try {
            session = HibernateUtils.getSession();
            session.beginTransaction();
            if (PersonValidator.validatePersonObject(iPerson)) {
                session.saveOrUpdate(iPerson);
                session.flush();
            }
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.fillInStackTrace();
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    /**
     * Updated die Daten des Personen-Objekts.
     *
     * @param iPerson
     */
    @Override
    public void updatePerson(IPerson iPerson) {
        Session session = null;
            try {
                session = HibernateUtils.getSession();
                session.beginTransaction();
                if (PersonValidator.validatePersonObject(iPerson)) {
                    session.update(iPerson);
                    session.flush();
                }
                session.getTransaction().commit();
            } catch (Exception ex) {
                ex.fillInStackTrace();
            } finally {
                HibernateUtils.closeSession(session);
            }
        }


    /**
     * Loescht alle eintraege aus der Datenbank.
     */
    @Override
    public void deleteAll(){
        try {
            for(IPerson iPerson : findAllPersons()){
                deletePerson(iPerson);
            }
        } catch (Exception ex) {
            ex.fillInStackTrace();
        }
    }

    /**
     * Speichert eine Person anhand der Query.
     *
     * @param iPerson
     */
    @Override
    public void savePersonWithQueries(IPerson iPerson) {
        //todo
    }

    /**
     * Loescht eine Person in der Datenbank.
     *
     * @param iPerson - Die Person die gespeichert werden soll.
     */
    @Override
    public void deletePerson(IPerson iPerson) {
        Session session = null;

        try {
            session = HibernateUtils.getSession();
            session.beginTransaction();
            if (PersonValidator.validatePersonObject(iPerson)) {
                session.delete(iPerson);
                session.flush();
            }
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.fillInStackTrace();
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    /**
     * Sucht eine Person anhand Ihres Namens.
     *
     * @param name - Name der Person(en).
     * @return - Liste mit moegleichen Personen.
     */
    @Override
    public List<IPerson> findPersonByName(String name){
        Session session = null;
        List<IPerson> personList;
        String queryString = "Select pn.name From Person pn where pn.name=" +"'"+name+"'";
        //die 'name' also '' nicht vergessen ;-)

        try {
            session = HibernateUtils.getSession();
            session.beginTransaction();
            Query query = session.createQuery(queryString);
            personList = (List<IPerson>) query.getResultList();
            session.flush();
            session.getTransaction().commit();

            return personList;
        }catch (Exception ex){
            ex.fillInStackTrace();
        }finally{
            HibernateUtils.closeSession(session);
        }

        return null;
    }

    /**
     * Gibt alle Personen aus.
     *
     * @return - Liste mit allen Personen.
     */
    @Override
    public List<IPerson> findAllPersons(){
        Session session = null;
        personList      = new ArrayList<>();

        try{
            session = HibernateUtils.getSession();
            session.beginTransaction();
            String queryString ="SELECT person FROM Person person";
            Query query = session.createQuery(queryString);
            personList = (List<IPerson>) query.getResultList();
            session.flush();
            session.getTransaction().commit();
        }catch (Exception ex){
            ex.fillInStackTrace();
        }finally{
            HibernateUtils.closeSession(session);
        }

        return personList;
    }

    /**
     * Gibt eine Person anhand seiner Id aus.
     *
     * @param id - id der zu suchenden Person.
     */
    @Override
    public IPerson findPerson(int id) {
        Session session = null;

        try {
            session = HibernateUtils.getSession();
            session.beginTransaction();
            IPerson iperson = (IPerson) session.get(Person.class, id);
            session.flush();
            session.getTransaction().commit();

            return iperson;
        } catch (Exception ex) {
            ex.fillInStackTrace();
        } finally {
            HibernateUtils.closeSession(session);
        }

        return null;
    }
}