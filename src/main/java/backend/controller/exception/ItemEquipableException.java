package backend.controller.exception;

public class ItemEquipableException extends RuntimeException {
    public ItemEquipableException(String msg) {
        super(msg);
    }
}