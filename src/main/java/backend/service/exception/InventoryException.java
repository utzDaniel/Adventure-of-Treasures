package backend.service.exception;

public class InventoryException extends RuntimeException {
    public InventoryException(String msg) {
        super(msg);
    }
}