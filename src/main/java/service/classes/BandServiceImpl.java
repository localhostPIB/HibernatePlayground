package service.classes;

import dao.classes.BandDaoHibernateImpl;
import dao.interfaces.IBandDao;
import model.interfaces.IBand;
import service.interfaces.IBandService;

import java.util.List;

/**
 * Service-Klasse der Band(s).
 */
public class BandServiceImpl implements IBandService {
    private IBandDao bandDao;

    private static IBandService bandService = null;

    public BandServiceImpl(){
        bandDao = BandDaoHibernateImpl.getInstance();
    }

    /**
     * Singleton
     * @return - Genau ein Band-Objekt.
     */
    public static IBandService getInstance(){
        if(bandService == null){
            return new BandServiceImpl();
        }
        return bandService;
    }

    /**
     * Speichert ein Band in der Datenbank.
     *
     * @param iBand - Die zu speichernde Band.
     */
    @Override
    public void saveBand(IBand iBand) {

        bandDao.saveBand(iBand);
    }

    /**
     * Loescht eine Band aus der Datenbank.
     *
     * @param iBand - Die zu loeschende Band.
     */
    @Override
    public void deleteBand(IBand iBand) {

        bandDao.deleteBand(iBand);
    }

    /**
     * Loescht alle Band saus der Datenbank.
     */
    @Override
    public void deleteAllBands() {

        bandDao.deleteAll();
    }

    /**
     * Findet eine Band anhand ihrer Id.
     *
     * @param id - Id der zu suchenden Band.
     * @return - Ein Band-Objekt.
     */
    @Override
    public IBand findBandById(int id) {
        return bandDao.findBand(id);
    }

    /**
     * Findet Bands anhand ihres Namens.
     *
     * @param bandName - Der Name der moeglichen Bands.
     * @return - Liste mit Bands.
     */
    @Override
    public List<IBand> findBandByName(String bandName) {
        return bandDao.findBandByName(bandName);
    }

    /**
     * Gibt alle Bands aus der Datenbank aus.
     *
     * @return - Eine Liste mit allen Bands.
     */
    @Override
    public List<IBand> findAllBands() {
        return bandDao.findAllBands();
    }
}
