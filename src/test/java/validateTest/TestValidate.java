package validateTest;

import model.classes.Band;
import model.classes.Person;
import model.interfaces.IBand;
import model.interfaces.IPerson;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import validator.BandValidator;
import validator.PersonValidator;

import static org.junit.Assert.assertEquals;

/**
 * Testet den Validator
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestValidate {
    private IBand   band;
    private IPerson person;

    /**
     * Vorbereitung fuer die Tests.
     */
    @Before
    public void setUp() {
        band   = new Band();
        band.setName("KoRn");

        person = new Person();
        person.setName("Jonathan");
    }

    /**
     * Testet den Band-Validator.
     */
    @Test
    public void testBandValidate(){
     boolean test1 =   BandValidator.validateBandObject(band);

        assertEquals(test1, true);
    }

    /**
     * Testet den Personen-Validator.
     */
    @Test
    public void testPersonValidate(){
        boolean test2 = PersonValidator.validatePersonObject(person);

        assertEquals(test2, true);
    }

}
