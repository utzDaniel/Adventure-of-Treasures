package backend.service;

import backend.controller.interfaces.IActionService;
import backend.controller.interfaces.IResponse;
import backend.service.component.inventory.quit.InventoryQuit;
import backend.service.component.move.Move;
import backend.service.component.open.Open;
import backend.service.component.take.Take;

public final class ActionService implements IActionService {
    /**
     * O service é responsável por conter a lógica de negócio da aplicação.
     * Ele encapsula as operações e regras de negócio que são necessárias para realizar as tarefas desejadas.
     * O service recebe os dados do controller, executa a lógica necessária e se comunica com o repository,
     * se for necessário acessar dados persistentes.
     * O service também pode realizar validações adicionais,
     * orquestrar várias operações do repository e aplicar regras de negócio mais complexas.
     * */
    @Override
    public IResponse refresh() {
        return new InventoryQuit().run();
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
