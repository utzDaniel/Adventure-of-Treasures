package backend.controller.model;

import backend.controller.interfaces.IEventAction;
import backend.controller.interfaces.IRequest;
import backend.controller.interfaces.IResponse;
import backend.service.component.combination.ServiceCombinationItem;
import backend.service.component.inventory.quit.InventoryQuit;
import backend.service.component.use.ServiceUseItem;
import backend.service.component.drop.ServiceDropItem;
import backend.service.component.equip.ServiceEquipItem;
import backend.service.component.move.Move;
import backend.service.component.open.Open;
import backend.service.component.inventory.open.InventoryOpen;
import backend.service.component.take.Take;

public class EventAction implements IEventAction {

//TODO usar Injeção de Dependência para resolvermos a Inversão de Controle (new Service). usar a anotação ver video da alura

    @Override
    public IResponse run(IRequest request) {
        var action = request.action();
        if ("Move".equalsIgnoreCase(action)) {
            return new Move().run(request);
        } else if ("Open".equalsIgnoreCase(action)) {
            return new Open().run();
        } else if ("Take".equalsIgnoreCase(action)) {
            return new Take().run();
        } else if ("InventoryOpen".equalsIgnoreCase(action)) {
            return new InventoryOpen().run();
        }else if ("Remover".equalsIgnoreCase(action)) {
            return new ServiceDropItem().run(request);
        }else if ("Equipar".equalsIgnoreCase(action)) {
            return new ServiceEquipItem().run(request);
        }else if ("Usar".equalsIgnoreCase(action)) {
            return new ServiceUseItem().run(request);
        }else if ("Combinar".equalsIgnoreCase(action)) {
            return new ServiceCombinationItem().run(request);
        }else if ("InventoryQuit".equalsIgnoreCase(action)) {
            return new InventoryQuit().run();
        }
        return null;
    }
}
