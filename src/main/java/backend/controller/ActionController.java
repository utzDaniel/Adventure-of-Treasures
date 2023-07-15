package backend.controller;

import backend.controller.interfaces.IActionController;
import backend.controller.interfaces.IResponse;
import backend.controller.interfaces.IService;

public final class ActionController implements IActionController {

    private final IService service;

    public ActionController(IService service) {
        this.service = service;
    }
    /*
    * o controller valida os dados de entrada,
    * chama os métodos do service apropriados e
    * retorna as respostas HTTP adequadas, como códigos de status e conteúdo da resposta.
    */

    @Override
    public IResponse inventoryQuit() {
        return this.service.inventoryQuit();
    }

    @Override
    public IResponse combination(String names) {
        return this.service.combination(names.split(","));
    }

    @Override
    public IResponse use(String name) {
        return this.service.use(name);
    }

    @Override
    public IResponse equip(String name) {
        return this.service.equip(name);
    }

    @Override
    public IResponse drop(String name) {
        return this.service.drop(name);
    }

    @Override
    public IResponse inventoryOpen() {
        return this.service.inventoryOpen();
    }

    @Override
    public IResponse take() {
        return this.service.take();
    }

    @Override
    public IResponse open() {
        return this.service.open();
    }

    @Override
    public IResponse move(String direction) {
        return this.service.move(direction);
    }
}
