package rules.model;

import backend.service.MovePlayer;
import rules.enums.Direction;
import rules.interfaces.IEventAction;

public class EventAction implements IEventAction {

    @Override
    public String run(int keyCode, String json) {

        if (Direction.containsKeyCode(keyCode)) {

            var direction = Direction.getLabel(keyCode);
            return new MovePlayer().run(direction, json);

        }
        return "";
    }
}
