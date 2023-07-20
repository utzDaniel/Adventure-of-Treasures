package backend.service.dto;

import backend.controller.interfaces.IMessage;

public record Message(
        boolean sucess,

        String text,

        String effect) implements IMessage {
}
