package backend.controller;


import backend.controller.factory.ResponseFactory;
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
        var rsp = this.actionService.refresh();
        return ResponseFactory.create(rsp);
    }


    @Override
    public IResponse take() {
        var rsp = this.actionService.take();
        return ResponseFactory.create(rsp);
    }

    @Override
    public IResponse open() {
        var rsp = this.actionService.open();
        return ResponseFactory.create(rsp);
    }

    @Override
    public IResponse interact() {
        var rsp = this.actionService.interact();
        return ResponseFactory.create(rsp);
    }

    @Override
    public IResponse move(String direction) {
        var rsp = this.actionService.move(direction);
        return ResponseFactory.create(rsp);
    }
}
