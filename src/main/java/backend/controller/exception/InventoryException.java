package backend.controller.exception;

public class InventoryException extends RuntimeException {
    public InventoryException(String msg) {
        super(msg);
    }
}