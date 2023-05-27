package rules.model;

import backend.service.MovePlayer;
import backend.service.OpenInventory;
import rules.enums.Direction;
import rules.interfaces.IEventAction;
import backend.service.Open;
import backend.service.Take;

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
