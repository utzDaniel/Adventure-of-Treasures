package backend.controller.exception;

public class DoorExeption extends RuntimeException {
    public DoorExeption(String msg) {
        super(msg);
    }
}