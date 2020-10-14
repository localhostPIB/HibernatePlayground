package daoTest;

import dao.classes.BandDaoHibernateImpl;
import dao.classes.PersonDaoHibernateImpl;
import dao.interfaces.IBandDao;
import dao.interfaces.IPersonDao;
import model.classes.Band;
import model.classes.Person;
import model.interfaces.IBand;
import model.interfaces.IPerson;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Datenbank-Test fuer die Band.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestBandDAO {
    private IBandDao bandDaoHibernate = BandDaoHibernateImpl.getInstance();
    private IPersonDao personDao      = PersonDaoHibernateImpl.getInstance();


    /**
     * Initialisiert den Test.
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

        IPerson person1 = new Person();
        IPerson person2 = new Person();
        IPerson person3 = new Person();

        person1.setName("Till");
        person2.setName("Tom");
        person3.setName("Oliver");

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

        bandDaoHibernate.saveBand(band1);
        bandDaoHibernate.saveBand(band2);
        bandDaoHibernate.saveBand(band3);
    }

    /**
     * Test zum ausgeben aller Bands.
     */
    @Test
    public void a_FindAllBandsTest(){
        List<IBand> bandTestList;
        int testsize = 3;

        bandTestList = bandDaoHibernate.findAllBands();
        assertEquals(bandTestList.size(),testsize);
    }

    /**
     * Test zur Such-Funktion.
     */
    @Test
    public void b_findBandByIdTest(){
        int test_Id = 4;

        IBand iBand = bandDaoHibernate.findBand(test_Id);
        assertEquals(iBand.getId(),test_Id);
    }

    @Test
    public void c_findBandByNameTest(){
        int test_Size = 3;
        String test_String = "Slayer";

        List<IBand> iBandList = bandDaoHibernate.findBandByName(test_String);
        assertEquals(iBandList.size(),test_Size);
    }

    /**
     * Testet ob alle Bands geloescht werden.
     */
    @Test
    public void d_deleteAllEntryTest(){
        List<IBand> bandTestList2;
        int testsize_Zero = 0;

        bandDaoHibernate.deleteAll();

        bandTestList2 = bandDaoHibernate.findAllBands();

        assertEquals(bandTestList2.size(), testsize_Zero);
    }

}
