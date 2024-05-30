package backend.service.interfaces;

public interface ICommandComposite extends ICommand {

    void add(ICommand children);

}
