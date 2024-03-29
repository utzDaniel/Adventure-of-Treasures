package backend.controller;

import backend.controller.factory.ResponseFactory;
import backend.controller.interfaces.IInventoryController;
import backend.controller.interfaces.IInventoryService;
import backend.controller.interfaces.IResponse;

import java.util.Arrays;

public final class InventoryController implements IInventoryController {

    private final IInventoryService inventoryService;

    public InventoryController(IInventoryService service) {
        this.inventoryService = service;
    }


    @Override
    public IResponse combination(String idItens) {
        var listIdItens = Arrays.stream(idItens.split(","))
                .map(Integer::parseInt)
                .toList();
        var rsp = this.inventoryService.combination(listIdItens);
        return ResponseFactory.create(rsp);
    }

    @Override
    public IResponse use(String idItem) {
        var rsp = this.inventoryService.use(Integer.parseInt(idItem));
        return ResponseFactory.create(rsp);
    }

    @Override
    public IResponse equip(String idItem) {
        var rsp = this.inventoryService.equip(Integer.parseInt("10"));
        return ResponseFactory.create(rsp);
    }

    @Override
    public IResponse drop(String idItem) {
        var rsp = this.inventoryService.drop(Integer.parseInt(idItem));
        return ResponseFactory.create(rsp);
    }

    @Override
    public IResponse open() {
        var rsp = this.inventoryService.open();
        return ResponseFactory.create(rsp);
    }

}
