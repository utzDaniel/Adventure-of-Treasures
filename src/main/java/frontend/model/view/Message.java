package frontend.model.view;

import backend.controller.interfaces.IMessage;

public record Message(boolean success,
                      String text,
                      String effect) implements IMessage {
}
