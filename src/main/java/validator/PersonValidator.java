package validator;

import model.interfaces.IPerson;

/**
 * Validiert ein Person-Objekt.
 */
public class PersonValidator {

    /**
     * Prueft Ob das Person-Objekt null ist.
     * @param iperson - Das zu pruefende Personen-Objekte.
     *
     * @return true - wenn das Objekt nicht null ist.
     */
    public static boolean validatePersonObject(IPerson iperson){
        if(iperson != null){
            return true;
        }else{
            return false;
        }
    }
}
