package frontend.request;

import backend.controller.interfaces.IDropItemRequest;

public record DropItemRequest(
        String action,
        String name) implements IDropItemRequest {
}
