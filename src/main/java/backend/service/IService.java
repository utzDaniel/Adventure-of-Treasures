package backend.service;

import backend.controller.interfaces.IResponse;

public interface IService {
    IResponse inventoryQuit();

    IResponse combination(String... names);

    IResponse use(String name);

    IResponse equip(String name);

    IResponse drop(String name);

    IResponse inventoryOpen();

    IResponse take();

    IResponse open();

    IResponse move(String direction);
}
