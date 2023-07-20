package backend.controller;

import backend.controller.interfaces.IActionController;
import backend.controller.interfaces.IActionService;
import backend.controller.interfaces.IResponse;

public final class ActionController implements IActionController {

    private final IActionService actionService;

    public ActionController(IActionService service) {
        this.actionService = service;
    }

    /**
    * o controller valida os dados de entrada,
    * chama os métodos do service apropriados e
    * retorna as respostas HTTP adequadas, como códigos de status e conteúdo da resposta.
    */

    @Override
    public IResponse refresh() {
        return this.actionService.refresh();
    }


    @Override
    public IResponse take() {
        return this.actionService.take();
    }

    @Override
    public IResponse open() {
        return this.actionService.open();
    }

    @Override
    public IResponse move(String direction) {
        return this.actionService.move(direction);
    }
}
