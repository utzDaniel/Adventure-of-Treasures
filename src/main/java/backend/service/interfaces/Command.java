package backend.service.interfaces;

public interface Command {

    boolean run();
    String getName();

}
