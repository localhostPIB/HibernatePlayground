package dao;

import model.Person;
import org.hibernate.Session;
import util.HibernateUtils;

public class PersonDaoHibernate {

    public void savePerson(final Person person){
        Session session = null;

        try {
            session = HibernateUtils.getSession();
            session.beginTransaction();
            session.saveOrUpdate(person);
            session.flush();
            session.getTransaction().commit();
        }catch (final Exception ex){
            ex.fillInStackTrace();
        }finally {
            HibernateUtils.closeSession(session);
        }
    }
}
