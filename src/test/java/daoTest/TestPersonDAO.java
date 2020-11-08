package daoTest;

import dao.classes.PersonDaoHibernateImpl;
import dao.interfaces.IPersonDao;
import model.classes.Person;
import model.interfaces.IPerson;

import static org.junit.Assert.assertEquals;

import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Datenbank-Test fuer die Person.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestPersonDAO {

    private IPersonDao personDaoHibernate = PersonDaoHibernateImpl.getInstance();

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
        IPerson person1    = new Person();
        IPerson person2    = new Person();
        person1.setName("Oliver");
        person2.setName("Kurt");

        personDaoHibernate.savePerson(person1);
        personDaoHibernate.savePerson(person2);
    }

    /**
     * Test zur Such-Funktion.
     */
    @Test
    public void a_findPersonByIdTest(){
        int id = 1;

        IPerson iperson = personDaoHibernate.findPerson(1);

        assertEquals(iperson.getId(),id);
    }

    /**
     * Test zum ausgeben aller Personen.
     */
    @Test
    public void b_findAllPersonsTest() {
        List<IPerson> personTestList;
        int testsize = 4;

        personTestList = personDaoHibernate.findAllPersons();
        assertEquals(personTestList.size(), testsize);
    }

    /**
     * Test zur Such-Funktion.
     */
    @Test
    public void c_findPersonByNameTest() {
        List<IPerson> personTestList1;
        String testName = "Oliver";

        personTestList1 = personDaoHibernate.findPersonByName(testName);

        assertEquals(3, personTestList1.size());
    }

    /**
     * Testet ob alle Personen geloescht werden.
     */
     @Test
     public void d_deleteAllEntryTest(){
        List<IPerson> personTestList2;
        personDaoHibernate.deleteAll();

        personTestList2 = personDaoHibernate.findAllPersons();

        assertEquals(personTestList2.size(), 0);
     }
}
