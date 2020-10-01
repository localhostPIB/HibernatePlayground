import dao.PersonDaoHibernate;
import model.Person;
import org.junit.Before;
import org.junit.Test;


public class TestPerson {

    @Before
    public void setUp() {
        prepareDatabase();
    }

    public void prepareDatabase(){
        PersonDaoHibernate personDaoHibernate = new PersonDaoHibernate();
        Person person1 = new Person();
        Person person2 = new Person();
        Person person3 = new Person();
        person1.setName("Oliver");
        person2.setName("Freddy");
        person3.setName("Jason");

        personDaoHibernate.savePerson(person1);
        personDaoHibernate.savePerson(person2);
        personDaoHibernate.savePerson(person3);
    }

    @Test
    public void test1(){

    }
}
