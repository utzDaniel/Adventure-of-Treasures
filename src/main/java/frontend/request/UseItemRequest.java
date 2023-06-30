package frontend.request;

import backend.controller.interfaces.IUseItemRequest;

public record UseItemRequest(
        String action,
        String name) implements IUseItemRequest {
}
