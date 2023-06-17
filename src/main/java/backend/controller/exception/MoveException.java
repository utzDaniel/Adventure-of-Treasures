package backend.controller.exception;

public class MoveException extends RuntimeException {
    public MoveException(String msg) {
        super(msg);
    }
}