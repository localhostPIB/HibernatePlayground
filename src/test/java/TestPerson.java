import dao.PersonDaoHibernate;
import model.classes.Person;
import model.interfaces.IPerson;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;

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
    public void savePersonTest(){
        IPerson person3 = new Person();
        person3.setName("Jason");
        personDaoHibernate.savePerson(person3);
        //TODO
    }

    @Test
    public void findPersonTest(){
        int id = 1;

       IPerson iperson = personDaoHibernate.findPerson(1);
       assertEquals(iperson.getId(),id);
    }
}
