package backend.model.interfaces;

public interface Command {

    boolean run();
    String getName();

}
