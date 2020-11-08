package service.interfaces;

import model.interfaces.IBand;
import model.interfaces.IPerson;

import java.util.List;

public interface IBandService {

    void saveBand(IBand iBand);

    void deleteBand(IBand band);

    void deleteAllBands();

    IBand findBandById(int id);

    List<IBand> findBandByName(String bandName);

    List<IBand> findAllBands();

    List<IPerson>findPersonByBand(IBand iBand);
}
