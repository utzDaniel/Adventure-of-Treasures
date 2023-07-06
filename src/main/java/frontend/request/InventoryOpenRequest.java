package frontend.request;

import backend.controller.interfaces.IInventoryOpenRequest;

public record InventoryOpenRequest(String action) implements IInventoryOpenRequest {
}
