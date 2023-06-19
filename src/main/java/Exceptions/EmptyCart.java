package Exceptions;

public class EmptyCart extends Exception{
    public EmptyCart(String message) {
        super(message);
    }
}
