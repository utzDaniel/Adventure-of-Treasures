package backend.controller.model;

import backend.controller.interfaces.IEventAction;
import backend.controller.interfaces.IResponse;
import backend.service.IService;

public class EventAction implements IEventAction {

    private final IService service;

    public EventAction(IService service) {
        this.service = service;
    }


    public IResponse inventoryQuit() {
        return this.service.inventoryQuit();
    }

    public IResponse combination(String names) {
        return this.service.combination(names.split(","));
    }

    public IResponse use(String name) {
        return this.service.use(name);
    }

    public IResponse equip(String name) {
        return this.service.equip(name);
    }

    public IResponse drop(String name) {
        return this.service.drop(name);
    }

    public IResponse inventoryOpen() {
        return this.service.inventoryOpen();
    }

    public IResponse take() {
        return this.service.take();
    }

    public IResponse open() {
        return this.service.open();
    }

    public IResponse move(String direction) {
        return this.service.move(direction);
    }
}
