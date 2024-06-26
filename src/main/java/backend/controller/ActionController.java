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

    @Override
    public IResponse refresh() {
        var rsp = this.actionService.refresh();
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

    @Override
    public IResponse load() {
        var rsp = this.actionService.load();
        return ResponseFactory.create(rsp);
    }

    @Override
    public IResponse save() {
        var rsp = this.actionService.save();
        return ResponseFactory.create(rsp);
    }
}
