package serviceTest;

import model.classes.Person;
import model.interfaces.IPerson;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import service.classes.PersonServiceImpl;
import service.interfaces.IPersonService;

import java.util.List;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestPersonService {

   private IPersonService personService = PersonServiceImpl.getInstance();

    /**
     * Initialisiert den Test fuer den Personen-Service.
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
        person2.setName("Freddy");

        personService.savePerson(person1);
        personService.savePerson(person2);
    }

    /**
     * Test zur SUch-Funktion.
     */
    @Test
    public void a_findPersonTest(){
        int id = 1;

        IPerson iperson = personService.findPersonById(1);
        assertEquals(iperson.getId(),id);
    }

    /**
     * Test zum ausgeben aller Personen.
     */
    @Test
    public void b_findAllPersonTest() {
        List<IPerson> personTestList;

        personTestList = personService.findAllPersons();
        assertEquals(personTestList.size(), 4);
    }

    /**
     * Test zur Such-Funktion.
     */
    @Test
    public void c_findPersonByNameTest() {
        List<IPerson> personTestList1;
        String testName = "Oliver";

        personTestList1 = personService.findPersonByName(testName);

        assertEquals(3, personTestList1.size());
    }

    /**
     * Testet die Update-Funktion.
     */
    @Test
    public void d_updatePersonTest(){
        int id = 1;

        IPerson iperson = personService.findPersonById(1);
        iperson.setName("Christian");
        personService.updatePerson(iperson);

    }

    /**
     * Testet die Update-Funktion.
     */
    @Test
    public void e_findPersonwithO(){
        List<IPerson> personList = personService.findPersonswithO();

        assertEquals(1, personList.size());
    }

    /**
     * Testet ob alles geloescht wird.
     */
    @Test
    public void f_deleteAllTest(){
        List<IPerson> personTestList2;
        personService.deleteAllPersons();

        personTestList2 = personService.findAllPersons();

        assertEquals(personTestList2.size(), 0);
    }
}
