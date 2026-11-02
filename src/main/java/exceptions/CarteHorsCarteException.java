package exceptions;
/** Cette classe d'exception signale que la case qui a été tenté d'être manipulée n'existe pas sur la carte considérée */
public class CarteHorsCarteException extends CarteException {
    public CarteHorsCarteException(String message){
        super(message);
    }
}
