package backend.controller;

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
        return this.inventoryService.combination(names.split(","));
    }

    @Override
    public IResponse use(String name) {
        return this.inventoryService.use(name);
    }

    @Override
    public IResponse equip(String name) {
        return this.inventoryService.equip(name);
    }

    @Override
    public IResponse drop(String name) {
        return this.inventoryService.drop(name);
    }

    @Override
    public IResponse open() {
        return this.inventoryService.open();
    }

}
