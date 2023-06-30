package backend.controller.model;

import backend.controller.interfaces.IEventAction;
import backend.controller.interfaces.IRequest;
import backend.controller.interfaces.IResponse;
import backend.service.component.use.ServiceUseItem;
import backend.service.component.drop.ServiceDropItem;
import backend.service.component.equip.ServiceEquipItem;
import backend.service.component.move.Move;
import backend.service.component.open.Open;
import backend.service.component.openInventory.OpenInventory;
import backend.service.component.take.Take;

public class EventAction implements IEventAction {

    @Override
    public IResponse run(IRequest request) {
        var action = request.action();
        if ("Move".equalsIgnoreCase(action)) {
            return new Move().run(request);
        } else if ("Open".equalsIgnoreCase(action)) {
            return new Open().run();
        } else if ("Take".equalsIgnoreCase(action)) {
            return new Take().run();
        } else if ("Inventory".equalsIgnoreCase(action)) {
            return new OpenInventory().run();
        }else if ("Remover".equalsIgnoreCase(action)) {
            return new ServiceDropItem().run(request);
        }else if ("Equipar".equalsIgnoreCase(action)) {
            return new ServiceEquipItem().run(request);
        }else if ("Usar".equalsIgnoreCase(action)) {
            return new ServiceUseItem().run(request);
        }
        return null;
    }
}
