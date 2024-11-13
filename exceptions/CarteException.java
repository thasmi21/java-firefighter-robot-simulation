package exceptions;
/** Les exception de ce type sont reliés à des erreurs survenues lors de manipulation d'objets de type Carte */
public class CarteException extends Exception {
    public CarteException(String message) {
        super(message);
    }
}
