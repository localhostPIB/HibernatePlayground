package serviceTest;


import dao.classes.PersonDaoHibernateImpl;
import dao.interfaces.IPersonDao;
import model.classes.Band;
import model.classes.Person;
import model.interfaces.IBand;
import model.interfaces.IPerson;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import service.classes.BandServiceImpl;
import service.interfaces.IBandService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestBandService {
    private IBandService bandService = BandServiceImpl.getInstance();
    private IPersonDao personDao     = PersonDaoHibernateImpl.getInstance();


    /**
     * Initialisiert den Test fuer den Band-Service.
     */
    @Before
    public void setUp() {

        prepareDatabase();
    }

    /**
     * Datenbank wird mit ersten Werten befuellt.
     */
    public void prepareDatabase() {
        List<IPerson> personList1 = new ArrayList<>();
        List<IPerson> personList2 = new ArrayList<>();

        IBand band1 = new Band();
        IBand band2 = new Band();
        IBand band3 = new Band();
        IBand band4 = new Band();

        IPerson person1 = new Person();
        IPerson person2 = new Person();
        IPerson person3 = new Person();
        IPerson person4 = new Person();

        person1.setName("Till");
        person2.setName("Tom");
        person3.setName("Oliver");
        person4.setName("Test_Member");

        personList1.add(person1);
        personList1.add(person3);
        personList2.add(person2);

        personDao.savePerson(person1);
        personDao.savePerson(person2);
        personDao.savePerson(person3);

        band1.setPerson(personList1);
        band2.setPerson(personList2);

        band1.setName("Rammstein");
        band2.setName("Slayer");
        band3.setName("Nine Inch Nails");
        band4.setName("Test_Band");
        band4.add(person4);
        bandService.saveBand(band1);
        bandService.saveBand(band2);
        bandService.saveBand(band3);
        bandService.saveBand(band4);

    }

    /**
     * Test zum ausgeben aller Bands.
     */
    @Test
    public void a_FindAllBandsTest(){
        List<IBand> bandTestList;
        int testsize = 4;

        bandTestList = bandService.findAllBands();
        assertEquals(bandTestList.size(),testsize);
    }

    /**
     * Test zur Such-Funktion die nach einer Id in der Datenbank sucht.
     */
    @Test
    @Ignore
    public void b_findBandByIdTest(){
        int test_Id = 4;

        IBand iBand = bandService.findBandById(test_Id);
        List<IPerson> band = bandService.findPersonByBand(iBand);
        System.out.println("Band: " + band.toString());
        assertEquals(iBand.getId(),test_Id);
    }

    /**
     * Testet die Such-Funktion die nach Namen in der Datenbank sucht.
     */
    @Test
    public void c_findBandByNameTest(){
        int test_Size = 2;
        String test_String = "Slayer";

        List<IBand> iBandList = bandService.findBandByName(test_String);
        assertEquals(iBandList.size(),test_Size);
    }


    /**
     * Testet ob alle Bands geloescht werden.
     */
    @Test
    public void d_deleteAllEntryTest(){
        List<IBand> bandTestList2;
        int testsize_Zero = 0;

        bandService.deleteAllBands();

        bandTestList2 = bandService.findAllBands();

        assertEquals(bandTestList2.size(), testsize_Zero);
    }
}
