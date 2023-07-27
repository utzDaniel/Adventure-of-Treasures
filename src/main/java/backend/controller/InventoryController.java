package backend.controller;

import backend.controller.factory.ResponseFactory;
import backend.controller.interfaces.IInventoryController;
import backend.controller.interfaces.IInventoryService;
import backend.controller.interfaces.IResponse;

public final class InventoryController implements IInventoryController {

    private final IInventoryService inventoryService;

    public InventoryController(IInventoryService service) {
        this.inventoryService = service;
    }
    /**
     * o controller valida os dados de entrada,
     * chama os métodos do service apropriados e
     * retorna as respostas HTTP adequadas, como códigos de status e conteúdo da resposta.
     */

    @Override
    public IResponse combination(String names) {
        var rsp = this.inventoryService.combination(names.split(","));
        return ResponseFactory.create(rsp);
    }

    @Override
    public IResponse use(String name) {
        var rsp = this.inventoryService.use(name);
        return ResponseFactory.create(rsp);
    }

    @Override
    public IResponse equip(String name) {
        var rsp = this.inventoryService.equip(name);
        return ResponseFactory.create(rsp);
    }

    @Override
    public IResponse drop(String name) {
        var rsp = this.inventoryService.drop(name);
        return ResponseFactory.create(rsp);
    }

    @Override
    public IResponse open() {
        var rsp = this.inventoryService.open();
        return ResponseFactory.create(rsp);
    }

}
