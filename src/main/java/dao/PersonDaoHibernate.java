package dao;

import model.classes.Person;
import model.interfaces.IPerson;
import org.hibernate.Session;
import util.HibernateUtils;
import validator.PersonValidator;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Die Methoden welche fuer die Datenbank wichtig sind.
 */
public class PersonDaoHibernate {
    private List<IPerson> personList;

    /**
     * Speichert eine Person in die Datenbank ab.
     *
     * @param iperson - Die Person die gespeichert werden soll.
     */
    public void savePerson(IPerson iperson) {
        Session session = null;

        try {
            session = HibernateUtils.getSession();
            session.beginTransaction();
            if (PersonValidator.validatePersonObject(iperson) == true) {
                session.saveOrUpdate(iperson);
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
    public void deleteAll(){
        Session session = null;
        String queryString = "DELETE FROM Person";

        try {
            session = HibernateUtils.getSession();
            session.beginTransaction();
            Query query = session.createQuery(queryString);
            //TODO
        } catch (Exception ex) {
            ex.fillInStackTrace();
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    /**
     * Loescht eine Person in der Datenbank.
     *
     * @param iperson - Die Person die gespeichert werden soll.
     */
    public void deletePerson(IPerson iperson) {
        Session session = null;

        try {
            session = HibernateUtils.getSession();
            session.beginTransaction();
            if (PersonValidator.validatePersonObject(iperson) == true) {
                session.delete(iperson);
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
     * @param name - Name der Person
     * @return -
     */
    public List<IPerson> findPersonByName(String name){
        Session session = null;
        List<IPerson> personList = new ArrayList<>();
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
     * Gibt alles Personen aus.
     */
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
    public IPerson findPerson(int id) {
        Session session = null;

        try {
            session = HibernateUtils.getSession();
            session.beginTransaction();
            IPerson iperson = (IPerson) session.get(Person.class, id);
            session.flush();
            session.getTransaction().commit();

            return iperson;

        } catch (final Exception ex) {
            ex.fillInStackTrace();
        } finally {
            HibernateUtils.closeSession(session);
        }

        return null;
    }
}