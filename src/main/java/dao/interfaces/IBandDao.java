package dao.interfaces;

import model.interfaces.IBand;

import java.util.List;

public interface IBandDao {

    List<IBand> findAllBands();

    void saveBand(IBand iBand);

    void deleteBand(IBand iBand);

    void deleteAll();

    List<IBand> findBandByName(String bandName);

    IBand findBand(int id);
}
