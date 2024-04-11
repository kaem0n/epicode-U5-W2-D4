package kaem0n.u5w2d4.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id){
        super("Element with id '" + id + "' not found.");
    }
}