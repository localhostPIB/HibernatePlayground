import dao.PersonDaoHibernate;
import model.classes.Person;
import model.interfaces.IPerson;

import static org.junit.Assert.assertEquals;

import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Datenbank-Test.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestPerson {

    private PersonDaoHibernate personDaoHibernate;

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
        personDaoHibernate = new PersonDaoHibernate();
        IPerson person1    = new Person();
        IPerson person2    = new Person();

        person1.setName("Oliver");
        person2.setName("Freddy");

        personDaoHibernate.savePerson(person1);
        personDaoHibernate.savePerson(person2);
    }

    /**
     * Test zur SUch-Funktion.
     */
    @Test
    public void a_findPersonTest(){
        int id = 1;

        IPerson iperson = personDaoHibernate.findPerson(1);
        assertEquals(iperson.getId(),id);
    }

    /**
     * Test zum ausgeben aller Personen.
     */
    @Test
    public void b_findAllPersonTest() {
        List<IPerson> personTestList;

        personTestList = personDaoHibernate.findAllPersons();
        assertEquals(personTestList.size(), 4);
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
     * Testet ob alles geloescht wird.
     */
     @Test
     public void d_deleteAllTest(){
        List<IPerson> personTestList2;
        personDaoHibernate.deleteAll();

        personTestList2 = personDaoHibernate.findAllPersons();

        assertEquals(personTestList2.size(), 0);
     }
}
