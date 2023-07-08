package backend.service;

import backend.controller.interfaces.IResponse;
import backend.service.component.combination.ServiceCombinationItem;
import backend.service.component.drop.ServiceDropItem;
import backend.service.component.equip.ServiceEquipItem;
import backend.service.component.inventory.open.InventoryOpen;
import backend.service.component.inventory.quit.InventoryQuit;
import backend.service.component.move.Move;
import backend.service.component.open.Open;
import backend.service.component.take.Take;
import backend.service.component.use.ServiceUseItem;

public final class Service implements IService {
    @Override
    public IResponse inventoryQuit() {
        return new InventoryQuit().run();
    }

    @Override
    public IResponse combination(String... names) {
        return new ServiceCombinationItem().run(names);
    }

    @Override
    public IResponse use(String name) {
        return new ServiceUseItem().run(name);
    }

    @Override
    public IResponse equip(String name) {
        return new ServiceEquipItem().run(name);
    }

    @Override
    public IResponse drop(String name) {
        return new ServiceDropItem().run(name);
    }

    @Override
    public IResponse inventoryOpen() {
        return new InventoryOpen().run();
    }

    @Override
    public IResponse take() {
        return new Take().run();
    }

    @Override
    public IResponse open() {
        return new Open().run();
    }

    @Override
    public IResponse move(String direction) {
        return new Move().run(direction);
    }
}
