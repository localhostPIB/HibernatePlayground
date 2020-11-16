package dao.classes;

import dao.interfaces.IBandDao;
import model.classes.Band;
import model.interfaces.IBand;
import model.interfaces.IPerson;
import org.hibernate.Session;
import util.HibernateUtils;
import validator.BandValidator;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Die Methoden welche fuer die Datenbank (Tabelle fuer die Band) wichtig sind.
 */
public class BandDaoHibernateImpl implements IBandDao {
    private List<IBand> bandList;

    private static BandDaoHibernateImpl bDaoInstance = null;

    /**
     * Singelton fuer die Datenbankzugriffe.
     *
     * @return - 'Eine' Instanz/Objekt von BandDaoHibernate.
     */
    public static BandDaoHibernateImpl getInstance(){
        if(bDaoInstance == null){
            return new BandDaoHibernateImpl();
        }
        return bDaoInstance;
    }

    @Override
    public void deleteAll(){
        try {
            for(IBand iBand : findAllBands()){
                deleteBand(iBand);
            }
        } catch (Exception ex) {
            ex.fillInStackTrace();
        }


    }

    /**
     * Gibt alle Band aus.
     *
     * JOIN FETCH verhindert eine Lazy Exception.
     *  https://www.heise.de/developer/artikel/Hibernate-Tipp-Wie-sollten-lazy-geladene-Beziehungen-initialisiert-werden-3799298.html
     *
     * @return - Liste mit allen Bands.
     */
    @Override
    public List<IBand> findAllBands(){
        Session session = null;
        bandList        = new ArrayList<>();

        try{
            session = HibernateUtils.getSession();
            session.beginTransaction();
            String queryString ="SELECT band FROM Band band JOIN FETCH band.personList";
            Query query = session.createQuery(queryString);
            bandList = (List<IBand>) query.getResultList();
            session.flush();
            session.getTransaction().commit();
        }catch (Exception ex){
            ex.fillInStackTrace();
        }finally{
            HibernateUtils.closeSession(session);
        }

        return bandList;
    }

    /**
     * Speichert eine Band in die Datenbank ab.
     *
     * @param iBand - Die Band die gespeichert werden soll.
     */
    @Override
    public void saveBand(IBand iBand){
        Session session = null;

        try {
            session = HibernateUtils.getSession();
            session.beginTransaction();
        if(BandValidator.validateBandObject(iBand)){
            session.saveOrUpdate(iBand);
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
     * Loescht eine Band in der Datenbank.
     *
     * @param iBand - Die Band die gespeichert werden soll.
     */
    @Override
    public void deleteBand(IBand iBand) {
        Session session = null;

        try {
            session = HibernateUtils.getSession();
            session.beginTransaction();
            if (BandValidator.validateBandObject(iBand)) {
                session.delete(iBand);
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
     * Sucht eine Band anhand Ihres Namens.
     *
     * @param bandName - Name der Band(s).
     * @return         - Liste mit moegleichen Bands.
     */
    @Override
    public List<IBand> findBandByName(String bandName){
        Session session = null;
        List<IBand> bandList;
        String queryString = "Select b.name From Band b where b.name=" +"'"+bandName+"'";

        //Auch hier gilt^^: die 'bandName' also '' nicht vergessen ;-)

        try {
            session = HibernateUtils.getSession();
            session.beginTransaction();
            Query query = session.createQuery(queryString);
            bandList = (List<IBand>) query.getResultList();
            session.flush();
            session.getTransaction().commit();

            return bandList;
        }catch (Exception ex){
            ex.fillInStackTrace();
        }finally{
            HibernateUtils.closeSession(session);
        }

        return null;

    }

    /**
     * Gibt eine Band anhand seiner Id aus.
     *
     * @param id - id der zu suchenden Band.
     */
    @Override
    public IBand findBand(int id) {
        Session session = null;

        try {
            session = HibernateUtils.getSession();
            session.beginTransaction();
            IBand iBand = (IBand) session.get(Band.class, id);
            session.flush();
            session.getTransaction().commit();

            return iBand;
        } catch (Exception ex) {
            ex.fillInStackTrace();
        } finally {
            HibernateUtils.closeSession(session);
        }

        return null;
    }

    }
