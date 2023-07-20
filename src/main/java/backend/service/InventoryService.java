package backend.service;

import backend.controller.interfaces.IInventoryService;
import backend.controller.interfaces.IResponse;
import backend.service.component.combination.ServiceCombinationItem;
import backend.service.component.drop.ServiceDropItem;
import backend.service.component.equip.ServiceEquipItem;
import backend.service.component.inventory.open.InventoryOpen;
import backend.service.component.use.ServiceUseItem;

public final class InventoryService implements IInventoryService {
    /**
     * O service é responsável por conter a lógica de negócio da aplicação.
     * Ele encapsula as operações e regras de negócio que são necessárias para realizar as tarefas desejadas.
     * O service recebe os dados do controller, executa a lógica necessária e se comunica com o repository,
     * se for necessário acessar dados persistentes.
     * O service também pode realizar validações adicionais,
     * orquestrar várias operações do repository e aplicar regras de negócio mais complexas.
     */

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
    public IResponse open() {
        return new InventoryOpen().run();
    }
}
