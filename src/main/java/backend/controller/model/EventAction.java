package backend.controller.model;

import backend.service.component.MovePlayer;
import backend.service.component.OpenInventory;
import backend.enums.Direction;
import backend.controller.interfaces.IEventAction;
import backend.service.component.Open;
import backend.service.component.Take;

public class EventAction implements IEventAction {

    @Override
    public String run(int keyCode, String json) {

        if (Direction.containsKeyCode(keyCode)) {
            var direction = Direction.getLabel(keyCode);
            return new MovePlayer().run(direction, json);
        } else if (keyCode == 97) {
            return new Open().run();
        }else if (keyCode == 98) {
            return new Take().run();
        }else if (keyCode == 99) {
            return new OpenInventory().run();
        }

        return "";
    }
}
