package validator;

import model.interfaces.IBand;

/**
 * Validiert ein Band-Objekt.
 */
public class BandValidator {

    /**
     * Prueft Ob das Band-Objekt null ist.
     * @param iBand - Das zu pruefende Band-Objekte.
     *
     * @return true - wenn das Objekt nicht null ist.
     */
    public static boolean validateBandObject(IBand iBand){
        if(iBand != null){
            return true;
        }else{
            return false;
        }
    }
}
