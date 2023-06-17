package backend.service.factory;

import backend.controller.interfaces.IMessage;
import backend.service.dto.Message;

import java.util.Objects;

public final class MessageFactory {

    public IMessage create(String text, String effect) {
        return new Message(true, text, effect);
    }

    public IMessage create(Exception e) {
        if (Objects.isNull(e)) return new Message(true, "null", "");
        var name = e.getClass().getSimpleName();
        return switch (name) {
            case "DoorExeption" -> msgDoorExeption(e.getMessage());
            case "InventoryException" -> msgInventoryException(e.getMessage());
            case "ItemException" -> msgItemException(e.getMessage());
            case "MapGameException" -> msgMapGameException(e.getMessage());
            case "MoveException" -> msgMoveException(e.getMessage());
            default -> new Message(false, "Exception não mapeada", "");
        };
    }

    private IMessage msgMoveException(String text) {
        return new Message(false, text, null);
    }

    private IMessage msgMapGameException(String text) {
        return new Message(false, text, null);
    }

    private IMessage msgItemException(String text) {
        return new Message(false, text, null);
    }

    private IMessage msgInventoryException(String text) {
        return new Message(false, text, "erro");
    }

    private IMessage msgDoorExeption(String text) {
        var effect = "";
        if (text.equalsIgnoreCase("Porta está fechada!")) {
            effect = "erro";
        } else if (text.equalsIgnoreCase("Porta não existe!"))
            effect = null;
        return new Message(false, text, effect);
    }
}
