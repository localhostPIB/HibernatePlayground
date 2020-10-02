import dao.PersonDaoHibernate;
import model.classes.Person;
import model.interfaces.IPerson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Datenbank-Test.
 */
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
    public void prepareDatabase(){
        personDaoHibernate = new PersonDaoHibernate();
        IPerson person1 = new Person();
        IPerson person2 = new Person();
        IPerson person3 = new Person();
        person1.setName("Oliver");
        person2.setName("Freddy");

        personDaoHibernate.savePerson(person1);
        personDaoHibernate.savePerson(person2);
        //personDaoHibernate.deletePerson(person1);
    }

    @Test
    public void findallPersonTest(){
        List<IPerson> personTestList = new ArrayList<>();

        personTestList = personDaoHibernate.findAllPersons();
        System.out.println(personTestList.toString());
        assertEquals(personTestList.size(), 2);
    }

    @Test
    public void findPersonByNameTest(){
        String testName = "Oliver";

        IPerson iperson = personDaoHibernate.findPersonByName("Oliver");

        assertEquals("Oliver", iperson.getName());
    }

    @Test
    public void findPersonTest(){
        int id = 1;

       IPerson iperson = personDaoHibernate.findPerson(1);
       assertEquals(iperson.getId(),id);
    }
}
