package frontend.request;

import backend.controller.interfaces.IEquipItemRequest;

public record EquipItemRequest(
        String action,
        String name) implements IEquipItemRequest {
}
