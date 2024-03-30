package backend.service.dto;

import backend.controller.interfaces.IMessage;

public record Message(
        boolean success,

        String text,

        String effect) implements IMessage {
}
