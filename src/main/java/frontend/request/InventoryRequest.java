package frontend.request;

import backend.controller.interfaces.IInventoryRequest;

public record InventoryRequest(String action) implements IInventoryRequest {
}
