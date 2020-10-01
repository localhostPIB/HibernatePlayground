package dao;

import model.classes.Person;
import model.interfaces.IPerson;
import org.hibernate.Session;
import util.HibernateUtils;
import validator.PersonValidator;

/**
 * Die Methoden welche fuer die Datenbank wichtig sind.
 */
public class PersonDaoHibernate {

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
        } catch (final Exception ex) {
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
        } catch (final Exception ex) {
            ex.fillInStackTrace();
        } finally {
            HibernateUtils.closeSession(session);
        }
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